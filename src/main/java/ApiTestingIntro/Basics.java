package ApiTestingIntro;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        create a place
        String response = given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/jason", ContentType.TEXT))).log().all().queryParam("key", ": key =qaclick123").header("Content-Type", "application/jason")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n")
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().asString();

        JsonPath js = ReUsableMethods.rawToJSon(response);//For parsing jason
        String placeId = js.getString("place_id");

        System.out.println(placeId);


//Updating the existing place address using placid
        String newAddress = "70 Summer walk, USA";
        given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/jason", ContentType.TEXT))).log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
                body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().put("maps/api/place/update/json").
                then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//Retreive the updated place
        String getPlaceResponse = given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/jason", ContentType.TEXT))).log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get("/maps/api/place/get/json").
                then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJSon(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
    }
}

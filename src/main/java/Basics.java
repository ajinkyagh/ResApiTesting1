import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import  static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/jason", ContentType.TEXT))).log().all().queryParam("key",": key =qaclick123").header("Content-Type","application/jason")
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
                            .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().asString()
                                ;

        JsonPath js=new JsonPath(response);//For parsing jason
        String placeId=js.getString("place_id");

        System.out.println(placeId);
    }
}

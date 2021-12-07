import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class Basics {
    public static void main(String[] args){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/jason", ContentType.TEXT))).log().all().queryParam("key",": key =qaclick123").header("Content-Type","application/jason")
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
                            .then().log().all().assertThat().statusCode(200);
    }
}

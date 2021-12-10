package LibraryApiTesting.main;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class DynamicJson {
    @Test
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";

        String response=given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT))).log().all().header("Content-Type", "application/json")
                .body(payload.addBook("adsfs","6464"))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js=ReUsableMethods.rawToJSon(response);
        String id=js.get("ID");
        System.out.println(id);
    }
}
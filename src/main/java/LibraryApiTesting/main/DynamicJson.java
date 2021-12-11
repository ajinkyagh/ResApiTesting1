package LibraryApiTesting.main;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class DynamicJson {
    String id;
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
//Add book
        String response=given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT))).log().all().header("Content-Type", "application/json")
                .body(payload.addBook(isbn,aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js=ReUsableMethods.rawToJSon(response);
        id=js.get("ID");
        System.out.println(id);
    }

    @Test
    public void deleteBook(){
        RestAssured.baseURI = "http://216.10.245.166";

        given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT))).log().all().header("Content-Type", "application/json")
                .queryParam("ID",id)
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);

    }

    @DataProvider(name="BooksData")
    public Object[][] getData(){
        return new Object[][]{{"ojfwdy","9353"},{"cwetedfe","42d3"},{"okgfet","5fg3"}};
    }
}
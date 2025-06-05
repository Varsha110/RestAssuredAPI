import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

public class JsonFileHandling {
	@Test
	public static void JsonFile() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Content of the file to string: Convert file to bytes and then byte to String

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("D:\\PostMan\\RestAssured\\APIs\\AddPlaceAPIBody.txt"))))
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		System.out.println(response);
	}

}

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;// This should be typed manually
import static org.hamcrest.Matchers.*; // This should be typed manually

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class BasicsRestAssured {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Add/Create Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace())

				.when().post("maps/api/place/add/json")

				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		System.out.println(">> Response is : " + response);

		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id"); // "place_id" will get from output "Body"
		System.out.println("place_id is: " + placeID);

		// Update Place
		
		String ExpectedNewAddress = "Legacy Celestia, Wakad, Pune";

		//String updatedResponse = 
				given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n"
						+ "\"address\":\""+ ExpectedNewAddress +"\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
				
				/*.header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().asString();*/

	//	System.out.println("Updated Address is : " + updatedResponse);

		// Get place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);

		
		String ActualAddress = js1.getString("address");

		System.out.println("Actual Address is : " + ActualAddress);
		
		Assert.assertEquals(ActualAddress, ExpectedNewAddress);

	}

}

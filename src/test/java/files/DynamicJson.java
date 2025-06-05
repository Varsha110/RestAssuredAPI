package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

	@Test(dataProvider="BooksDetails")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	String response =	given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(response);
	
	JsonPath js = ReusableMethods.rawToJson(response);
	String getID = js.get("ID");
	
	System.out.println(">> ID generAted is: " + getID);
	}
	
	
	
	// array is a collection of elements
	// multidimensional array = collection of arrays
	
	@DataProvider(name="BooksDetails")
	public Object[][] dataSet() {
		return new Object[][] {{"qwerty","12345"}, {"poiuyt","098765"}, {"asdfgh","876543"}};
		
	}

}
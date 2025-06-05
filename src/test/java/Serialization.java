/*
 * import io.restassured.RestAssured; import io.restassured.response.Response;
 * 
 * import static io.restassured.RestAssured.*;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import POJO.addPlace; import POJO.locationChildOfAddPlace;
 * 
 * public class serialization {
 * 
 * public static void main(String[] args) { RestAssured.baseURI =
 * "https://rahulshettyacademy.com";
 * 
 * addPlace AP = new addPlace(); AP.setAccuracy(50);
 * AP.setName("Frontline Legacy"); AP.setAddress("C503, Bhumkar Nagar");
 * AP.setLanguage("Chhattisgarhi"); AP.setPhone_number(" (+91) 4539890089");
 * AP.setWebsite("https://varsha.academy.com"); List<String> myList = new
 * ArrayList<String>(); myList.add("shoe park"); myList.add("shop");
 * 
 * AP.setTypes(myList);
 * 
 * locationChildOfAddPlace loc = new locationChildOfAddPlace();
 * loc.setLat(-38.383494); loc.setLng(33.427362);
 * 
 * AP.setLocation(loc);
 * 
 * Response res = given().queryParam("key", "qaclick123") .body(AP)
 * .when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).
 * extract().response(); } }
 */

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.addPlace;
import POJO.locationChildOfAddPlace;

public class Serialization {

    public static void main(String[] args) {
        // Set the base URI for REST Assured
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Create and populate the addPlace object
        addPlace AP = new addPlace();
        AP.setAccuracy(50); // Correct method name
        AP.setName("Frontline Legacy");
        AP.setAddress("C503, Bhumkar Nagar");
        AP.setLanguage("Chhattisgarhi");
        AP.setPhone_number("(+91) 4539890089");
        AP.setWebsite("https://varsha.academy.com");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        AP.setTypes(myList);

        // Create and set location details
        locationChildOfAddPlace loc = new locationChildOfAddPlace();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        AP.setLocation(loc);

        // Send the POST request and capture the response
        Response res = given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json") // Add content type header
                .body(AP) // Serialize addPlace object
                .when().post("/maps/api/place/add/json") // API endpoint
                .then().log().all() // Log the entire request and response
                .assertThat().statusCode(200) // Verify the status code
                .extract().response();

        // Print the response for debugging
        System.out.println("Response: " + res.asString());
    }
}
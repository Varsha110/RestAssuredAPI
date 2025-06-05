import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.addPlace;
import POJO.locationChildOfAddPlace;

public class SpecBuilderTest {

    public static void main(String[] args) {

        // Set the base URI for REST Assured
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Create and populate the addPlace object with required data
        addPlace addPlacePayload = new addPlace();

        // Setting basic information for the place
        addPlacePayload.setAccuracy(50); // Accuracy level
        addPlacePayload.setName("Frontline Legacy"); // Name of the place
        addPlacePayload.setAddress("C503, Bhumkar Nagar"); // Address
        addPlacePayload.setLanguage("Chhattisgarhi"); // Language preference
        addPlacePayload.setPhone_number("(+91) 4539890089"); // Contact number
        addPlacePayload.setWebsite("https://varsha.academy.com"); // Website

        // Adding types/categories of the place
        List<String> typesList = new ArrayList<>();
        typesList.add("shoe park");
        typesList.add("shop");
        addPlacePayload.setTypes(typesList);

        // Setting location coordinates
        locationChildOfAddPlace location = new locationChildOfAddPlace();
        location.setLat(-38.383494); // Latitude
        location.setLng(33.427362); // Longitude
        addPlacePayload.setLocation(location);

        // Define Request Specification using RequestSpecBuilder
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com") // Base URI
                .addQueryParam("key", "qaclick123") // Query parameter for API key
                .setContentType(ContentType.JSON) // Content type for the request
                .build();

        // Define Response Specification using ResponseSpecBuilder
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200) // Expected status code
                .expectContentType(ContentType.JSON) // Expected content type
                .build();

        // Send the POST request with the specified payload and request specification
        RequestSpecification request = given().spec(requestSpec).body(addPlacePayload);

        // Execute the API request and validate the response using ResponseSpecification
        Response response = request
                .when().post("/maps/api/place/add/json") // API endpoint for adding a place
                .then().spec(responseSpec) // Validate response using response specification
                .extract().response(); // Extract the full response

        // Convert the response to a string for logging/debugging
        String responseString = response.asString();

        // Print the response for verification
        System.out.println("Response: " + responseString);
    }
}
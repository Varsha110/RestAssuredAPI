import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthTestPOJOPart1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String Access_Token = js.getString("access_token");

		System.out.println(">> Bearer Token is: " + Access_Token);

		// Get Course Details

		String getCourseDetails = given().queryParam("access_token", Access_Token).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		
		System.out.println("Course Details: "+ getCourseDetails);
		
		
	}

}

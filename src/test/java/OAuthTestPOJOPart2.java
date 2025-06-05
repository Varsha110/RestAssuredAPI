//DeSerialization POJO

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.GetCourses;
import POJO.WebAutomationChildOfCourses;
import POJO.apiChildOfCourses;

public class OAuthTestPOJOPart2 {

	public static void main(String[] args) {
		
		String[] WebAutomationCourseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
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
		// Converting it to java object by help og class

		GetCourses getCourseDetails = given().queryParam("access_token", Access_Token).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);

		System.out.println("Course Details: " + getCourseDetails);

		String getLinkedIn = getCourseDetails.getLinkedIn();
		System.out.println("** Get  LinkedIn : " + getLinkedIn);

		String getInstructor = getCourseDetails.getInstructor();
		System.out.println("** Get  Instructor : " + getInstructor);

		// Find API 2nd part courseTitle
		String getAPICourseTitle = getCourseDetails.getCourses().getApi().get(1).getCourseTitle();
		System.out.println("** API 2nd course title : " + getAPICourseTitle);

		// WIthout indexing, get the price of the appropriate courseTitle

		List<apiChildOfCourses> getAPICourseTitle1 = getCourseDetails.getCourses().getApi();
		for (int i = 0; i < getAPICourseTitle1.size(); i++) {

			if (getAPICourseTitle1.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				String getPrice = getAPICourseTitle1.get(i).getPrice();
				System.out.println("Price of " + getAPICourseTitle + "is : " + getPrice);

			}
			
			// Print all the courseTitles of WebAutomation
			
			
			ArrayList<String> a = new ArrayList<String>();
			List<WebAutomationChildOfCourses> WebAutomation = getCourseDetails.getCourses().getWebAutomation();
			
			for(int j=0; j<WebAutomation.size();j++) {
				String Titles = WebAutomation.get(j).getCourseTitle();
				System.out.println("@@ WebAutomation Titles are : " + Titles);
			}

		}

	}

}

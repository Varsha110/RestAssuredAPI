import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {

		// Create Bug
		RestAssured.baseURI = "https://varshathakur110293.atlassian.net";

		String createIssueResponse = given().log().all().header("Content-Type", "application/json").header(
				"Authorization",
				"Basic dmFyc2hhLnRoYWt1cjExOTNAZ21haWwuY29tOkFUQVRUM3hGZkdGMHFyMVdhUHpabWZWWnpyY2R6cVVKeC1ac1R0eHIyQVJNNXgxYXhOWi1JMlkydDdFXzZBT1hid3FWZXhmQ1FMTHdrT3UwWF90RGhlX0xRUW93b0NtOFpvRVZqVTJoQm5nMTFlNzk0Um9NWl9YeTVscFg3RkdXZmlwRWFuZHlEUVJnQUdTb1JwelFubmRiUGFwelcwNnhZcUhUb0JpdkMwdUY0cnRpTzhDb1lYWT02RTRDMjQ0MQ==")
				.body("{\r\n" + "    \"fields\": {\r\n" + "        \"project\": {\r\n"
						+ "            \"key\": \"SCRUM\"\r\n" + "        },\r\n"
						+ "        \"summary\": \"Website items are not working >> Automation\",\r\n"
						+ "        \"description\": {\r\n" + "            \"type\": \"doc\",\r\n"
						+ "            \"version\": 1,\r\n" + "            \"content\": [\r\n" + "                {\r\n"
						+ "                    \"type\": \"paragraph\",\r\n" + "                    \"content\": [\r\n"
						+ "                        {\r\n" + "                            \"type\": \"text\",\r\n"
						+ "                            \"text\": \"Creating of an issue using project keys and issue type names using the REST API\"\r\n"
						+ "                        }\r\n" + "                    ]\r\n" + "                }\r\n"
						+ "            ]\r\n" + "        },\r\n" + "        \"issuetype\": {\r\n"
						+ "            \"name\": \"Bug\"\r\n" + "        }\r\n" + "    }\r\n" + "}\r\n" + "")
				.when().post("rest/api/3/issue").then().assertThat().statusCode(201).extract().response().asString();

		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.get("id");
		System.out.println(">>> Issued Id is: " + issueId);

		// Add Attachments

		given().pathParam("key", issueId).header("Authorization",
				"Basic dmFyc2hhLnRoYWt1cjExOTNAZ21haWwuY29tOkFUQVRUM3hGZkdGMHFyMVdhUHpabWZWWnpyY2R6cVVKeC1ac1R0eHIyQVJNNXgxYXhOWi1JMlkydDdFXzZBT1hid3FWZXhmQ1FMTHdrT3UwWF90RGhlX0xRUW93b0NtOFpvRVZqVTJoQm5nMTFlNzk0Um9NWl9YeTVscFg3RkdXZmlwRWFuZHlEUVJnQUdTb1JwelFubmRiUGFwelcwNnhZcUhUb0JpdkMwdUY0cnRpTzhDb1lYWT02RTRDMjQ0MQ==")
				.header("X-Atlassian-Token", "no-check")
				.multiPart("file", new File("/C:/Users/varsh/Downloads/Screenshot 2025-01-04 131105.png")).log().all()
				.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

	}

}

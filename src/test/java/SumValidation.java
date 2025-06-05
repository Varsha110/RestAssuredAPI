import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public static void sumOfCoursses() {
		
		int sum =0;

		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int amount = price * copies;
			System.out.println(">> Total Amount is: " + amount);
			sum = sum + amount;
		
		}
		System.out.println("Total Sum is: " +  sum);
		
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(">> Total is : " + purchaseAmount);
		
		Assert.assertEquals(purchaseAmount, sum);
		
	}
}

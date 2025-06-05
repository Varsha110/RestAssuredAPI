import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.CoursePrice());

		// Print No. of Courses

		int CoursesCount = js.getInt("courses.size()");

		System.out.println(CoursesCount);

		// Print purchase amount

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		// Print title of the first course

		String CoursefirstTitle = js.get("courses[0].title");
		System.out.println(CoursefirstTitle);

		int CoursesFirstPrice = js.getInt("courses[0].price");
		System.out.println(">> Courses First Price is : " + CoursesFirstPrice);

		// Print all courses titles and their respective Prices

		for (int i = 0; i < CoursesCount; i++) {

			String CoursesTitle = js.getString("courses[" + i + "].title");

			System.out.println("All Courses Title is : " + CoursesTitle);

			System.out.println("Price is : " + js.get("courses[" + i + "].price").toString());
		}

		// Print No. of copies sold by RPA

		for (int i = 0; i < CoursesCount; i++) {

		String CoursesTitle = js.getString("courses[" + i + "].title");
			
			if(CoursesTitle.equals("RPA")) {
			int copies =	js.get("courses["+i+"].copies");
			
			System.out.println(copies);
			break;
				
			}


		}

	}

}

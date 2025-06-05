import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.ECommereceOrderDetail;
import POJO.ECommereceOrders;
import POJO.LoginRequest;
import POJO.LoginResponse;

public class ECommerceEndToEndFlow {

	public static void main(String[] args) {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginReq = new LoginRequest();
		loginReq.setUserEmail("varsha.thakur1193@gmail.com");
		loginReq.setUserPassword("@Maapapa0515");

		RequestSpecification reqlog = given().spec(req).body(loginReq);
		LoginResponse loginResponse = reqlog.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponse.class);

		String token = loginResponse.getToken();
		String UserId = loginResponse.getUserId();
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getUserId());

		// Add Product

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
				.param("productName", "Shirtsss kid").param("productAddedBy", UserId)
				.param("productCategory", "fashion").param("productSubCategory", "shirtttts")
				.param("productPrice", "12000").param("productDescription", "kid picutes").param("productFor", "kiiiid")
				.multiPart("productImage", new File("C:\\Users\\varsh\\OneDrive\\Pictures\\Screenshots\\babypic.png"));

		String ResAddProduct = reqAddProduct.when().post("api/ecom/product/add-product").then().log().all().extract()
				.response().asString();

		JsonPath js = new JsonPath(ResAddProduct);
		String ProductId = js.get("productId");
		js.get("message");

		// Create Order

		RequestSpecification CreateOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		ECommereceOrderDetail eCommereceOrderDetail = new ECommereceOrderDetail();
		eCommereceOrderDetail.setCountry("India");
		eCommereceOrderDetail.setProductOrderedId(ProductId);

		List<ECommereceOrderDetail> orderDetailList = new ArrayList<ECommereceOrderDetail>();
		orderDetailList.add(eCommereceOrderDetail);

		ECommereceOrders eCommereceOrders = new ECommereceOrders();
		eCommereceOrders.setOrders(orderDetailList);

		RequestSpecification createOrderReq = given().log().all().spec(CreateOrderBaseReq).body(eCommereceOrders);
		String createOrderRes = createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract()
				.response().asString();

		System.out.println(createOrderRes);

		// Delete Order

		RequestSpecification DeleteOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		RequestSpecification deleteProdReq = given().log().all().spec(DeleteOrderBaseReq).pathParam("productId",
				ProductId);
		String deleteProductResponse = deleteProdReq.when().delete("api/ecom/product/delete-product/{productId}").then()
				.log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteProductResponse);
		String Message = js1.get("message");
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
		
		System.out.println(">>>> Deleted Response"+ deleteProductResponse);
		

	}

}

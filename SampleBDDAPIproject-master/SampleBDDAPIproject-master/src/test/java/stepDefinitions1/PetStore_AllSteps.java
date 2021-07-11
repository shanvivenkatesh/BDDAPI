package stepDefinitions1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClass.petStore.Pojo_PetStore_InvalidResponse;
import pojoClass.petStore.Pojo_PetStore_Post_Request;
import pojoClass.petStore.Pojo_PetStore_Valid_Response;
import pojoClass.petStore.Pojo_PostReqBody;
import utilities.BaseClass;
import static io.restassured.RestAssured.*;

public class PetStore_AllSteps extends BaseClass {

	RequestSpecification postRequest;
	Response response;
	Pojo_PostReqBody postReqBody = new Pojo_PostReqBody();
	Pojo_PetStore_Valid_Response validResponseBody;
	Pojo_PetStore_InvalidResponse invalidResponseBody;
	int actualStatusCode;
	RequestSpecification getRequest;
	int orderId;
		
	@Given("placing a valid pet order")
	public void postValidPetOrder() throws IOException {

		postRequest = given().spec(requestSpecification()).body(postReqBody.postValidRequest());

	}
	
	@When("User calls PostPetOrderAPI with {string} post HTTP method")
	public void receivePetOrderResponse(String postResources) {

		response = postRequest.when().post(postResources).then().spec(responseSpecification()).extract().response();

	}

	@Given("placing a invalid pet order")
	public void postInvalidPetOrder() throws IOException {

		postReqBody = new Pojo_PostReqBody();

		postRequest = given().spec(requestSpecification())
				.body("{\r\n" + "    \"id\": 34A33,\r\n" + "    \"petId\": 408584,\r\n" + "    \"quantity\": 47754,\r\n"
						+ "    \"shipDate\": \"2000-08-20T11:56:30.604Z\",\r\n" + "    \"status\": \"placed\",\r\n"
						+ "    \"complete\": true\r\n" + "}");

	}
	
	@Then("validate the API status code {int}")
	public void validatePetOrderHttpStatusCode(int expectedStatusCode) {

		actualStatusCode = response.getStatusCode();
		assertEquals(expectedStatusCode, actualStatusCode);
		
	}
	
	@And("validate the {string} response message")
	public void validatePetOrderPostResponse(String httpdMethod) {

		
		if (httpdMethod.equalsIgnoreCase("POST") || httpdMethod.equalsIgnoreCase("GET")) {
			if (actualStatusCode == 200) {
				validResponseBody = response.as(Pojo_PetStore_Valid_Response.class);

				assertEquals(postReqBody.postValidRequest().getId(), validResponseBody.getId());
				assertEquals(postReqBody.postValidRequest().getPetId(), validResponseBody.getPetId());
				assertEquals(postReqBody.postValidRequest().getQuantity(), validResponseBody.getQuantity());
				assertEquals(postReqBody.postValidRequest().getStatus(), validResponseBody.getStatus());
				assertEquals(postReqBody.postValidRequest().isComplete(), validResponseBody.isComplete());

				System.out.println("Pet Store Post HTTP request is successful");

			} else if(actualStatusCode == 400 ) {

				invalidResponseBody = response.as(Pojo_PetStore_InvalidResponse.class);

				assertEquals(400, invalidResponseBody.getCode());
				assertEquals("unknown", invalidResponseBody.getType());
				assertEquals("bad input", invalidResponseBody.getMessage());

				System.out.println("Pet Store " + httpdMethod + "request is unsuccessful");

			}
			else {
				
				invalidResponseBody = response.as(Pojo_PetStore_InvalidResponse.class);

				assertEquals(1, invalidResponseBody.getCode());
				assertEquals("error", invalidResponseBody.getType());
				assertEquals("Order not found", invalidResponseBody.getMessage());

				System.out.println("Pet Store " + httpdMethod + "request is unsuccessful");
				
			}
			

		}
	}
	@Given("get a pet order ID")
	public void get_a_pet_order_id() throws IOException {
		// RequestSpecification request = new
		// RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
		getRequest = given().spec(requestSpecification());
	}

	
	@When("User calls GetPetOrderAPI {string} with valid order ID")
	public void user_calls_get_pet_order_api_with_valid_order_ID(String resources) {

		 orderId =postReqBody.postValidRequest().getId();

		// getResponse = new
		// ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		response = getRequest.when().get(resources+orderId ).then().spec(responseSpecification()).extract().response();
	}
	
	@Then("validate the Get API status code {int}")
	public void validatePetOrderHttpGetStatusCode(int expectedStatusCode) {

		actualStatusCode = response.getStatusCode();
		assertEquals(expectedStatusCode, actualStatusCode);
		
	}

	@When("User calls GetPetOrderAPI {string} with invalid order ID")
	public void user_calls_get_pet_order_api_with_invalid_order_ID(String resources) {

		
		// getResponse = new
		// ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(404).build();
		response = getRequest.when().get(resources + "555545").then().spec(responseSpecification()).extract()
				.response();
	}
	
}

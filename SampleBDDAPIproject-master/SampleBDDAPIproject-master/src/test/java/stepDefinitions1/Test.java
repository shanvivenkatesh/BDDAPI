package stepDefinitions1;

import static io.restassured.RestAssured.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		String response= given().log().all().header("Content-type", "application/json").body("{\r\n" + 
				"    \"id\": 1999,\r\n" + 
				"    \"petId\": 191923,\r\n" + 
				"    \"quantity\": 25,\r\n" + 
				"    \"shipDate\": \"2021-07-09T11:56:30.604Z\",\r\n" + 
				"    \"status\": \"placed\",\r\n" + 
				"    \"complete\": true\r\n" + 
				"}").
		 when().post("/store/order").
		 then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		int id = js.get("id");
		System.out.println(js.get("id"));
		
		String rep= given().log().all().when().get("/store/order/55666").then().log().all().extract().response().asString();
		System.out.println(rep);
		
	}
}

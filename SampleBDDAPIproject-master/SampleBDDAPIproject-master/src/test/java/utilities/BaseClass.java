package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {

	public static RequestSpecification request;

	public static RequestSpecification requestSpecification() throws IOException {
		// It generates all the request and response logs and store in text	format
		if (request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("RequestResponseLogs.txt"));

			// Commonly used Request methods across all the request are
			// constructed in Request Specification Builder
			request = new RequestSpecBuilder().setBaseUri(getPropertyValues("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return request;
		}
		return request;
	}

	public static ResponseSpecification responseSpecification() {
		// Commonly used Response methods across all the request are constructed
		// in Response Specification Builder
		ResponseSpecification postResponse = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return postResponse;
	}

	// Below method gets the common properties from Config.Properties file
	public static String getPropertyValues(String url) throws IOException {

		Properties propertyFile = new Properties();
		FileInputStream propFilePath = new FileInputStream("D:\\Bava\\SampleBDDAPIproject-master\\SampleBDDAPIproject-master\\src\\test\\java\\utilities\\Config.properties");
		propertyFile.load(propFilePath);
		return propertyFile.getProperty(url);

	}
}

package test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class AddVehicleRSTest {
	
	/**
	 * Unit Test for AddVehicleRS Resource
	 */
	@BeforeTest
	public void setUp() {
	
		RestAssured.baseURI = "http://localhost:8080/VehicleInventoryRestApplication/vehicleInventory";
	}
	
	@Test
	public void testVehicleAddSuccess() {
		
		Response response  = given()
				.formParam("vin", "337hhh333")
				.formParam("type", "Truck")
				.formParam("make", "Ford")
				.formParam("model", "F150")
				.formParam("year", 2019)
			    .when()
			    .contentType (ContentType.URLENC)
			    .post("/addvehicle")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Successfully Added", statusCode /*actual value*/, 200 /*expected value*/);
	}

}

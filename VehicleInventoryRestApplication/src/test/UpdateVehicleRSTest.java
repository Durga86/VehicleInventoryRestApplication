package test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class UpdateVehicleRSTest {
	
	/**
	 * Unit Test for UpdateVehicleRS Resource
	 */
	@BeforeTest
	public void setUp() {
	
		RestAssured.baseURI = "http://localhost:8080/VehicleInventoryRestApplication/vehicleInventory";
	}
	
	@Test
	public void testVehicleUpdateSuccess() {
		
		Response response  = given()
				.formParam("vin", "337hhh333")
				.formParam("type", "Air Plane")
				.formParam("make", "Boeing")
				.formParam("model", "727")
				.formParam("year", 2019)
			    .when()
			    .contentType (ContentType.URLENC)
			    .put("/updatevehicle")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Successfully Updated", statusCode /*actual value*/, 200 /*expected value*/);
	}
	
	@Test
	public void testVehicleUpdateFail() {
		
		Response response  = given()
				.formParam("vin", "2837467bdj")
				.formParam("type", "Air Plane")
				.formParam("make", "Boeing")
				.formParam("model", "727")
				.formParam("year", 2019)
			    .when()
			    .contentType (ContentType.URLENC)
			    .put("/updatevehicle")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Not Found", statusCode /*actual value*/, 404 /*expected value*/);
	}

}

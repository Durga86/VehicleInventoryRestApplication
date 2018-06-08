package test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class DeleteMostRecentVehicleRSTest {
	
	/**
	 * Unit Test for DeleteMostRecentVehicleRS Resource
	 */
	@BeforeTest
	public void setUp() {
	
		RestAssured.baseURI = "http://localhost:8080/VehicleInventoryRestApplication/vehicleInventory";
	}
	
	@Test
	public void testVehicleDeleteSuccess() {
		
		Response response  = given()
			    .when()
			    .delete("/deleterecentvehicle")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Successfully Deleted", statusCode /*actual value*/, 200 /*expected value*/);
	}

}

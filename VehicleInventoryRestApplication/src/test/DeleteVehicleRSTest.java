package test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class DeleteVehicleRSTest {
	
	/**
	 * Unit Test for DeleteVehicleRS Resource
	 */
	@BeforeTest
	public void setUp() {
	
		RestAssured.baseURI = "http://localhost:8080/VehicleInventoryRestApplication/vehicleInventory";
	}
	
	@Test
	public void testVehicleDeleteSuccess() {
		
		Response response  = given()
			    .when()
			    .delete("/deletevehicle?vin=337hhh333")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Successfully Deleted", statusCode /*actual value*/, 200 /*expected value*/);
	}
 
	@Test
	public void testVehicleDeleteFail() {
		Response response  = given()
			    .when()
			    .delete("/deletevehicle?vin=2332342")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Not found", statusCode /*actual value*/, 400 /*expected value*/);
	}

}

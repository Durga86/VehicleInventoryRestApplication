package test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class SearchVehicleByTypeRSTest {
	
	/**
	 * Unit Test for SearchVehicleByVinRS Resource
	 */
	@BeforeTest
	public void setUp() {
	
		RestAssured.baseURI = "http://localhost:8080/VehicleInventoryRestApplication/vehicleInventory";
	}
	
	@Test
	public void testVehicleSearchSuccess() {
		
		Response response  = given()
			    .when()
			    .get("/searchVehicleByType?type=Truck")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Correct Vehicle Found", statusCode /*actual value*/, 200 /*expected value*/);
	}
 
	@Test
	public void testVehicleSearchFail() {
		Response response  = given()
			    .when()
			    .get("/searchVehicleByType?type=jeep")
			    .then()
			    .extract().response();
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals("Vehicle Not found", statusCode /*actual value*/, 400 /*expected value*/);
	}

}

package resources;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.VehicleInventoryDAO;
import data.Vehicle;

/**
 * @author Durga
 * 
 * This API adds a vehicle to the database.
 *
 */

@Path("/addvehicle")
public class AddVehicleRS {
	
	/*
	 * Adds Vehicle to the Database
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addVehicle(@FormParam("vin") String vin,
      @FormParam("type") String type,
      @FormParam("make") String make,
      @FormParam("model") String model,
      @FormParam("year") int year) throws IOException{
		VehicleInventoryDAO vehicleInventoryDAO= new VehicleInventoryDAO();
		Date currDate= new Date();
		long currTime = currDate.getTime();
		Timestamp currTimeStamp = new Timestamp(currTime);
		Vehicle vehicle = new Vehicle(vin,type,make,model,year,currTimeStamp);
		int result = vehicleInventoryDAO.addVehicle(vehicle);
		if(result == 1){
			return Response.status(200).entity("Successfully Added Vehicle with VIN "+vin).build();
		}
		return Response.status(400).entity("Error Occured").build();
	}
}

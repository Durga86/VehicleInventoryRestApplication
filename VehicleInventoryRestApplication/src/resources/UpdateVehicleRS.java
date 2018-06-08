package resources;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.VehicleInventoryDAO;
import data.Vehicle;

/**
 * @author Durga
 * 
 * This API updates vehicle details in the database.
 *
 */

@Path("/updatevehicle")
public class UpdateVehicleRS {
	
	/*
	 * Update vehicle details
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateVehicle(@FormParam("vin") String vin,
      @FormParam("type") String type,
      @FormParam("make") String make,
      @FormParam("model") String model,
      @FormParam("year") int year) throws IOException{
		VehicleInventoryDAO vehicleInventoryDAO = new VehicleInventoryDAO();
		Date currDate= new Date();
		long currTime = currDate.getTime();
		Timestamp currTimeStamp = new Timestamp(currTime);
		Vehicle vehicledetails = new Vehicle(vin,type,make,model,year,currTimeStamp);
		int result = vehicleInventoryDAO.updateVehicle(vehicledetails);
		if(result == 1){
			return Response.status(200).entity("Successfully updated Vehicle").build();
		}
		else if(result == 0)
		{
			return Response.status(404).entity("Cannot find Vehicle with VIN" + vin).build();
		}
		else
		{
			return Response.status(400).entity("Error Occurred").build();
		}
	}

}

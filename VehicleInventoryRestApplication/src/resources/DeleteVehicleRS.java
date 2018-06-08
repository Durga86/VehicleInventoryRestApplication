package resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.VehicleInventoryDAO;

/**
 * @author Durga
 * 
 * This API deletes vehicle from the database.
 *
 */

@Path("/deletevehicle")
public class DeleteVehicleRS {
	
	/*
	 * Deletes the vehicle from DB
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteVehicleDetails(@QueryParam("vin") String vin) {
		VehicleInventoryDAO vehicleInventoryDAO = new VehicleInventoryDAO();
		
		try {
			int result = vehicleInventoryDAO.deleteVehicle(vin);
			if(result == 1){
		    	  return Response.status(200).entity("Successfully deleted vehicle with VIN "+vin).build();
		      }
		      
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(400).entity("Failure").build();
	}


}

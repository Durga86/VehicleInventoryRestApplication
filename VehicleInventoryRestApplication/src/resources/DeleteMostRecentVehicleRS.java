package resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.VehicleInventoryDAO;

/**
 * @author Durga
 * 
 * This API deletes most recently added vehicle from the database.
 *
 */

@Path("/deleterecentvehicle")
public class DeleteMostRecentVehicleRS {
	
	/*
	 * Deletes most recent vehicle details
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteVehicleDetails() {
		VehicleInventoryDAO vehicleInventoryDAO = new VehicleInventoryDAO();
		
		try {
			int result = vehicleInventoryDAO.deleteMostRecentVehicle();
			if(result == 1){
		    	  return Response.status(200).entity("Successfully deleted most recent vehicle").build();
		      }
		      
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(400).entity("Failure").build();
	}


}

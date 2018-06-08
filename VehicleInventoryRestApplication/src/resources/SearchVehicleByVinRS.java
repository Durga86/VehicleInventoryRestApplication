package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dao.VehicleInventoryDAO;
import data.Vehicle;

/**
 * @author Durga
 * 
 * This API searches vehicle in the database based on vin number.
 *
 */

@Path("/searchVehicleByVin")
public class SearchVehicleByVinRS {
	
	/*
	 * Search the vehicle based on vin number
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchVehicleByVin(@QueryParam("vin") String vin){
		Vehicle searchedVehicle;
		VehicleInventoryDAO vehicleInventoryDAO = new VehicleInventoryDAO();
		try {
			searchedVehicle = vehicleInventoryDAO.searchVehicleByVin(vin);
			if(searchedVehicle != null)
			{
				Gson gson = new Gson();
			    String vehicleJson = gson.toJson(searchedVehicle);
				return Response.ok(vehicleJson).build();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(400).build();
	}

}

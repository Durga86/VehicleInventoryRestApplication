package resources;

import java.util.List;

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
 * This API searches vehicle in the database based on vehicle type.
 *
 */

@Path("/searchVehicleByType")
public class SearchVehicleByTypeRS {
	
	/*
	 * Search the vehicle based on vin number
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchVehicleByVin(@QueryParam("type") String type){
		List<Vehicle> searchedVehicleList;
		VehicleInventoryDAO vehicleInventoryDAO = new VehicleInventoryDAO();
		try {
			searchedVehicleList = vehicleInventoryDAO.searchVehicleByType(type);
			if(searchedVehicleList != null && !searchedVehicleList.isEmpty())
			{
				Gson gson = new Gson();
			    String vehicleListJson = gson.toJson(searchedVehicleList);
				return Response.ok(vehicleListJson).build();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(400).build();
	}

}

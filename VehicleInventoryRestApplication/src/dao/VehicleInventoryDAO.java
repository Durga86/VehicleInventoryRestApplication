/**
 * VehicleInventoryDAO
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.Vehicle;

/**
 * @author Durga
 * 
 * This DAO class maintains the DB Connections and performs CRUD Operations.
 *
 */
public class VehicleInventoryDAO {
	
	public static Connection dbConnection;
	public Statement stmt;
	
	/**
	 * Returns DB Connection
	 * @return
	 * @throws SQLException
	 */
	public static Connection getDbConnection() throws SQLException
	{
		try {
			Class.forName ("org.h2.Driver"); 
			dbConnection = DriverManager.getConnection ("jdbc:h2:~/test", "testdb","testpass"); 
		}
		catch(ClassNotFoundException exception)
		{
			exception.printStackTrace();
		}
		return dbConnection;
	}
	
	/**
	 * Returns vehicle details based on vehicle's vin number
	 * @param vin
	 * 
	 * @return
	 */
	public Vehicle searchVehicleByVin(String vin)
	{
		Vehicle searchedVehicle = null;
		try {
			dbConnection = getDbConnection();
			stmt = dbConnection.createStatement();
			ResultSet vehicleResultSet = stmt.executeQuery("select * from vehicle_inventory where vin='"+vin+"'");
			if(vehicleResultSet != null)
			{
				while (vehicleResultSet.next()) {
					searchedVehicle = new Vehicle();
					searchedVehicle.setVin(vehicleResultSet.getString("VIN"));
					searchedVehicle.setType(vehicleResultSet.getString("TYPE")); 
					searchedVehicle.setMake(vehicleResultSet.getString("MAKE"));
					searchedVehicle.setModel(vehicleResultSet.getString("MODEL")); 
					searchedVehicle.setYear(vehicleResultSet.getInt("YEAR"));
					searchedVehicle.setInventoryAddedDate(vehicleResultSet.getTimestamp("INVENTORY_ADDEDON"));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();;
		}
		finally
		{
	      try{
	         if(dbConnection!=null)
	         {
	        	 dbConnection.close();
	         }
	      }catch(SQLException exception){
	    	  exception.printStackTrace();
	      }
		}
		return searchedVehicle;
	}

	/** Adds Vehicle into DB
	 * 
	 * @param vehicle
	 * @return
	 */
	public int addVehicle(Vehicle vehicle) {
		try {
			dbConnection = getDbConnection();
				String sql="insert into vehicle_inventory(vin,type,make,model,year,inventory_addedon) values (?,?,?,?,?,?)";
				PreparedStatement preStmt = dbConnection.prepareStatement(sql);
				
				if(vehicle != null)
				{
					preStmt.setString(1, vehicle.getVin());
					preStmt.setString(2, vehicle.getType());
					preStmt.setString(3, vehicle.getMake());
					preStmt.setString(4, vehicle.getModel());
					preStmt.setInt(5, vehicle.getYear());
					preStmt.setTimestamp(6, vehicle.getInventoryAddedDate());
					    
					return preStmt.executeUpdate();
				}			
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
	      try{
	         if(dbConnection!=null)
	         {
	        	 dbConnection.close();
	         }
	      }catch(SQLException exception){
	    	  exception.printStackTrace();
	      }
		}
		return -1;
	}
	
	
	/**
	 * Deletes Vehicle from DB
	 * @param vin
	 * 
	 * @return
	 */
	public int deleteVehicle(String vin)
	{
		try {
			dbConnection = getDbConnection();
			stmt = dbConnection.createStatement();
			int result = stmt.executeUpdate("delete from vehicle_inventory where vin='"+vin+"'");
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();;
		}
		finally
		{
	      try{
	         if(dbConnection!=null)
	         {
	        	 dbConnection.close();
	         }
	      }catch(SQLException exception){
	    	  exception.printStackTrace();
	      }
		}
		return 0;
	}
	
	/**
	 * Deletes Recent Vehicle from DB
	 * @param vin
	 * 
	 * @return
	 */
	public int deleteMostRecentVehicle()
	{
		try {
			dbConnection = getDbConnection();
			stmt = dbConnection.createStatement();
			int result = stmt.executeUpdate("delete from vehicle_inventory where "
					+ "inventory_addedon = ( select max(inventory_addedon) from vehicle_inventory )");
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();;
		}
		finally
		{
	      try{
	         if(dbConnection!=null)
	         {
	        	 dbConnection.close();
	         }
	      }catch(SQLException exception){
	    	  exception.printStackTrace();
	      }
		}
		return 0;
	}

	/** Updates Vehicle details
	 * 
	 * @param vehicle
	 * @return
	 */
	public int updateVehicle(Vehicle vehicle) {
		try {
				dbConnection = getDbConnection();
				String updateQuery="update vehicle_inventory set type=?,make=?,model=?,year=?,inventory_addedon=? where vin=?";
				PreparedStatement preStmt = dbConnection.prepareStatement(updateQuery);
				if(vehicle != null)
				{
					preStmt.setString(1, vehicle.getType());
					preStmt.setString(2, vehicle.getMake());
					preStmt.setString(3, vehicle.getModel());
					preStmt.setInt(4, vehicle.getYear());
					preStmt.setTimestamp(5, vehicle.getInventoryAddedDate());
					preStmt.setString(6, vehicle.getVin());

					return preStmt.executeUpdate();
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
	      try{
	         if(dbConnection!=null)
	         {
	        	 dbConnection.close();
	         }
	      }catch(SQLException exception){
	    	  exception.printStackTrace();
	      }
		}
		return -1;
	}

}

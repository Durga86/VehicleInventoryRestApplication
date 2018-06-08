/**
 * Vehicle Class
 */
package data;

import java.sql.Timestamp;

/**
 * @author Durga
 * 
 * This is the Vehicle Data Object class.
 *
 */

public class Vehicle {
	private String vin;
	private String type;
	private String make;
	private String model;
	private Integer year;
	private Timestamp inventoryAddedDate;

	public Vehicle(String vin, String type, String make, String model, int year, Timestamp inventoryAddedDate) {
		super();
		this.vin = vin;
		this.type = type;
		this.model = model;
		this.make = make;
		this.year = year;
		this.inventoryAddedDate = inventoryAddedDate;
	}

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the inventoryAddedDate
	 */
	public Timestamp getInventoryAddedDate() {
		return inventoryAddedDate;
	}
	/**
	 * @param inventoryAddedDate the inventoryAddedDate to set
	 */
	public void setInventoryAddedDate(Timestamp inventoryAddedDate) {
		this.inventoryAddedDate = inventoryAddedDate;
	}


}

package com.ideas2It.dao;

import java.sql.SQLException;
import java.util.List;

import com.ideas2It.model.Manufacturer;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles manufacturer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface ManufacturerDao {

	/**
	 * This method is used to insert one row in our database
	 *
	 * @param manufacturer
	 * @throws SQLException
	 * @return inserted manufacturer
	 */
	public Manufacturer insertManufacturer(Manufacturer manufacturer) throws VehicleManagementException;

	/**
	 * This method was return manufacturer details
	 * 
	 * @throws SQLException
	 * @return its return manufacturer details
	 */
	public List<Manufacturer> retriveManufacturers() throws VehicleManagementException;

	/**
	 * This method gets user given input whether it's in, then it will return object
	 *
	 * @param manufacturerId - id from the service
	 * @throws SQLException
	 * @return its return manufacturer object
	 */
	public Manufacturer retriveManufacturerById(int manufacturerId) throws VehicleManagementException;

	/**
	 * This method gets user given input wheather its in then it will remove object
	 *
	 * @param userVehicleId - id from the service
	 * @throws SQLException
	 * @return if object removed it return true, else it return false
	 */
	public boolean deleteManufacturerById(int manufacturerId) throws VehicleManagementException;

	/**
	 * Gets manufacturer id then change the specified value
	 *
	 * @param manufacturerId
	 * @param result
	 * @throws VehicleManagementException
	 * @return if value updated then it return true, else it return false
	 */
	public boolean updateManufacturer(Manufacturer manufacturer) throws VehicleManagementException;
}
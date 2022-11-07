package com.ideas2It.service;

import java.util.List;

import com.ideas2It.model.Manufacturer;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles manufacturer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface ManufacturerService {

	/**
	 * This method create manufacturer object and return it
	 * 
	 * @param name       - name from the controller
	 * @param company    - city type from the controller
	 * @param investment - investment from the controller
	 * @throws VehicleManagementException
	 * 
	 * @return its return two wheeler object
	 */
	public Manufacturer createManufacturer(String name, String city, Double investment)
			throws VehicleManagementException;

	/**
	 * This method was return manufacturer details
	 * 
	 * @throws SQLException
	 * @return its return manufacturer details
	 */
	public List<Manufacturer> getManufacturers() throws VehicleManagementException;

	/**
	 * This method gets user given input wheather it's in, then it will return
	 * object
	 *
	 * @param manufacturerId
	 * @throws VehicleManagementException
	 * @return its return Manufacturer object
	 */
	public Manufacturer getManufacturerById(int manufacturerId) throws VehicleManagementException;

	/**
	 * This method gets user given input wheather its in then it will remove object
	 *
	 * @param manufacturerId
	 * @throws VehicleManagementException
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
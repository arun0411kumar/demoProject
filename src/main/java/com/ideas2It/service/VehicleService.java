package com.ideas2It.service;

import java.util.Date;
import java.util.List;

import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;
import com.ideas2It.util.enumeration.Type;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;

/**
 * This interface handles manufacturer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface VehicleService {

	/**
	 * This method create two wheeler object and return it
	 * 
	 * @param brandname           - brand name from the controller
	 * @param fueltype            - fuel type from the controller
	 * @param mileage             - mileage from the controller
	 * @param colour              - colour from the controller
	 * @param dateOfManufacturing - date from the controller
	 * @param noOfStroke          - noOfStroke from the controller
	 * @param twoWheelerType      - two wheeler type from the controller
	 * @throws VehicleManagementException
	 * @return its return two wheeler object
	 */
	public TwoWheeler createTwoWheeler(Brand brandName, FuelType fuelType, byte mileage, Colours colour,
			Date dateOfManufacturing, byte noOfStroke, Type type, Manufacturer manufacturer, Dealer dealer)
			throws VehicleManagementException;

	/**
	 * This method was return two wheeler details
	 *
	 * @throws VehicleManagementException
	 * @return its return two wheeler details
	 */
	public List<TwoWheeler> getTwoWheelers() throws VehicleManagementException;

	/**
	 * This method gets user given input wheather it's in, then it will return
	 * object
	 *
	 * @param userVehicleCode
	 * @throws VehicleManagementException
	 * @return its return two wheeler object
	 */
	public TwoWheeler getTwoWheelerByCode(String vehicleCode) throws VehicleManagementException;

	/**
	 * This method gets user given input wheather its in then it will remove object
	 *
	 * @param userVehicleCode
	 * @throws VehicleManagementException
	 * @return if object removed it return true, else it return false
	 */
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException;

	/**
	 * Gets vehicle Code then change the specified value
	 *
	 * @param vehicleCode
	 * @param result
	 * @throws VehicleManagementException
	 * @return if value updated then it return true, else it return false
	 */
	public boolean updateVehicle(TwoWheeler twoWheeler) throws VehicleManagementException;

	/**
	 * this method get the vehicle details with the given range
	 *
	 * @param userChoiceOne
	 * @param userChoiceTwo
	 * @throws VehicleManagementException
	 * @return it return the given range
	 */
	public List<TwoWheeler> retriveVehiclesInRange(int choice, String start, String end) throws VehicleManagementException;

	/**
	 * It's gets value and return that specific searched list
	 *
	 * @param value - Gets value from the user
	 * @throws VehicleManagementException
	 * @return it's return two wheeler list based on value
	 */
	public List<TwoWheeler> searchTwoWheeler(String value) throws VehicleManagementException;

	/**
	 * this method get the vehicle details with the given Codes
	 *
	 * @param result
	 * @throws VehicleManagementException
	 * @return it return the given range
	 */
	public List<TwoWheeler> getTwoWheelerByCodes(String codes[]) throws VehicleManagementException;
}
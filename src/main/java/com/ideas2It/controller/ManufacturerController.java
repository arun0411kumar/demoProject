package com.ideas2It.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2It.model.Manufacturer;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.util.Validation;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The manufacturer controller class that implements an application that Simply
 * create, view, search, update and delete these operations passed service from
 * controller class
 *
 * @version 1.0
 * @author arunkumar
 */
public class ManufacturerController {
	private Scanner scanner = new Scanner(System.in);
	private ManufacturerService manufacturerService = new ManufacturerServiceImpl();

	/**
	 * This method shows manufacturer functionality based options
	 */
	public void displayManufacturerMenu() {
		String choice = Constants.EMPTY_STRING;
		String menu = Constants.EMPTY_STRING;
		do {
			try {
				choice = getString(Constants.MANUFACTURER_MENU);
				switch (choice) {
				case Constants.CHOICE_ONE:
					createManufacturer();
					break;

				case Constants.CHOICE_TWO:
					displayManufacturers();
					break;

				case Constants.CHOICE_THREE:
					getManufacturer();
					break;

				case Constants.CHOICE_FOUR:
					deleteManufacturer();
					break;

				case Constants.CHOICE_FIVE:
					updateManufacturer();
					break;

				default:
					throw new VehicleManagementException(Constants.INVALID);
				}
			} catch (VehicleManagementException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
			}
			if (true) {
				menu = getString(Constants.CONTINUE_TWOWHEELER_MENU);
			}
		} while (Constants.CHOICE_ONE.equals(menu));
	}

	/**
	 * This method was create the manufacturer details and print that object
	 */
	private void createManufacturer() {
		try {
			String name = getValidAlphabet("Enter manufacturer name");
			String company = getValidAlphabet("Enter manufacturer company");
			double investment = getValidInvestment();
			System.out.println(manufacturerService.createManufacturer(name, company, investment));
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method was print the manufacturer details
	 */
	private void displayManufacturers() {
		try {
			List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
			if (null == manufacturers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			}
			System.out.println(manufacturers);
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a manufacturerId from the user and display the manufacturer
	 */
	private void getManufacturer() {
		int manufacturerId = getInt(Constants.ENTER_MANUFACTURER_ID);
		try {
			Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
			VehicleManagementLogger.displayVehicleInfo(Constants.VEHICLE_DETAILS);
			print(manufacturer.toString());
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a manufacturerId from the user and delete the manufacturer
	 */
	private void deleteManufacturer() {
		int manufacturerId = getInt(Constants.ENTER_MANUFACTURER_ID);
		try {
			manufacturerService.deleteManufacturerById(manufacturerId);
			VehicleManagementLogger.displayVehicleInfo("Deleted successfully");
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method get manufacturer object If object is null then it will show
	 * invalid id Else it's calls updateManufacturerId
	 */
	private void updateManufacturer() {
		int manufacturerId = getInt(Constants.ENTER_MANUFACTURER_ID);
		try {
			Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
			VehicleManagementLogger.displayVehicleInfo("vehicle id verified successfully");
			if (updateManufacturer(manufacturer)) {
				VehicleManagementLogger.displayVehicleInfo("updated successfully");
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method get's user given id and get user given choice and update specific
	 * field
	 *
	 * @param manufacturer
	 * @return if updated it return true, else it return false.
	 */
	private boolean updateManufacturer(Manufacturer manufacturer) throws VehicleManagementException {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			print("how many details want to update");
			int option = scanner.nextInt();
			scanner.nextLine();
			if (option > 4) {
				print("Provide below four value");
				return updateManufacturer(manufacturer);
			}
			int choice = Constants.CHOICE_ZERO;
			String value[] = new String[option];

			while (option-- > 0) {
				stringBuffer.setLength(0);
				stringBuffer.append("Enter choice ");
				stringBuffer.append(choice + 1);
				value[choice] = getString(stringBuffer.toString());
				choice++;
			}
			option = value.length;
			choice = Constants.CHOICE_ZERO;

			while (option-- > 0) {
				manufacturer = replaceValue(value[choice], manufacturer);
				choice++;
			}
			return manufacturerService.updateManufacturer(manufacturer);
		} catch (Exception ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return false;
	}

	private Manufacturer replaceValue(String userChoice, Manufacturer manufacturer) {
		try {
			ManufacturerServiceImpl manufacturerService = new ManufacturerServiceImpl();
			switch (userChoice) {
			case Constants.CHOICE_ONE:
				manufacturer = manufacturerService.updateManufacturer(userChoice,
						getValidAlphabet("Enter manufacturer name"), manufacturer);
				break;

			case Constants.CHOICE_TWO:
				manufacturer = manufacturerService.updateManufacturer(userChoice,
						getValidAlphabet("Enter manufacturer company"), manufacturer);
				break;

			case Constants.CHOICE_THREE:
				manufacturer = manufacturerService.updateManufacturer(userChoice, Double.toString(getValidInvestment()),
						manufacturer);
				break;

			default:
				throw new VehicleManagementException(Constants.INVALID);
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return manufacturer;
	}

	/**
	 * It's get collection of alphabet.
	 *
	 * @param value
	 * @return given valid alphabet.
	 */
	private String getValidAlphabet(String value) {
		String alphabet = Constants.EMPTY_STRING;
		boolean result = false;
		do {
			alphabet = getString(value);
			result = Validation.isValidAlphabet(alphabet);
			if (!result) {
				VehicleManagementLogger.displayVehicleInfo(Constants.INVALID);
			}
		} while (!result);
		return alphabet;
	}

	/**
	 * It's Validate the user given investment
	 *
	 * @return valid investment.
	 */
	private double getValidInvestment() {
		double investment = 0;
		do {
			investment = getDouble("Enter investment");
		} while (investment < 59000);
		return investment;
	}

	/**
	 * Gets the message, print the message and get String value
	 *
	 * @param the message
	 * @return its return string value
	 */
	private String getString(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

	/**
	 * Gets message from the user and print that message
	 *
	 * @param message
	 */
	private void print(String message) {
		System.out.println(message);
	}

	/**
	 * Gets the message from the user and it's return int
	 *
	 * @param message - print the message
	 * @return its return int value
	 */
	private int getInt(String message) {
		boolean isNumber = false;
		int temporary = Constants.CHOICE_ZERO;
		do {
			try {
				System.out.println(message);
				temporary = scanner.nextInt();
				scanner.nextLine();
				isNumber = true;
			} catch (InputMismatchException | NumberFormatException ex) {
				print("Enter number only");
				scanner.next();
			}
		} while (!isNumber);
		return temporary;
	}

	/**
	 * Gets the message from the user and it's return byte
	 *
	 * @param message - print the message
	 * @return its return byte value
	 */
	private double getDouble(String message) {
		System.out.println(message);
		double temporary = scanner.nextDouble();
		scanner.nextLine();
		return temporary;
	}
}
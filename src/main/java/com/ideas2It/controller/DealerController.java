package com.ideas2It.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2It.model.Dealer;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.impl.DealerServiceImpl;
import com.ideas2It.util.Validation;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The dealer controller class that implements an application that Simply
 * create, view, search, update and delete these operations passed service from
 * controller class
 *
 * @version 1.0
 * @author arunkumar
 */
public class DealerController {
	private DealerService dealerService = new DealerServiceImpl();
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Show dealer options
	 *
	 * @param choice - the user choice for dealer
	 */
	public void displayDealerMenu() {
		String menu = Constants.EMPTY_STRING;
		do {
			print(Constants.DEALER_MENU);
			switch (scanner.nextLine()) {
			case Constants.CHOICE_ONE:
				createDealer();
				break;

			case Constants.CHOICE_TWO:
				displayDealers();
				break;

			case Constants.CHOICE_THREE:
				displayDealerById();
				break;

			case Constants.CHOICE_FOUR:
				deleteDealerById();
				break;

			case Constants.CHOICE_FIVE:
				updateDealer();
				break;
			}
			if (true) {
				menu = getString("Do you want to continue? 1");
			}
		} while (menu.equals(Constants.CHOICE_ONE));
	}

	/**
	 * Gets the attributes of dealer
	 */
	private void createDealer() {
		String company = getValidAlphabet("Enter your company");
		String city = getValidAlphabet("Enter your city");
		int stockAvailable = getValidStockAvailable();
		try {
			Dealer dealer = dealerService.createDealer(company, stockAvailable, city);
			print(dealer.toString());
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Display the dealers list
	 */
	private void displayDealers() {
		try {
			List<Dealer> dealers = dealerService.getDealers();
			if (null == dealers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			} else
				System.out.println(dealers);
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a dealerId from the user and display the dealer
	 */
	private void displayDealerById() {
		try {
			int dealerId = getInt(Constants.ENTER_DEALER_ID);
			Dealer dealer = dealerService.getDealerById(dealerId);
			System.out.println(dealer);
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a dealerId from the user and marking deleted the dealer
	 */
	private void deleteDealerById() {
		try {
			dealerService.deleteDealerById(getInt(Constants.ENTER_DEALER_ID));
			VehicleManagementLogger.displayVehicleInfo("Deleted successfully");
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method get manufacturer object If object is null then it will show
	 * invalid id Else it's calls updateManufacturerId
	 */
	private void updateDealer() {
		int dealerId = getInt(Constants.ENTER_DEALER_ID);
		try {
			Dealer dealer = dealerService.getDealerById(dealerId);
			VehicleManagementLogger.displayVehicleInfo("vehicle id verified successfully");
			if (updateDealer(dealer)) {
				VehicleManagementLogger.displayVehicleInfo("updated successfully");
			}
		} catch (VehicleManagementException vehicleManagementException) {
			VehicleManagementLogger.displayVehicleError(vehicleManagementException);
		}
	}

	/**
	 * This method get's user given id and get user given choice and update specific
	 * field
	 *
	 * @param manufacturer
	 * @return if updated it return true, else it return false.
	 */
	private boolean updateDealer(Dealer dealer) throws VehicleManagementException {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			print("how many details want to update");
			int option = scanner.nextInt();
			scanner.nextLine();
			if (option > 4) {
				print("Provide below four value");
				return updateDealer(dealer);
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
				dealer = replaceValue(value[choice], dealer);
				choice++;
			}
			return dealerService.updateDealer(dealer);
		} catch (Exception ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return false;

	}

	private Dealer replaceValue(String userChoice, Dealer dealer) {
		try {
			DealerServiceImpl dealerService = new DealerServiceImpl();
			switch (userChoice) {
			case Constants.CHOICE_ONE:
				dealer = dealerService.updateDealer(userChoice, getValidAlphabet("Enter dealer company"), dealer);
				break;

			case Constants.CHOICE_TWO:
				dealer = dealerService.updateDealer(userChoice, Integer.toString(getValidStockAvailable()), dealer);
				break;

			case Constants.CHOICE_THREE:
				dealer = dealerService.updateDealer(userChoice, getValidAlphabet("Enter manufacturer city"), dealer);
				break;

			default:
				throw new VehicleManagementException(Constants.INVALID);
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return dealer;
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
	 * It's get stock available in given dealer.
	 *
	 * @param value
	 * @return given valid stock.
	 */
	private int getValidStockAvailable() {
		int stockAvailable = Constants.CHOICE_ZERO;
		do {
			stockAvailable = getInt("Enter stock available");
		} while (stockAvailable <= 0);
		return stockAvailable;
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
	 * Gets message from the user and print that message
	 *
	 * @param message
	 */
	private void print(String message) {
		System.out.println(message);
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
}

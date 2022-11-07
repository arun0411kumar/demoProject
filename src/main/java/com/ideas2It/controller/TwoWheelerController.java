package com.ideas2It.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.VehicleService;
import com.ideas2It.service.impl.DealerServiceImpl;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.service.impl.VehicleServiceImpl;
import com.ideas2It.util.DateUtil;
import com.ideas2It.util.Validation;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;
import com.ideas2It.util.enumeration.Type;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The vehicle controller class that implements an application that Simply
 * create, view, search, update and delete these operations called from service
 * to controller class
 *
 * @version 1.0
 * @author arunkumar
 */
public class TwoWheelerController {
	private Scanner scanner = new Scanner(System.in);
	private VehicleService vehicleService = new VehicleServiceImpl();

	public void chooseVehicleOption() {
		String menu = Constants.EMPTY_STRING;
		do {
			System.out.println(Constants.VEHICLE_MENU);
			displayVehicleMenu(scanner.nextLine());
			menu = getString(Constants.CONTINUE_VEHICLE_MENU);
		} while (Constants.CHOICE_ONE.equals(menu.trim()));
	}

	/**
	 * Show vehicle options
	 *
	 * @param choice - the user choice for vehicle
	 */
	private void displayVehicleMenu(String choice) {
		String menu = Constants.EMPTY_STRING;
		String twoWheelerChoice;
		do {
			try {
				switch (choice) {
				case Constants.CHOICE_ONE:
					twoWheelerChoice = getString(Constants.TWOWHEELER_MENU);
					showTwoWheelerMenu(twoWheelerChoice.trim());
					break;

				default:
					throw new VehicleManagementException(Constants.INVALID);
				}
			} catch (VehicleManagementException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
			}
			if (Constants.CHOICE_ONE.equals(choice)) {
				menu = getString(Constants.CONTINUE_TWOWHEELER_MENU);
			}
		} while (Constants.CHOICE_ONE.equals(menu.trim()));
	}

	/**
	 * Show two wheeler menu
	 *
	 * @param twoWheelerChoice - the user choice for two wheeler
	 */
	public void showTwoWheelerMenu(String twoWheelerChoice) {
		try {
			switch (twoWheelerChoice) {
			case Constants.CHOICE_ONE:
				createTwoWheeler();
				break;

			case Constants.CHOICE_TWO:
				displayTwoWheelers();
				break;

			case Constants.CHOICE_THREE:
				displayTwoWheeler();
				break;

			case Constants.CHOICE_FOUR:
				updateTwoWheeler();
				break;

			case Constants.CHOICE_FIVE:
				deleteTwoWheeler();
				break;

			case Constants.CHOICE_SIX:
				searchTwoWheeler();
				break;

			case Constants.CHOICE_SEVEN:
				retriveVehiclesInRange();
				break;

			case Constants.CHOICE_EIGHT:
				displayTwoWheelerByIds();
				break;

			default:
				throw new VehicleManagementException(Constants.INVALID);
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets the attributes of two wheeler
	 */
	private void createTwoWheeler() {
		StringBuffer stringBuffer = new StringBuffer();
		print("---------------------------------");
		try {
			Brand brandName = getValidBrand();
			FuelType fuelType = getValidFuelType();
			byte mileage = getValidMileage();
			Colours colour = getValidColour();
			Date dateOfManufacturing = getDateOfManufacture();
			byte noOfStroke = getValidNoOfStroke();
			Type type = getValidType();
			Manufacturer manufacturer = getValidManufacturer();
			Dealer dealer = getValidDealer();

			VehicleManagementLogger.displayVehicleInfo("Vehicle is created successfully");
			stringBuffer.append("\nYour vehicle information ");
			stringBuffer.append(vehicleService.createTwoWheeler(brandName, fuelType, mileage, colour,
					dateOfManufacturing, noOfStroke, type, manufacturer, dealer));
			print(stringBuffer.toString());
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Display the two wheeler list
	 */
	private void displayTwoWheelers() {
		try {
			List<TwoWheeler> twoWheelers = vehicleService.getTwoWheelers();
			if (null == twoWheelers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			}
			VehicleManagementLogger.displayVehicleInfo(Constants.VEHICLE_DETAILS);
			for (TwoWheeler twoWheeler : twoWheelers) {
				print(twoWheeler.toString());
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a vehicleId from the user and display the two wheeler
	 */
	private void displayTwoWheeler() {
		String vehicleCode = getString(Constants.ENTER_VEHICLE_CODE);
		try {
			TwoWheeler twoWheeler = vehicleService.getTwoWheelerByCode(vehicleCode);
			VehicleManagementLogger.displayVehicleInfo(Constants.VEHICLE_DETAILS);
			print(twoWheeler.toString());
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Gets a vehicleCode from the user and delete the two wheeler
	 */
	private void deleteTwoWheeler() {
		String vehicleCode = getString(Constants.ENTER_VEHICLE_CODE);
		try {
			vehicleService.deleteTwoWheelerByCode(vehicleCode);
			VehicleManagementLogger.displayVehicleInfo("Deleted successfully");
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method get twowheeler object If object is null then it will show invalid
	 * id Else it's calls updateTwowheelerId
	 */
	private void updateTwoWheeler() {
		String vehicleCode = getString(Constants.ENTER_VEHICLE_CODE);
		try {
			TwoWheeler twoWheeler = vehicleService.getTwoWheelerByCode(vehicleCode);
			VehicleManagementLogger.displayVehicleInfo("vehicle id verified successfully");
			if (updateTwoWheeler(twoWheeler)) {
				VehicleManagementLogger.displayVehicleInfo("updated successfully");
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * Based on the user Input it's update that value
	 *
	 * @param vehicleId
	 * @return it's return boolean which is updated or not
	 */
	private boolean updateTwoWheeler(TwoWheeler twoWheeler) {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			showTwoWheelerOption();
			print("how many details want to update");
			int option = scanner.nextInt();
			scanner.nextLine();
			if (option > 7) {
				print("Provide below 7 value");
				return updateTwoWheeler(twoWheeler);
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
				twoWheeler = replaceValue(value[choice], twoWheeler);
				choice++;
			}
			return vehicleService.updateVehicle(twoWheeler);
		} catch (Exception ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return false;
	}

	/**
	 * This method display twowheeler list based on the user given value
	 */
	private void searchTwoWheeler() {
		try {
			List<TwoWheeler> twoWheelers = vehicleService.searchTwoWheeler(getString("Enter searching letter"));
			if (null == twoWheelers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			} else
			    System.out.println(twoWheelers);
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method display twowheeler list based on the user given range
	 */
	private void retriveVehiclesInRange() {
		try {
			int index = 0;
			print("Select below choice");
			int choice = getInt("\n1.mileage \n2.dateOfManufacture");
			if (0 > choice || 2 < choice) {
				throw new VehicleManagementException("try again");
			}
			String value[] = new String[2];
			do {
				switch (Integer.toString(choice)) {
				case Constants.CHOICE_ONE:
					value[index] = Byte.toString(getValidMileage());
					break;

				case Constants.CHOICE_TWO:
					value[index] = getDateOfManufacture().toString();
					break;
				}
			} while (index++ < 1);
			List<TwoWheeler> twoWheelers = vehicleService.retriveVehiclesInRange(choice, value[0], value[1]);
			if (null == twoWheelers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			} else
			    twoWheelers.forEach(twoWheeler -> print(twoWheeler.toString()));
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
	}

	/**
	 * This method display the value of given no of ids
	 */
	public void displayTwoWheelerByIds() {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			int index = Constants.CHOICE_ZERO;
			int choice = getInt("How many choice you want?");
			String codes[] = new String[choice];
			do {
				codes[index] = getString(stringBuffer.append("Code-").append(index + 1).toString());
				stringBuffer.setLength(0);
				index++;
			} while (--choice > 0);
			List<TwoWheeler> twoWheelers = vehicleService.getTwoWheelerByCodes(codes);
			if (null == twoWheelers) {
				VehicleManagementLogger.displayVehicleInfo("Your list empty");
			} else
			    twoWheelers.forEach(twoWheeler -> System.out.println(twoWheeler));
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
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
	 * Gets the message from the user and it's return byte
	 *
	 * @param message - print the message
	 * @return its return byte value
	 */
	private byte getByte(String message) {
		System.out.println(message);
		byte temporary = scanner.nextByte();
		scanner.nextLine();
		return temporary;
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
	 * It's the message, print the message and display the vehicle menu
	 *
	 * @param message
	 */
	private void showVehicleMenu() {
		print(Constants.TWOWHEELER_MENU);
	}

	/**
	 * Show two wheeler option for update two wheeler
	 */
	private void showTwoWheelerOption() {
		print(Constants.UPDATE_MENU);
	}
 
	/**
	 * Gets date then it's return lifetime of the two wheeler
	 *
	 * @param dateOfManufacturing
	 * @return its return two wheeler life time Ex: 01-05-2001 which is date data
	 *         type
	 */
	private int getTwoWheelerLifeTime(Date dateOfManufacturing) {
		int twoWheelerLifeTime = DateUtil.differenceBetweenYear(dateOfManufacturing, DateUtil.currentDate);
		return twoWheelerLifeTime;
	}

	/**
	 * This method returns brand name of the two wheeler
	 *
	 * @return its return brand name
	 */
	private Brand getValidBrand() {
		Brand brandName = null;
		do {
			brandName = Brand.getBrand(getString(Constants.ENTER_BRAND_NAME));
			if (null == brandName) {
				VehicleManagementLogger.displayVehicleInfo(Constants.INVALID);
			}
		} while (null == brandName);
		return brandName;
	}

	/**
	 * This method returns fuel type of the two wheeler
	 *
	 * @return its return fuel type
	 */
	private FuelType getValidFuelType() {
		FuelType fuelType;
		String userChoice = Constants.EMPTY_STRING;
		do {
			print(Constants.ENTER_FUEL_TYPE);
			userChoice = getString("\t 1.petrol \n\t 2.diesel \n\t 3.battery");
			fuelType = FuelType.getFuelType(userChoice);
			if (null == fuelType) {
				VehicleManagementLogger.displayVehicleInfo(Constants.INVALID);
			}
		} while (null == fuelType);
		return fuelType;
	}

	/**
	 * This method returns mileage of the two wheeler
	 *
	 * @return its return mileage
	 */
	private byte getValidMileage() {
		byte mileage = (byte) (Constants.CHOICE_ZERO);
		boolean found = false;
		do {
			try {
				mileage = getByte(Constants.ENTER_MILEAGE);
				if (10 <= mileage && 78 >= mileage) {
					found = true;
				} else {
					throw new VehicleManagementException("mileage must be within 10 km/l to 78 km/l");
				}
			} catch (VehicleManagementException | InputMismatchException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
				scanner.next();
			}
		} while (!found);
		return mileage;
	}

	/**
	 * This method returns colour of the two wheeler
	 *
	 * @return its return colour
	 */
	private Colours getValidColour() {
		Colours colour;
		String userChoice = Constants.EMPTY_STRING;
		do {
			print(Constants.ENTER_COLOUR);
			userChoice = getString("\t 1.blue \n\t 2.black \n\t 3.yellow" + "\n\t 4.red \n\t 5.white");
			colour = Colours.getColours(userChoice);
			if (null == colour) {
				VehicleManagementLogger.displayVehicleInfo(Constants.INVALID);
			}
		} while (null == colour);
		return colour;
	}

	/**
	 * This method returns manufacturing date of the two wheeler
	 *
	 * @return its return manufacturing date
	 */
	private Date getDateOfManufacture() {
		Date dateOfManufacture = null;
		do {
			try {
				dateOfManufacture = DateUtil
						.getDate(Validation.validateDate(getString(Constants.ENTER_DATE_OF_MANUFACTURING)));
				if (null == dateOfManufacture) {
					VehicleManagementLogger.displayVehicleInfo(Constants.INVALID);
				}
			} catch (VehicleManagementException | ParseException | NumberFormatException
					| ArrayIndexOutOfBoundsException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
			}
		} while (null == dateOfManufacture || (getTwoWheelerLifeTime(dateOfManufacture) < 0));
		return dateOfManufacture;
	}

	/**
	 * This method returns engine stroke of the two wheeler
	 *
	 * @return its return engine stroke
	 */
	private byte getValidNoOfStroke() {
		byte noOfStroke = (byte) (Constants.CHOICE_ZERO);
		boolean found = false;
		do {
			try {
				noOfStroke = getByte(Constants.ENTER_NO_OF_STROKE);
				if (2 == noOfStroke || 4 == noOfStroke) {
					found = true;
				} else {
					throw new VehicleManagementException("An engine is either a 2 stroke or a 4 stroke");
				}
			} catch (VehicleManagementException | InputMismatchException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
				scanner.next();
			}
		} while (!found);
		return noOfStroke;
	}

	/**
	 * This method returns twowheeler type of the two wheeler
	 *
	 * @return its return twowheeler type
	 */
	private Type getValidType() {
		Type type = null;
		do {
			type = Type.getType(getString(Constants.ENTER_TWOWHEELER_TYPE));
			if (null == type) {
				VehicleManagementLogger.displayVehicleInfo("Enter valid two wheeler type");
			}
		} while (null == type);
		return type;
	}

	/**
	 * This method returns manufacture object for two wheeler
	 *
	 * @return its return id
	 */
	private Manufacturer getValidManufacturer() {
		int choice = 0;
		int size = 0;
		Manufacturer manufacturer = null;
		do {
			try {
				List<Manufacturer> manufacturers = new ManufacturerServiceImpl().getManufacturers();
				print(manufacturers.toString());
				size = manufacturers.size();
				choice = getInt("\nChoose manufacturer");
				if (0 >= choice || choice > size) {
					VehicleManagementLogger.displayVehicleInfo("Choose valid manufacturer");
				}
				manufacturer = manufacturers.get(choice - 1);
			} catch (InputMismatchException | VehicleManagementException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
			}
		} while (0 >= choice || choice > size);
		return manufacturer;
	}

	/**
	 * This method returns dealer object for two wheeler
	 *
	 * @return its return id
	 */
	private Dealer getValidDealer() {
		int choice = Constants.CHOICE_ZERO;
		int size = 1;
		Dealer dealer = null;
		do {
			try {
				choice = getInt(Constants.DEALER_OPTION);
				if (1 == choice) {
					List<Dealer> dealers = new DealerServiceImpl().getDealers();
					print(dealers.toString());
					size = dealers.size();
					choice = getInt("\nChoose dealer");
					if (choice <= 0 || choice > size) {
						VehicleManagementLogger.displayVehicleInfo("Choose valid dealer");
					}
					dealer = dealers.get(choice - 1);
				} else {
					choice = 1;
				}
			} catch (InputMismatchException | VehicleManagementException ex) {
				VehicleManagementLogger.displayVehicleError(ex);
			}
		} while (choice <= 0 || choice > size);
		return dealer;
	}

	/**
	 * This method returns dealer id for two wheeler
	 *
	 * @return its return id
	 */
	private TwoWheeler replaceValue(String userChoice, TwoWheeler twoWheeler) {
		VehicleServiceImpl vehicleService = new VehicleServiceImpl();
		try {
			switch (userChoice) {
			case Constants.CHOICE_ONE:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler, getValidBrand().toString());
				break;

			case Constants.CHOICE_TWO:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler, getValidFuelType().toString());
				break;

			case Constants.CHOICE_THREE:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler,
						Byte.toString(getValidMileage()));
				break;

			case Constants.CHOICE_FOUR:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler, getValidColour().toString());
				break;

			case Constants.CHOICE_FIVE:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler, getDateOfManufacture());
				break;

			case Constants.CHOICE_SIX:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler,
						Byte.toString(getValidNoOfStroke()));
				break;

			case Constants.CHOICE_SEVEN:
				twoWheeler = vehicleService.updateTwoWheelerByCode(userChoice, twoWheeler, getValidType().toString());
				break;

			default:
				throw new VehicleManagementException(Constants.INVALID);
			}
		} catch (VehicleManagementException ex) {
			VehicleManagementLogger.displayVehicleError(ex);
		}
		return twoWheeler;
	}
}
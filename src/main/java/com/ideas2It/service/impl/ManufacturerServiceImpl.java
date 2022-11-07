package com.ideas2It.service.impl;

import java.util.List;

import com.ideas2It.dao.ManufacturerDao;
import com.ideas2It.dao.impl.ManufacturerDaoImpl;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs create, read, update, delete operation This class pass
 * the data into manufacturer dao
 *
 * @version 1.0
 * @author arunkumar
 */
public class ManufacturerServiceImpl implements ManufacturerService {
	private ManufacturerDao manufacturerDao = new ManufacturerDaoImpl();

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer createManufacturer(String name, String company, Double investment)
			throws VehicleManagementException {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(name);
		manufacturer.setCompany(company);
		manufacturer.setInvestment(investment);
		return manufacturerDao.insertManufacturer(manufacturer);
	}

	/**
	 * {@inheritdoc}
	 */
	public List<Manufacturer> getManufacturers() throws VehicleManagementException {
		List<Manufacturer> manufacturers = manufacturerDao.retriveManufacturers();
		return (manufacturers.isEmpty()) ? null : manufacturers;
	}

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer getManufacturerById(int manufacturerId) throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerDao.retriveManufacturerById(manufacturerId);
		if (null == manufacturer) {
			throw new VehicleManagementException("\nplease provide valid id."
					+ "\nif your not register manufacturer please create");
		}
		return manufacturer;
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteManufacturerById(int manufacturerId) throws VehicleManagementException {
		boolean result = manufacturerDao.deleteManufacturerById(manufacturerId);
		if (!result) {
			throw new VehicleManagementException(Constants.ALERT_MESSAGE + "\nplease provide valid id."
					+ "\nif your not register manufacturer please create");
		}
		return result;
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean updateManufacturer(Manufacturer manufacturer) throws VehicleManagementException {
		boolean found = manufacturerDao.updateManufacturer(manufacturer);
		if (!found) {
			throw new VehicleManagementException("\nplease provide valid id."
					+ "\nif your not register man please create");
		}
		return found;
	}
	
	public Manufacturer updateManufacturer(String userChoice, String updationValue, Manufacturer manufacturer) throws VehicleManagementException {
		switch (userChoice) {
		case Constants.CHOICE_ONE:
            manufacturer.setName(updationValue);
			break;

		case Constants.CHOICE_TWO:
			manufacturer.setCompany(updationValue);
			break;

		case Constants.CHOICE_THREE:
            manufacturer.setInvestment(Double.parseDouble(updationValue));
			break;

		default:
			throw new VehicleManagementException(Constants.INVALID);
		}
		return manufacturer;
	}
}
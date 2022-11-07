package com.ideas2It.service.impl;

import java.util.List;

import com.ideas2It.dao.DealerDao;
import com.ideas2It.dao.impl.DealerDaoImpl;
import com.ideas2It.model.Dealer;
import com.ideas2It.service.DealerService;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs create, read, update, delete operation This class pass
 * the data into dealer dao
 *
 * @version 1.0
 * @author arunkumar
 */
public class DealerServiceImpl implements DealerService {
	private DealerDao dealerDao = new DealerDaoImpl();

	public Dealer createDealer(String company, int stockAvailable, String city) throws VehicleManagementException {
		Dealer dealer = new Dealer();
		dealer.setCompany(company);
		dealer.setStockAvailable(stockAvailable);
		dealer.setCity(city);
		return dealerDao.insertDealers(dealer);
	}

	/**
	 * {@inheritdoc}
	 */
	public List<Dealer> getDealers() throws VehicleManagementException {
		List<Dealer> dealers = dealerDao.getDealers();
		return (dealers.isEmpty()) ? null : dealers;
	}

	/**
	 * {@inheritdoc}
	 */
	public Dealer getDealerById(int dealerId) throws VehicleManagementException {
		Dealer dealer = dealerDao.retriveDealerById(dealerId);
		if (null == dealer) {
			throw new VehicleManagementException(
					"\nplease provide valid id." + "\nif your not register dealer, please create");
		}
		return dealer;
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException {
		boolean result = dealerDao.deleteDealerById(dealerId);
		if (!result) {
			throw new VehicleManagementException(
					"\nplease provide valid id." + "\nif your not register dealer, please create");
		}
		return result;
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean updateDealer(Dealer dealer) throws VehicleManagementException {
		boolean found = dealerDao.updateDealer(dealer);
		if (!found) {
			throw new VehicleManagementException(
					"\nplease provide valid id." + "\nif your not register dealer, please create");
		}
		return found;
	}

	/**
	 * {@inheritdoc}
	 */
	public Dealer updateDealer(String userChoice, String updationValue, Dealer dealer)
			throws VehicleManagementException {
		switch (userChoice) {
		case Constants.CHOICE_ONE:
			dealer.setCompany(updationValue);
			break;

		case Constants.CHOICE_TWO:
			dealer.setStockAvailable(Integer.parseInt(updationValue));
			break;

		case Constants.CHOICE_THREE:
			dealer.setCity(updationValue);
			break;

		default:
			throw new VehicleManagementException(Constants.INVALID);
		}
		return dealer;
	}
}
package com.ideas2It.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2It.dao.DealerDao;
import com.ideas2It.model.Dealer;
import com.ideas2It.util.connection.DataBaseConnection;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs insert, retrive, update, delete operation This class
 * store data into database in dealers table
 *
 * @version 1.0
 * @author arunkumar
 */
public class DealerDaoImpl implements DealerDao {
	private SessionFactory factory = DataBaseConnection.getSessionFactory();
	private Session session = null;

	/**
	 * {@inheritdoc}
	 */
	public Dealer insertDealers(Dealer dealer) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			int id = (int) session.save(dealer);
			transaction.commit();
			return session.get(Dealer.class, id);
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(
					new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE).toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public List<Dealer> getDealers() throws VehicleManagementException {
		try {
			session = factory.openSession();
			Query<Dealer> query = session.createQuery("from Dealer where isDeleted = 0", Dealer.class); 
			return query.getResultList();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(
					new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE).toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public Dealer retriveDealerById(int dealerId) throws VehicleManagementException {
		try {
			session = factory.openSession();
			return session.get(Dealer.class, dealerId);
		} catch (HibernateException ex) {
			throw new VehicleManagementException(
					new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE).toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException {
		StringBuffer hql = new StringBuffer();
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			hql.append("update Dealer set isDeleted = 1 ").append("where id = :dealerId and isDeleted = 0");
			Query query = session.createQuery(hql.toString());
			query.setParameter("dealerId", dealerId);
			int status = query.executeUpdate();
			transaction.commit();
			return 0 < status;
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(
					new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE).toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean updateDealer(Dealer dealer) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(dealer);
			transaction.commit();
			return session.contains(dealer);
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(
					new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE).toString());
		} finally {
			session.close();
		}
	}
}
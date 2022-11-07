package com.ideas2It.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2It.dao.ManufacturerDao;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.util.connection.DataBaseConnection;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs insert, retrieve, update, delete operation This class
 * store data into database in manufacturers table
 *
 * @version 1.0
 * @author arunkumar
 */
public class ManufacturerDaoImpl implements ManufacturerDao {
	private SessionFactory factory = DataBaseConnection.getSessionFactory();
	private Session session = null;

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer insertManufacturer(Manufacturer manufacturer) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			int id = (int) session.save(manufacturer);
			transaction.commit();
			return session.get(Manufacturer.class, id);
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to insert").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public List<Manufacturer> retriveManufacturers() throws VehicleManagementException {
		try {
			session = factory.openSession();
			Query<Manufacturer> query = session.createQuery("from Manufacturer where isDeleted = 0",
					Manufacturer.class);
			return query.getResultList();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public Manufacturer retriveManufacturerById(int manufacturerId) throws VehicleManagementException {
		try {
			session = factory.openSession();
			return session.get(Manufacturer.class, manufacturerId);
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive by Id").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteManufacturerById(int manufacturerId) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			StringBuffer hql = new StringBuffer();
			hql.append("update Manufacturer set isDeleted = 1 ").append("where id = :manufacturerId and isDeleted = 0");
			Query query = session.createQuery(hql.toString());
			query.setParameter("manufacturerId", manufacturerId);
			int status = query.executeUpdate();
			transaction.commit();
			System.out.println(status);
			return 0 < status;
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to delete").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean updateManufacturer(Manufacturer manufacturer) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(manufacturer);
			transaction.commit();
			return session.contains(manufacturer);
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to update").toString());
		} finally {
			session.close();
		}
	}
}
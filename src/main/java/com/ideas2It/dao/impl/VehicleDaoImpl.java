package com.ideas2It.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ideas2It.dao.VehicleDao;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.util.connection.DataBaseConnection;
import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * This class performs insert, retrieve, update, delete operation This class
 * store data into database in twowheeler table
 *
 * @version 1.0
 * @author arunkumar
 */
public class VehicleDaoImpl implements VehicleDao {
	private SessionFactory factory = DataBaseConnection.getSessionFactory();
	private Session session = null;

	/**
	 * {@inheritdoc}
	 */
	public TwoWheeler insertTwoWheeler(TwoWheeler twoWheeler) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			int id = (int) session.save(twoWheeler);
			twoWheeler.setId(id);
			transaction.commit();
			return twoWheeler;
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
	public List<TwoWheeler> retriveTwoWheelers() throws VehicleManagementException {
		try {
			String hql = "from TwoWheeler where isDeleted = 0";
			session = factory.openSession();
			Query<TwoWheeler> query = session.createQuery(hql, TwoWheeler.class);
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
	public TwoWheeler retriveTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = new TwoWheeler();
		try {
			session = factory.openSession();
			StringBuffer hql = new StringBuffer();
			hql.append("from TwoWheeler ").append("where vehicleCode = :vehicleCode and isDeleted = 0");
			Query<TwoWheeler> query = session.createQuery(hql.toString());
			query.setParameter("vehicleCode", vehicleCode);
			return query.getSingleResult();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive by code").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			StringBuffer hql = new StringBuffer();
			hql.append("update TwoWheeler set isDeleted = 1 ")
					.append("where vehicleCode = :vehicleCode and isDeleted = 0");
			Query<TwoWheeler> query = session.createQuery(hql.toString());
			query.setParameter("vehicleCode", vehicleCode);
			int status = query.executeUpdate();
			transaction.commit();
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
	public boolean updateVehicle(TwoWheeler twoWheeler) throws VehicleManagementException {
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(twoWheeler);
			transaction.commit();
			return session.contains(twoWheeler);
		} catch (HibernateException ex) {
			transaction.rollback();
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to update").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public List<TwoWheeler> retriveVehiclesInRange(int choice, String start, String end)
			throws VehicleManagementException {
		try {
			session = factory.openSession();
			StringBuffer hql = new StringBuffer();
			hql.append(" from TwoWheeler where ((mileage between '").append(start).append("' and '")
					.append(end).append("') or (dateOfManufacture between '").append(start)
					.append("' and '").append(end).append("')) and isDeleted = 0");
			Query<TwoWheeler> query = session.createQuery(hql.toString(), TwoWheeler.class);
			return query.getResultList();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive in range").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public List<TwoWheeler> searchVehicle(String value) throws VehicleManagementException {
		StringBuffer hql = new StringBuffer();
		value = hql.append("%").append(value).append("%").toString();
		try {
			session = factory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TwoWheeler> query = builder.createQuery(TwoWheeler.class);
			Root<TwoWheeler> root = query.from(TwoWheeler.class);
			
			  query.select(root).where(builder.or(builder.like(root.get("brandName").as(String.class), value),
					  builder.like(root.get("vehicleCode").as(String.class), value),
					  builder.like(root.get("fuelType").as(String.class), value),
					  builder.like(root.get("mileage").as(String.class), value),
					  builder.like(root.get("colour").as(String.class), value),
					  builder.like(root.get("dateOfManufacture").as(String.class), value),
					  builder.like(root.get("noOfStroke").as(String.class), value),
					  builder.like(root.get("type"). as(String.class), value)),
					  builder.and(builder.equal(root.get("isDeleted"), false))
			  );
			 
			return session.createQuery(query).getResultList();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to search").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public List<TwoWheeler> retriveTwoWheelerByCodes(String codes[]) throws VehicleManagementException {
		try {
			List<String> twoWheelerCodes = Arrays.asList(codes);
			session = factory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TwoWheeler> query = builder.createQuery(TwoWheeler.class);
			Root<TwoWheeler> root = query.from(TwoWheeler.class);
			query.select(root).where(root.get("vehicleCode").in(twoWheelerCodes), 
			        builder.equal(root.get("isDeleted"), false));
			return session.createQuery(query).getResultList();
		} catch (HibernateException ex) {
			throw new VehicleManagementException(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive by number of codes").toString());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 */
	public int getLastId() {
		long id = Constants.CHOICE_ZERO;
		try {
			session = factory.openSession();
			Query query = session.createQuery("select count(*) from TwoWheeler");
			id = (long) query.uniqueResult();
			System.out.print(id);
		} catch (HibernateException ex) {
			VehicleManagementLogger.displayVehicleInfo(new StringBuffer().append(ex).append(Constants.ALERT_MESSAGE)
					.append(" while trying to retrive by number of count").toString());
		} finally {
			session.close();
		}
		return (int) id;
	}
}
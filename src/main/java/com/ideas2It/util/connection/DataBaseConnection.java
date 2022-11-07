package com.ideas2It.util.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2It.model.BaseModel;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.model.Vehicle;

/*
 * this class give the connection object only once 
 * because it is a singleton class  
 *
 * @version 1.0
 * @author arunkumar 
 */
public class DataBaseConnection {
    private static Configuration configuration;
    private static SessionFactory factory;
    private static DataBaseConnection dataBaseConnection;   
    
    private DataBaseConnection() {
        configuration = new Configuration().configure("hibernate.cfg.xml")
        		.addAnnotatedClass(BaseModel.class)
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(TwoWheeler.class)
                .addAnnotatedClass(Manufacturer.class)
                .addAnnotatedClass(Dealer.class); 
        factory = configuration.buildSessionFactory();
    }

    /*
     * This method return session factory object
     *
     * @return factory
     */
    public static SessionFactory getSessionFactory() {
        if (null == factory) {
            dataBaseConnection = new DataBaseConnection();
        }
        return factory;
    } 
}
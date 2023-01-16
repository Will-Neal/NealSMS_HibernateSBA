package jpa.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author willw
 * A utility class that builds the session factory, exectures the confuration process, opens a session and returns that session.
 * Primarily for use in the StudentService and CourseService classes. 
 */
public class HibernateConnection {

	/**
	 * Returns a session to be using in the service methods
	 * @return Session object
	 */
	public static Session startConnection() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session sesh = factory.openSession();
		
		return sesh;
	}
	
	
}

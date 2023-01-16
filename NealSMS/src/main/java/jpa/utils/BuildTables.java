package jpa.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

/**
 * @author willw
 * The build table class has one method, createUpdateTableIfNotExist.
 * This method simply opens and closes a session so that hibernate will create or update tables if the table does not exist
 * or if the tables in the database do not match the entities in the program. 
 */
public class BuildTables {

	/**
	 * Establishes connection and starts session to create or update table if necessary and then closes the session.
	 */
	public static void createUpdateTableIfNotExist() {
		Session session = HibernateConnection.startConnection();
		
		session.close();
	}
}

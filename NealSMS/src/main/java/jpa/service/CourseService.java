package jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.utils.HibernateConnection;

/**
 * @author willw
 * Implements the CourseDAO interface and overrides the getAllCourses method.
 * Contains the logic for accessing course data.
 */
public class CourseService implements CourseDAO{

	/**
	 *Connect to Database and returns all Courses in a list of Course objects.
	 *Also prints to console for user.
	 */
	@Override
	public List<Course> getAllCourses() {
		//connection
		Session session = HibernateConnection.startConnection();
		
		//query
		String queryString = "FROM Course";
		TypedQuery<Course> query = session.createQuery(queryString, Course.class);
		List<Course> result = query.getResultList();
		
		//Print out header then loop through list and print query results, formatted
		System.out.format("%-3s %2s %-28s %-2s %-20s%n","ID", " ", "COURSE"," ","INSTRUCTOR");		
		for (Course c: result) {
			System.out.format("%-3s %2s %-28s %-2s %-20s%n", c.getcId(), "|", c.getcName(), "|", c.getcInstructorName());
		}

		session.close();
		return result;	
	}


}

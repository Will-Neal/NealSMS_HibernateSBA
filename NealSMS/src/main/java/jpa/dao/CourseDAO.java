package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;

/**
 * @author willw
 *CourseDAO contains the methods used to access the Course entity table 
 *from the database. It contains getAllCourse() which returns of List of Course objects.
 */
public interface CourseDAO {
	
	public List<Course> getAllCourses();

}

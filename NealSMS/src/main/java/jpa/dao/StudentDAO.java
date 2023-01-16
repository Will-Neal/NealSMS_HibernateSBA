package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

/**
 * @author willw
 *StudentDAO sets the methods used to access Student data in the database.
 *Its methods are getAllStudents which returns a list of Student objects,
 *getStudentEmail which takes in an email and returns a Student object,
 *validate students which takes in an email and string and returns a boolean based on the validation,
 *registerStudentsToCourse which takes in an email and courseId and has no return because it is a maniulation of the data layer,
 *and GetStudentCourse which take in an email and returns a List of Course objects associated with the email. 
 *
 */
public interface StudentDAO {

	List<Student> getAllStudents();
	Student getStudentByEmail(String email);
	boolean validateStudent(String email, String password);
	void registerStudentToCourse(String email, int courseId);
	List<Course> getStudentCourses(String email);
	
}

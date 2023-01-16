package jpa.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class getAllCoursesTest {

	@Test
	public void testGetAllCourses() {
		//Given
		CourseDAO courseMethods = new CourseService();
		Course expected = new Course();
		expected.setcId(1);
		expected.setcInstructorName("Anderea Scamaden");
		expected.setcName("English");
		
		//When
		List<Course> courseList = courseMethods.getAllCourses();
		Course actual = courseList.get(0);
		
		//Then
		assertEquals(expected, actual);
	}
	
	@Test public void testGetAllCoursesNegative() {
		//Given
		CourseDAO courseMethods = new CourseService();
		Course expected = new Course();
		expected.setcId(1);
		expected.setcInstructorName("John Doe");
		expected.setcName("Psychology 101");
		
		//When
		List<Course> courseList = courseMethods.getAllCourses();
		Course actual = courseList.get(0);
		
		//Then
		assertNotEquals(expected, actual);
	}

}

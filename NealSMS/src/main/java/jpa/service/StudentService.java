package jpa.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.utils.HibernateConnection;

/**
 * @author willw
 * An implementation of the studentDAO interface.
 * It contains the logic for accessing and manipulating data in the Student entity table.
 * Overrides the getAllStudents, getStudentByEmail, validateStudent, registerStudentToCourse, and getStudentCourses methods.
 */
public class StudentService implements StudentDAO{

	/**
	 *Gets all students from Database and returns a list of those Student objects.
	 *Prints to console.
	 */
	@Override
	public List<Student> getAllStudents() {
		//connection
		Session session = HibernateConnection.startConnection();
		
		//query
		String getQuery = "FROM Student";
		TypedQuery<Student> query = session.createQuery(getQuery, Student.class);
		List<Student> results = query.getResultList();
		
		//print header then loop through list and print student info
		System.out.println("Name Email Password");
		for(Student s : results) {
			System.out.println(s.getsName() + " " + s.getsEmail() + " " + s.getsPassword());
		}
		
		session.close();
		return results;
	}

	/**
	 *Takes in an email and passes that through to a query to get a Student with the given email.
	 *Returns that Student object.
	 *
	 *@param email  Unique email used to identify student in the database
	 */
	@Override
	public Student getStudentByEmail(String email) {
		//Connection
		Session session = HibernateConnection.startConnection();
		//Query
		String getQuery = "FROM Student WHERE email = :email";
		TypedQuery<Student> query = session.createQuery(getQuery, Student.class);
		query.setParameter("email", email);
		Student student = query.getSingleResult();
		session.close();
		return student;
	}

	/**
	 *Takes in the users email and password then checks it against the database.
	 *Uses the email to get the Student object from the database. 
	 *Then checks the given password against the password stored in the database
	 *for that user. Returns true if credentials match the database otherwise
	 *returns false.
	 *
	 *@param email  unique email used to identify student
	 *@param password  password given to be checked against password in db
	 */
	@Override
	public boolean validateStudent(String email, String password) {
		//Connection
		Session session = HibernateConnection.startConnection();
		//Query
		String checkEmail = "FROM Student WHERE email = :email";
		TypedQuery<Student> emailQuery = session.createQuery(checkEmail, Student.class);
		emailQuery.setParameter("email", email);
		//Handling if there is no email found
		try {
			Student emailResult = emailQuery.getSingleResult();
			String correctPassword = emailResult.getsPassword();
			
			//Check Password in DB against password given by user
			if(correctPassword.equals(password)) {
				System.out.println("Successfully Logged In");
				session.close();
				return true;
				
			} else {
				System.out.println("Invalid Password");
				session.close();
				return false;
			}
			
		//Catch exception if no user with that email and reprompt	
		} catch(NoResultException e) {
			System.out.println("Please Enter Valid Email");
			session.close();
			return false;
		}
		
	}

	/**
	 *Takes in the unique email and unique course Id to save the pairing to the database
	 *in the student_course join table. 
	 *
	 *@param email   Unique email for Student
	 *@param courseId   Unique courseId associated with each course
	 */
	@Override
	public void registerStudentToCourse(String email, int courseId) {
		//Connection
		Session session = HibernateConnection.startConnection();
		
		//Query
		String getQuery = "FROM Student WHERE email = :email";
		TypedQuery<Student> query = session.createQuery(getQuery, Student.class);
		query.setParameter("email", email);
		Student student = query.getSingleResult();
		
		//Store Students courses in a List
		List<Course> courseList = student.getsCourses();		
		
		//Start Transaction
		Transaction tx = session.beginTransaction();
		
		//Create Course object and set Id to the Id of chosen course
		Course courseRegistration = new Course();
		courseRegistration.setcId(courseId);
		
		//Add to list of Students courses
		courseList.add(courseRegistration);
		
		//Set updated course list to Student object
		student.setsCourses(courseList);
		
		session.save(student);
		tx.commit();
		System.out.println("Registration Successful");
		session.close();
	}

	/**
	 *Gets all courses associated with a given student. Student is identified
	 *with the unique email stored in the database. Returns a list of associated courses
	 *for use in the program and prints them to the console 
	 */
	@Override
	public List<Course> getStudentCourses(String email) {
		//Connection
		Session session = HibernateConnection.startConnection();
		
		//Query
		String getCourses = "SELECT course.id, course.instructor, course.name FROM course INNER JOIN student_course ON course.id = student_course.sCourses_id WHERE student_course.Student_email = :email ORDER BY course.id";
		@SuppressWarnings("unchecked")
		NativeQuery<Course> sqlQuery = session.createNativeQuery(getCourses);
		sqlQuery.addEntity(Course.class);
		sqlQuery.setParameter("email", email);
		List<Course> results = sqlQuery.list();
	
		//Print header and loop through results
		System.out.println("My Classes: \n");
		System.out.format("%-3s %2s %-28s %-2s %-20s%n","ID", " ", "COURSE"," ","INSTRUCTOR");
		for (int i=0; i<results.size(); i++) {
			Course c = results.get(i);
			System.out.format("%-3s %2s %-28s %-2s %-20s%n", c.getcId(), "|", c.getcName(), "|", c.getcInstructorName());
		}
		
		System.out.println();
		session.close();
		return results;	
	}
}

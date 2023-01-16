package jpa.SMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
import jpa.utils.BuildTables;
import jpa.utils.HibernateConnection;

/**
 * @author willw
 * Contains logic for running the program, navigating the menu and calling the methods of the studentService and courseService classes. 
 * Contains the methods startApp, login and mainMenu. All return void because they are primarly concerned with user interacction with the program. 
 */
public class SMSRunner {
	
	/**
	 *Starts the program logic. 
	 *Establishes a connection with Session Factory Util to create and update tables, if needed.
	 *Gives user option to login or exit. If exit, program ends, if login invokes login method.  
	 */
	public static void startApp() {
		//Establishes a connection to create and update tables if necessary
		BuildTables.createUpdateTableIfNotExist();
		
		//Prompt the user to login
		String selection;
    	Scanner input = new Scanner(System.in);
    	System.out.println("Select Option: ");
    	System.out.println("1. Login");
    	System.out.println("2. Quit");
    	
    	//If 1 login, If 2 exit
    	selection = input.nextLine();
    	switch(selection) {
    		case "1": 
    			System.out.println("Selected Login");
    			login(input);
    			break;
    		case "2": 
    			System.out.println("Goodbye");
    			break;
    		default:
    			System.out.println("Please Choose a Valid Option");
    			startApp();		
    	}
    }
	
    /**
     * Contains the user login menu logic. Takes in the scanner for continuity
     * and prompts for email and password. Gives user to exit by entering 0 for 
     * either option.
     * 
     * @param scan  The scanner from the startApp method
     */
    public static void login(Scanner scan) {
    	//Instantiating Student Service for access to methods
    	StudentDAO studentMethods = new StudentService();
    	
    	//Prompt user for email and password. Allow user to exit by entering 0.
    	System.out.println("Please Enter Your Student Email: (Or 0 to exit) ");
    	String userEmail = scan.next();
    	if(userEmail.equals("0")) {
    		System.out.println("Goodbye!");
    		return;
    	}
    	System.out.println("Please Enter Your Password: (Or 0 to exit)");
    	String userPassword = scan.next();
    	if(userPassword.equals("0")) {
    		System.out.println("Goodbye!");
    		return;
    	}
    	//Call validation method and store in variable, If true, invoke main menu
    	boolean loggedIn = studentMethods.validateStudent(userEmail, userPassword);
    	if(loggedIn) {
    		Student activeStudent = studentMethods.getStudentByEmail(userEmail);
    		mainMenu(activeStudent, scan);
    	} else {
    		login(scan);
    	}
    	
    }
    
    /**
     * Takes in the validated student and the scanner object.
     * Gets email from the validated to student and uses that to ensure 
     * courses are retrieved for the correct student. 
     * Retrieves the students courses and adds to an ArrayList so that the users selection
     * can be checked against the list and make sure they do not register twice. 
     * Contains the main menu logic that allows the user to Register or logoout
     * and if they choose register choose a number from a list of courses. 
     * 
     * @param student  The Student object that was validated in the login method.
     * @param scan  The scanner object used throughout the program.
     * 
     */
    public static void mainMenu(Student student, Scanner scan) {
    	//Get email from logged in user to get correct courses
    	String studentemail = student.getsEmail();
    	
    	//Instantiate CourseService and StudentService for access to methods
    	CourseDAO courseMethods = new CourseService();
    	StudentDAO studentMethods = new StudentService();
    	
    	//Store the courses associated with student in list and print
    	List<Course> courses = studentMethods.getStudentCourses(studentemail);    	
    	
    	//Create a list of id's to prevent duplicates
    	List<String> courseIds = new ArrayList<String>();
    	
    	//If there are courses add the id to the courseId list
    	if(courses != null) {
    		for(Course c: courses) {
        		courseIds.add(String.valueOf(c.getcId()));
        	}
    	}
    	
    	//Main menu options
    	System.out.println("Main Menu");
    	System.out.println("Select an Option:");
    	System.out.println("1. Register to Class");
    	System.out.println("2. Logout");
    	String menuselection = scan.next();
    	
    	switch(menuselection) {
    		case "1": 
    			System.out.println("Selected Register to Class");
    			courseMethods.getAllCourses();    			
    			System.out.println("Enter id of course to register or 0 to cancel: ");
    			String registerSelection = scan.next();
    			if(registerSelection.equals("0")) {
    				System.out.println("Goodbye!");
    				return;
    			}
    			
    			//Check that student is not already registered. If not proceed.
    			try {
    				if(courseIds.contains(registerSelection)) {
        				System.out.println("Already Registered, Cannot Register Again");
        				mainMenu(student, scan);
        				return;
        			} else if(Integer.parseInt(registerSelection) > 0 && Integer.parseInt(registerSelection)<11) {
        				int classId = Integer.parseInt(registerSelection);
            			studentMethods.registerStudentToCourse(studentemail, classId);
            			studentMethods.getStudentCourses(studentemail);
            			System.out.println("Class Registration Successful!");
            			System.out.println("You have been signed out");
        			} else {
        				System.out.println("Invalid selection, Try Again");
        				mainMenu(student, scan);
        				return;
        			}
    			} catch(NumberFormatException e) {
    				System.out.println("Must Select a Number");
    				mainMenu(student, scan);
    				return;
    			}	
    			
    			break;
    		case "2": 
    			System.out.println("Goodbye!");
    			break;
    		default:
    			System.out.println("Enter a Valid Option");
    			mainMenu(student, scan);
    			break;
    	}
    	
    }
    
}

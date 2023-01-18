# NealSMS

  ## Description

   The NealSMS is a command line application built with Java using Maven, Hibernate and MySQL. The application allows students who have been saved in the database to login with an email and password in order to see their registered classes or register for new classes. The user is able to exit at any time by following the prompts and is prevented from registering for the same class multiple times.

  ## Table of Contents 

  - [Installation](#installation)
  - [Usage](#usage)
  - [License](#license)

  ## Installation
  
Download the entire project. 

If you get it as a zip file, you must unzip it and import the NealSMS contained within the main directory (NealSMSZip) in Eclipse or your IDE of choice. 

If you clone it from github you must import the NealSMS contained within the main directory (NealSMS_HiberanteSBA) into eclipse or your IDE of choice.

Once you have the project, update the Hibernate configuration file with the desired url and database name as well as the valid username and login for your database. You only need to change the database name if you want it to have a different name on your local host or server. You MUST change the username and password fields to your MySQL login. Those Lines look like:

\<property name="connection.url">jdbc:mysql://localhost:3306/smsdb?createDatabaseIfNotExist=true</property\>

\<property name="connection.username">YOUR MYSQL USERNAME HERE</property\>

\<property name="connection.password">YOUR MYSQL PASSWORD HERE</property\>`

Run the program once to create the tables or update the tables if necessary. You should run the program by going to src/main/java then opening the jpa.SMS package, navigating to App.java, right clicking and selecting run as Java Application. 

Student and course data is available in the SQL Scripts directory within the NealSMS directory. Once you have run the program once and created the tables, run these scripts to insert the data into the tables in MySQL workbench. 

## Usage

  Once the database has been linked, tables created, and data seeded, simply run the application from the App.java file contained in src/main/java within the jpa.SMS package. This will start the application and you will be good to go. The application will look like: 

  ![SMS Screenshot](https://github.com/Will-Neal/NealSMS_HibernateSBA/blob/main/images/SMS_SS.png?raw=true)
  
  ## License

  ![License Badge](https://img.shields.io/badge/license-MIT-orange?style=plastic=appveyor?raw=true)
  <br>
  
  [Click here for more information regarding the above license](https://opensource.org/licenses/MIT)
    
  ---
    
  ## Technologies

  - Java
  - Maven 
  - MySQL
  - Hibernate
  - JUnit

  ## Tests

  The getAllCourses() method has been tested in both a positive and negative manner, using JUnit. This test is available under src/test/java in the jpa.service package. 

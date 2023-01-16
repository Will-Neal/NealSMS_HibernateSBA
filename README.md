# NealSMS

  ## Description

   The NealSMS is a command line application built with Java using Maven, Hibernate and MySQL. The application allows students who have been saved in the database to login with an email and password in order to see their registered classes or register for new classes. The user is able to exit at any time by following the prompts and is prevented from registering for the same class multiple times.

  ## Table of Contents 

  - [Installation](#installation)
  - [Usage](#usage)
  - [License](#license)

  ## Installation
  
  Download the entire project. If you get it as a zip file, you must unzip it and import the NealSMS folder contained within the main directory. Once you have the project, update the Hibernate configuration file with the desired url and database name as well as the valid username and login for your database. Running the program once, even just to immediately log out will create the tables or update the tables if necessary. Premade student and course data is available in the SQL Scripts folder and can be run straight in MySQL workbench or Users and Courses must be manually seeded with the desired information. 

## Usage
  Once the database has been linked, tables created, and data seeded, simply run the application from the App.java file contained in src/main/java within the jpa.SMS package. This will start the application and you will be good to go. 

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

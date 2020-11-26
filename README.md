**Main idea**

A demo version of a cinema ticket purchasing system. RESTful API system for selling tickets online.
_____

**Project Structure**

The project has an N-tier structure:
* database layer;
* DAO layer for interaction with the database;
* service layer with business logic
* controller layer for HTTP request processing.
____
**Client capabilities:**
* registration, logging in and out;
* looking through a list of all films and cinema halls;
* checking all available films on a particular date;
* adding a ticket to the shopping cart, and completing the order;
* looking through order history.
___

**Admin capabilities:**
* logging in and out;
* managing the movies, cinema halls, and movie sessions lists;
* finding users by email;
___
**Technologies Used:**
* Java 11
* Spring Core, Spring MVC, Spring Security
* Hibernate
* MySQL database
* Apache Tomcat
* Maven Checkstyle Plugin
* Lombok
* Log4j
_____
**To run the project, you need:**
* clone the project into your local folder and open the project in an IDE;
* configure Tomcat: deployment: war_exploded, context address: "/";
* copy the script from init_db.sql to the MySQL Workbench;
* insert your MySQL username and login in the db.properties file;
* install and activate the Lombok plugin for your IDE;

**For authorization as an ADMIN:**
* login: admin@gmail.com
* password: 12345

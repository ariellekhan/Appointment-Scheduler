# COMP3613 - DoctorAppointmentScheduler Application

### Application Credentials
 Creating a user
  1. To create a doctor user 
  
  Staff code is: doctor123 
  
  2. To create an Administrative-Staff User
  
  Staff code is: staff123 
  
 To change these, access the file CreateAccountPresenter.java
 
 location: DoctorAppointmentScheduler/src/doctorappointmentscheduler/client/presenter/CreateAccountPresenter.java

MySQL credentials

username = root

password = admin123

hostname = localhost

portNumber = 3306

##### Required: 
change these to match your machine's configuration; Access the file MySQLConnection.java

location: DoctorAppointmentScheduler/src/doctorappointmentscheduler/server/MySQLConnection.java 

### Requirements to run application:
  1. Eclipse
  2. Google Web Toolkit plugin
  3. mySQL database

### To run application:
  1. Set up mySQL database -- run doctorbaseScript.sql
  2. Import project from github to Eclipse.
  3. Compile using GWT (Google Web Toolkit) compiler.
  4. Run as GWT Development Mode with Jetty. 
  5. Navigate to Web page.


#### Google Web Toolkit Plugin Setup
link for additional help: http://gwt-plugins.github.io/documentation/gwt-eclipse-plugin/Download.html
  1. Open Eclipse -> Help -> Eclipse Marketplace
  2. Search using Find: GWT
  3. GWT Eclipse Plugin 3.0.0 -> Install -> (select all boxes) -> confirm

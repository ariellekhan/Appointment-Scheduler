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
  
#### MySQL WorkBench Setup
link to download: https://dev.mysql.com/downloads/installer/
  1. Navigate to download page using link above -> Select OS -> Download msi -> No thanks, just start my download
  2. Run download setup -> set up wizard opens -> accept license -> Choose Developer Default Setup Type -> Check requirements and download any missing ones -> Install -> Configure
  3. Open MySQL Workbench -> click on MySQL Connections + -> Setup New Connection 
  4. Either set Hostname to localhost and port to 3306; Username: root and password: admin123
  4. OR whatever you prefer (however you would need to change the java connection variables in the application to your machine's configurations;
  5. Leave default schema blank. (The sql script will create one).
  6. Test connection -> OK
 ####  7.  IMPORTANT! Open connection -> File -> Run SQL Script -> doctorbaseScript.sql (can be downloaded directly from repository or link: https://www.dropbox.com/s/emnw0jjvrvdp4em/doctorbaseScript.sql?dl=0 ) This is required to create the schema and tables for the application. 
 If any unexpected errors occurs you can simply delete the schema (DROP SCHEMA doctorbase) and rerun script.


#### To run the application on Eclipse
  1. Open Eclipse
  2. Import -> Git -> Projects from Git -> Clone URI -> URI: https://github.com/ariellekhan/comp3613 -> Next -> Deselect all -> Select Master -> Choose storage directory -> Import existing Eclipse Project -> Finish
  3. Configure the variables of MySQLConnection.java of the application, to match your machine's database setup.
  
  location: DoctorAppointmentScheduler/src/doctorappointmentscheduler/server/MySQLConnection.java 
  
  4. Right click on project file in Package Explorer once it loaded properly -> Run as -> GWT Compiler
  5. Right click on project file again -> Run as -> GWT Development Mode with Jetty
  6. May take a while to compile again but once completed it will generate a URL (in the Development Mode Console) to view the application.
  
  
#### Errors?
 1. "RPC to database fail"  = database not connected, thus ensure correct username, password and host location matches to the machine's and the application.
  
 Note - DoctorAppointmentScheduler is the main project folder.

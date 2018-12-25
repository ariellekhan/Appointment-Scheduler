 package doctorappointmentscheduler.server;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.shared.AdministrativeStaff;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.AppointmentInfo;
import doctorappointmentscheduler.shared.Doctor;
import doctorappointmentscheduler.shared.MedicalReport;
import doctorappointmentscheduler.shared.Patient;
import doctorappointmentscheduler.shared.PatientRecord;
import doctorappointmentscheduler.shared.User;
import doctorappointmentscheduler.shared.UserType;

/*This is responsible for the mySQL connection, sending and retrieving data to and from the database*/

@SuppressWarnings("serial")
public class MySQLConnection extends RemoteServiceServlet implements DBConnection {  
	
	private Connection conn = null;  
 
	/* ***************************** TODO ***************************************** */
	private String user = "root";  ////Change to your MYSQL database user
	private String pass = "admin123";  //Change to your mqSQL database password
	private String hostname = "localhost"; //change to your server's host name
	private String portNumber = "3306"; //change to your server's port number
 

	private String schema = "doctorbase";
	private String url = "jdbc:mysql://" + hostname + ":" + portNumber + "/" + schema;  

	
	public MySQLConnection() {
		try {  //connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();  
			conn = DriverManager.getConnection(url, user, pass);  
		} 
		catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  
 
	//verifies if the User is in the database
	public User authenticateUser(String username, String pass) {  
		User user = null;  
		try {  
			PreparedStatement ps = conn.prepareStatement("Select * from users where username='" + username + "' and loginpassword='" + pass + "'");
			ResultSet result = ps.executeQuery();  
 
			if(result.next()) {  	 
				user = new User(result.getString("username"), result.getString("loginpassword"), result.getString("usertype")); 
			}  

			result.close();  
			ps.close();  
		} 
		catch (SQLException sqle) {  
			sqle.printStackTrace(); 
		}  
		return user;  
	}

 
//creates a user and store in database
@Override
public String createUser(UserType ut, String password) {
	String resultMsg = "Error creating account, try again.";
	try {
		  PreparedStatement ps = conn.prepareStatement("Select * from users where username = '" + ut.getEmail() +"';");
		  ResultSet rs = ps.executeQuery(); 
		  if(rs.next()) {
			  return "Email already exists, please use another";
		  }
		  Statement stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO users (username, loginpassword, usertype) ";
	      String sql2;
	      if(ut instanceof Doctor) {
	    	 sql+= "VALUES ("+"'"+ ut.getEmail() +"', "+"'"+ password +"', 'doctor')";
	    	 sql2 = "INSERT INTO doctors (email,firstName,lastName,dateOfBirth,gender,homeAddress,contactNumber) ";
	      }
	      else if(ut instanceof AdministrativeStaff) {
	    	 sql+= "VALUES ("+"'"+ ut.getEmail() +"', "+"'"+ password +"', 'staff')";
		     sql2 = "INSERT INTO staff (email,firstName,lastName,dateOfBirth,gender,homeAddress,contactNumber) ";
		  }
	      else{
	    	  sql += "VALUES ("+"'"+ ut.getEmail() +"', "+"'"+ password +"', 'patient')";
	    	  sql2 = "INSERT INTO patients (email,firstName,lastName,dateOfBirth,gender,homeAddress,contactNumber) ";
	      }
	      String innerSql = "(SELECT username from users WHERE username='"+ ut.getEmail() +"')";
	      sql2 += "VALUES ("+ innerSql +", "+"'"+ ut.getFirstName() +"'," +  "'"+ ut.getLastName() +"', "+
	    		  "'"+ ut.getDOB() +"', "+ "'"+ ut.getGender()+"', "+ "'"+ ut.getAddress() +"', "+ "'"+ ut.getContactNo() +"') ";
	                   
	      stmt.executeUpdate(sql);
	      stmt.executeUpdate(sql2);
	      
	      resultMsg = "User successfully created";
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }  
	 return resultMsg;  
	 }

//gets a Patient from the database based on an email supplied
@Override
public Patient getPatient(String email) {
	Patient patient = null;
	try {  
		 PreparedStatement ps = conn.prepareStatement("Select * from patients where email='" + email + "'");
		 ResultSet rs = ps.executeQuery();  
	 
		 if(rs.next()) {  	 
				 patient = new Patient(rs.getString("firstName"), rs.getString("lastName"),rs.getString("dateOfBirth"),
						 rs.getString("email"), rs.getString("gender"),rs.getString("homeAddress"), rs.getString("contactNumber")); 
		 }  
		 rs.close();  
		 ps.close();  
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }  
	return patient;
}

//add a medical report to database
@Override
public String addReport(MedicalReport m, Appointment a, String email) {
	String resultMsg = "Error submitting form, try again.";
	try {  
		 
		 Statement stmt = conn.createStatement();
	      String innerSql = "(SELECT username from users WHERE username='"+ email +"')";

		  String sql = "INSERT INTO appointments(email, appDate, appTime) ";
		  		 sql+= "VALUES ("+ innerSql +", "+  "'"+ a.getAppointmentDate() +"', "+
			    		  "'"+ a.getAppointmentTime() +"');";
			      stmt.executeUpdate(sql);

		  		 String sql2 = " INSERT INTO reports (email,medHistory,currMeds,conditioncl,height,weightcl) ";
		  		 sql2 += "VALUES ("+ innerSql +", "+"'"+ m.getMedicalHistory() +"'," +  "'"+ m.getCurrentMedication() +"', "+
	    		  "'"+ m.getCondition() +"', "+ "'"+ m.getHeight()+"', "+ "'"+ m.getWeight()+"') ";
	       
	      
	      stmt.executeUpdate(sql2);
	     
	      
	      resultMsg = "Form successfully submitted";
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }
	
	
	return resultMsg;
}

//gets a Medical Report from the database based on an email and appointment number supplied
@Override
public MedicalReport getMedicalReport(String email, int appNum) {
	MedicalReport medicalReport = null;
	try {  
		 PreparedStatement ps = conn.prepareStatement("Select * from reports where email='" + email + "' AND appNum = " +appNum+";");
		 ResultSet rs = ps.executeQuery();  
	 
		 if(rs.next()) {  	 
				 medicalReport = new MedicalReport(rs.getString("medHistory"), rs.getString("currMeds"),rs.getString("conditioncl"),
						 rs.getString("height"), rs.getString("weightcl")); 
		 }  

		 rs.close();  
		 ps.close();  
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }  
	return medicalReport;
}

//gets all appointments from database after a certain date
@Override
public ArrayList<AppointmentInfo> getAppointmentsFromDateOnwards(String date) {
	ArrayList<AppointmentInfo> apps = new ArrayList<>();
	try {  
		 Statement stmt = conn.createStatement();
		 
		 String sql = "SELECT appointments.appNum, appointments.appDate, appointments.appTime, appointments.email, patients.firstName, patients.lastName, patients.contactNumber ";
		 sql += "FROM appointments ";
		 sql += "INNER JOIN patients ON appointments.email=patients.email ";
		 sql += "Where appDate >= '" + date + "';";
		 ResultSet rs = stmt.executeQuery(sql);  
		
		
		 
		 while(rs.next()){
			 Appointment a = new Appointment( rs.getString("appDate"),rs.getString("appTime"));
			 int appNum = rs.getInt("appNum");
			 a.setAppointmentNumber(appNum);
			 
			 AppointmentInfo ai = new AppointmentInfo(a,  rs.getString("email"), rs.getString("firstName") + " " + rs.getString("lastName"), rs.getString("contactNumber"));
			 apps.add(ai);
			 
		
		 }
		 rs.close();
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }  
	
	return apps;
}

//gets all Patient Records in the database
@Override
public ArrayList<PatientRecord> getAllPatientsRecords() {
	ArrayList<PatientRecord> patientRecords = new ArrayList<>();
	try {  
		 Statement stmt = conn.createStatement();
		 
		 String sql = "SELECT * From patients";
		 ResultSet rs = stmt.executeQuery(sql);  

		 while(rs.next()){
			 Patient patient = new Patient( rs.getString("firstName"),rs.getString("lastName"), rs.getString("dateOfBirth"),
					 			rs.getString("email"),rs.getString("gender"),rs.getString("homeAddress"),rs.getString("contactNumber"));
			 PatientRecord patientRecord = new PatientRecord(patient);
			 patientRecords.add(patientRecord);
		 }
		 rs.close();
	 } catch (SQLException sqle) {  
		sqle.printStackTrace(); 
	 }  
	
	return patientRecords;
}


}

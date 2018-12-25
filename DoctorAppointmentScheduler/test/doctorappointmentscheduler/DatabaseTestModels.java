package doctorappointmentscheduler;

import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.MedicalReport;
import doctorappointmentscheduler.shared.Patient;
import doctorappointmentscheduler.shared.User;
import doctorappointmentscheduler.shared.UserType;

/* The purpose of this class is so it can simulate a database storage of data wrt models. (The expected values)
 * Thus whenever this class is instantiated in a test case the same model can be used instead of creating 
 * new ones in each test class.
 * Note this is not a test, this is just the class for objects supplied to the tests. 
 */

public class DatabaseTestModels {
	//Models
	private Appointment appointment;
	private MedicalReport medReport;
	private Patient patient;
	private User user;
	private UserType userType;
	
	//Variables
	private String password;
	private String email;
	//change email if test cases for creating user needs to be run again because email is a primary key in the database
	
	
	//Constructor
	public DatabaseTestModels() {
		email = "junit339@gmail.com"; 
		password = "junit123";
		user = new User(email,password,"patient");
		patient = new Patient("JUnit","Test","01/06/1997",email, "male", "#123 CC Road", "665-2332");
		appointment = new Appointment("2019/12/25", "9am to 10pm");
		medReport = new MedicalReport("surgery","pandol","sick", "200cm", "100kg");
		userType = patient;
	}
	
	public Appointment getAppointment() {
		return appointment;
	}
	
	public MedicalReport getMedicalReport() {
		return medReport;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public User getUser() {
		return user;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

}

package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/* A model representing the information about an appointment */


public class AppointmentInfo implements  IsSerializable {  
	
	//Instance Variables
	private Appointment appointment;
	private String patientEmail;
	private String patientName;
	private String patientContactNumber;
	
	//Constructor
	public AppointmentInfo(Appointment app, String email, String name, String contactNo) {
		this.appointment = app;
		this.patientEmail = email;
		this.patientName = name;
		this.patientContactNumber = contactNo;
	}

	//Acccessors
	public String getPatientName() {
		return patientName;
	}
	
	public String getPatientEmail() {
		return patientEmail;
	}
	
	public String getContactNumber() {
		return patientContactNumber;
	}
	
	public Appointment getAppointment() {
		return appointment;
	}
	
	
	@SuppressWarnings("unused")  
	private AppointmentInfo() {  
		//required here  
	}
	
}
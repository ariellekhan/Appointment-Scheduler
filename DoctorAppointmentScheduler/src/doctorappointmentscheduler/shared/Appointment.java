package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/* A model representing an appointment */


public class Appointment implements  IsSerializable {  
	

	//Instance Variables
	private String appointmentDate;
	private String appointmentTime;
	private int appointmentNumber;

	//Constructor
	public Appointment(String date, String time) {
		this.appointmentDate = date;
		this.appointmentTime = time;
	}

	//Mutators
	public void setAppointmentNumber(int appNum) {
		this.appointmentNumber = appNum;
	}
	
	//Accessors
	public String getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getAppointmentTime() {
		return appointmentTime;
	}
	
	public int getAppointmentNumber() {
		return appointmentNumber;
	}
	
	
	@SuppressWarnings("unused")  
	private Appointment() {  
		//required here  
	}  
	
}

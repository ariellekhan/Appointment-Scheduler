package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;

/* A Patient Record Model which store information about a patient, their appointments and medical reports */


public class PatientRecord implements  IsSerializable {  
	 
	//Instance variables
	private Patient patient;
	private ArrayList<Appointment> appointmentsList;
	private ArrayList<MedicalReport> medicalReportsList;
	
	//Constructor
	public PatientRecord(Patient patient) {
		this.patient = patient;
		this.appointmentsList = new ArrayList<>();
		this.medicalReportsList = new ArrayList<>();
	}
	
	//Mutators
	private void addAppointment(Appointment app) {
		this.appointmentsList.add(app);
	}
	
	private void addMedicalReport(MedicalReport medReport) {
		this.medicalReportsList.add(medReport);
	}
	
	 //ensures that for every appointment added, there is also a medical report
	public void addAppointmentAndReport(Appointment app, MedicalReport medReport ) {
		addAppointment(app);
		addMedicalReport(medReport);
	}
	
	//Accessors
	public Patient getPatient() {
		return this.patient;
	}
	
	public ArrayList<Appointment> getAppointments() {
		return this.appointmentsList;
	}
	
	public ArrayList<MedicalReport> getMedReports() {
		return this.medicalReportsList;
	}
	
	
	@SuppressWarnings("unused")  
	private PatientRecord() {  
		//required here 
	} 
	
}

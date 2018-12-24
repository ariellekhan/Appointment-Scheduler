package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/* A Model similar to a doctor's medical report */

public class MedicalReport implements  IsSerializable {

	//Instance Variables
	private String medicalHistory;
	private String currentMedications;
	private String condition;
	private String height;
	private String weight;
	
	//Constructor
	public MedicalReport(String medHistory, String currMeds, String condition, String height, String weight) {
		this.medicalHistory = medHistory;
		this.currentMedications = currMeds;
		this.condition = condition;
		this.height = height;
		this.weight = weight;	
	}
	
	//Accessors
	public String getMedicalHistory() {
		return this.medicalHistory;
	}
	
	public String getCurrentMedication() {
		return this.currentMedications;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	public String getHeight() {
		return this.height;
	}
	
	public String getWeight() {
		return this.weight;
	}
	
	
	@SuppressWarnings("unused")  
	private MedicalReport() {  
		//required here 
	}  
	
}

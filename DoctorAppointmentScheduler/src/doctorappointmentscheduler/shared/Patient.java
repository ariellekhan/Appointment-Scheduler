package doctorappointmentscheduler.shared;

/* Patient is a User Type for accessing the application */


public class Patient implements UserType {
	
	//Instance variables
	private String firstName;
	private String lastName;
	private String dob;
	private String address;
	private String contactNumber;
	private String email;
	private String gender;  
	
	//Constructor #1
	public Patient(String firstName, String lastName, String dob, String email, String gender) {
	    this. firstName = firstName;
	    this.lastName = lastName;
	    this.dob = dob;
	    this.email = email;
	    this.gender = gender;
	}
	    
	//Constructor #2
	public Patient(String firstName, String lastName, String dob, String email, String gender, String address, String contactNumber) {
		this. firstName = firstName;
	    this.lastName = lastName;
	    this.dob = dob;
	    this.email = email;
	    this.gender = gender;
	    this.address = address;
	    this.contactNumber = contactNumber;
	 }
	
	//Mutators
	public void setAddress(String addr){
		this.address = addr;
	}

	public void setContactNo(String contactNum){
		this.contactNumber = contactNum;
	}
	
	//Accessors
	public String getFirstName(){
	    return this.firstName;
	}
	
	public String getLastName(){
	    return this.lastName;
	}

	public String getDOB(){
	    return this.dob;
	}

	public String getAddress(){
	    return this.address;
	}

	public String getContactNo(){
	    return this.contactNumber;
	}

	public String getEmail(){
	    return this.email;
	}

	public String getGender(){
	    return this.gender;
	}
	
	
	@SuppressWarnings("unused")  
	private Patient() {  
	  //required here
	}
	
}
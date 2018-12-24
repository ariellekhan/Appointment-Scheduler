package doctorappointmentscheduler.shared;

/* Administrative-Staff is a User Type for accessing the application */


public class AdministrativeStaff implements UserType {
	
	//Instance Variables
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String contactNumber;
    private String email;
    private String gender; 
    
    //Constructor #1
    public AdministrativeStaff(String firstName, String lastName, String dob, String email, String gender) {
    	this. firstName = firstName;
    	this.lastName = lastName;
    	this.dob = dob;
    	this.email = email;
    	this.gender = gender;
    }
    
    //Constructor #2
    public AdministrativeStaff(String firstName, String lastName, String dob, String email, String gender, String address, String contactNumber) {
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
    	return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }

    public String getDOB(){
        return dob;
    }

    public String getAddress(){
        return address;
    }

    public String getContactNo(){
        return contactNumber;
    }

    public String getEmail(){
        return email;
    }

    public String getGender(){
        return gender;
    }
    
    
    @SuppressWarnings("unused")  
	private AdministrativeStaff() {  
		//required here  
	} 
    
}
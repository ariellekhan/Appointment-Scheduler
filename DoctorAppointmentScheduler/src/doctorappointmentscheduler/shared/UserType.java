package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/* This interface is used for creating accounts of different types to access the application */


public interface UserType extends IsSerializable {
	
	//Mutators
	public void setAddress(String addr); //sets a user's address
	public void setContactNo(String contactNum); //sets a user's contact number
	
	//Accessors
	public String getFirstName(); //returns a user's first name
	public String getLastName(); //returns a user's last name
	public String getDOB(); //returns a user's date of birth
	public String getAddress(); //returns a user's home address
	public String getContactNo(); // returns a user's contact number
	public String getEmail(); //returns a user's email
	public String getGender(); //returns a user's gender
	 
}

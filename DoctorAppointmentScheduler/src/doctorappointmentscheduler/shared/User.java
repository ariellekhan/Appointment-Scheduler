package doctorappointmentscheduler.shared;

import com.google.gwt.user.client.rpc.IsSerializable;  

/* A User Model for logging into the application */


public class User implements IsSerializable {  

	//Instance Variables
	private String email;  
	private String password;  
	private String userType;
	
	//Constructor
	public User(String email, String password, String userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	//Accessors
	public String getEmail() {
		return this.email;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	@SuppressWarnings("unused")  
	private User() {  
		//required here
	}
	
}

package doctorappointmentscheduler.client;
import java.util.Date;

public class Patient{
	    private String firstName;
	    private String lastName;
	    private Date dob;
	    private String address;
	    private int contactNumber;
	    private String email;
	    private double weight;
	    private double height;

	    public void setName(String fname, String lname){
	        this.firstName = fname;
	        this.lastName = lname;  
	    }

	    public void setDOB(Date dob){
	        this.dob = dob;
	    }

	    public void setAddress(String addr){
	        this.address = addr;
	    }

	    public void setContactNo(int contactNum){
	        this.contactNumber = contactNum;
	    }

	    public void setEmail(String email){
	        this.email = email;
	    }

	    public void setWeight(double w){
	        this.weight = w;
	    }

	    public void setHeight(double h){
	        this.height = h;
	    }

	    public String getName(){
	        return firstName + lastName;
	    }

	    public Date getDOB(){
	        return dob;
	    }

	    public String getAddress(){
	        return address;
	    }

	    public int getContactNo(){
	        return contactNumber;
	    }

	    public String getEmail(){
	        return email;
	    }

	    public double getHeight(){
	        return height;
	    }

	    public double getWeight(){
	        return weight;
	    }
}

package doctorappointmentscheduler.client.presenter;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.client.event.CreateAccountEvent;
import doctorappointmentscheduler.shared.AdministrativeStaff;
import doctorappointmentscheduler.shared.Doctor;
import doctorappointmentscheduler.shared.Patient;
import doctorappointmentscheduler.shared.UserType;

/* This presenter is used for presenting a view of a form for Creating An Account*/


public class CreateAccountPresenter implements Presenter {
	public interface Display {
	    Widget asWidget();
		HasClickHandlers getCreateAccountButtonClickHandler();
		String getEmailText();
		String getPasswordText();
		String getFirstNameText();
		String getLastNameText();
		String getContactNumberText();
		String getDateOfBirthText();
		String getGenderText();
		String getAddressText();
		String getUserTypeText();
		String getCode();
		TextBox getCodeTextBox();
		ListBox getUserTypeList();
		Label getStaffCodeLabel();
	}

	//*******Code to create office accounts************
	private final String adminStaffCode = "staff123";
	private final String doctorCode = "doctor123";
	//*************************************************
	
	//Instance Variables
	private final Display display;
	private final HandlerManager eventBus;


	//Constructor
	public CreateAccountPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind() {
		//handles when the create account button is clicked
		this.display.getCreateAccountButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doCreateAccount();
					}
				});
		
		//handles when the user type is changed in the form
		this.display.getUserTypeList().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//Show option if doctor or staff
				if(display.getUserTypeList().getSelectedValue().equalsIgnoreCase("doctor") || display.getUserTypeList().getSelectedValue().equalsIgnoreCase("administrative-staff")) {
					display.getCodeTextBox().setVisible(true);
					display.getStaffCodeLabel().setVisible(true);
				}
				else { //hide option if its a patient
					display.getCodeTextBox().setVisible(false);
					display.getStaffCodeLabel().setVisible(false);
				}
			}
		});
	}
	
	//Gets data from fields, verifies if the required field are filled out and sends to database
	private void doCreateAccount() {
		//get form data
		String email = display.getEmailText();
		String password = display.getPasswordText();
		String fname = display.getFirstNameText();
		String lname = display.getLastNameText();
		String userType = display.getUserTypeText();
		String contactNo = display.getContactNumberText();
		String addr = display.getAddressText();
		String gender = display.getGenderText();
		String dob = display.getDateOfBirthText();

		
		//optional fields
		if(addr.isEmpty()) {
			addr = "-";
		}
		if(contactNo.isEmpty()) {
			contactNo = "-";
		}

		//required fields
		if(email.isEmpty()) {
			Window.alert("Please Enter your Email");
		}
		else if(password.isEmpty()) {
			Window.alert("Please Enter your Password");
		}
		else if(fname.isEmpty()) {
			Window.alert("Please Enter your First Name");
		}
		else if(lname.isEmpty()) {
			Window.alert("Please Enter your Last Name");
		}
		else if(dob.isEmpty()) {
			Window.alert("Please Enter your Date of Birth");
		}
		else if(userType.equalsIgnoreCase("doctor") && !display.getCode().equalsIgnoreCase(doctorCode) ) {
			Window.alert("Incorrect Doctor Code");
		}
		else if(userType.equalsIgnoreCase("administrative-staff") && !display.getCode().equalsIgnoreCase(adminStaffCode) ) {
			Window.alert("Incorrect Administrative Staff Code");
		}
		else {
			
			//User entered data in all fields of the create account form
			UserType account;
			if(userType.equalsIgnoreCase("doctor")) {//create doctor account
				account = new Doctor(fname,lname,dob,email,gender,addr,contactNo);
			}
			else if(userType.equalsIgnoreCase("administrative-staff")) { //create admin-staff account
				account = new  AdministrativeStaff(fname,lname,dob,email,gender,addr,contactNo);
			}
			else {	//create patient account
				account = new Patient(fname,lname,dob,email,gender,addr,contactNo);
			}
			
			DBConnectionAsync dbService = GWT.create(DBConnection.class);
			//Database RPC request
			dbService.createUser(account, password, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
					Window.alert("RPC to database failed");
				}

				public void onSuccess(String str) { 
					Window.alert(str);
					if(str.equals("User successfully created")) {
						eventBus.fireEvent(new CreateAccountEvent());
					}
							
				}
			});
		}
	}
	
	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}

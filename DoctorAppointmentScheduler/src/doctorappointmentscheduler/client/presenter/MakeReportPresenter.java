package doctorappointmentscheduler.client.presenter;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.client.event.BookAppointmentEvent;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.MedicalReport;
import doctorappointmentscheduler.shared.Patient;
import doctorappointmentscheduler.shared.User;

/* This presenter is used for presenting a view of a Medical Report Form*/


public class MakeReportPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		HasClickHandlers getSubmitButtonClickHandler();
		Label getDateTextLabel();
		Label getTimeTextLabel();
		Label getFirstNameTextLabel();
		Label getLastNameTextLabel();
		Label getEmailTextLabel();
		Label getAddressTextLabel();
		Label getDateOfBirthTextLabel();
		Label getGenderTextLabel();
		Label getContactNumberTextLabel();
		String getDate();
		String getTime();
		String getMedicalHistory();
		String getWeight();
		String getHeight();
		String getCurrentMedication();
		String getCondition();
	}
	
	//Instance Variables
	private final Display display;
	private final HandlerManager eventBus;
	private final String value;
	private final String value2;
	private final User user;
	
	//Constructor
	public MakeReportPresenter(HandlerManager eventBus, Display display, String value, String value2, User user) {
		this.display = display;
		this.eventBus = eventBus;
		this.value= value;
		this.value2 = value2;
		this.display.getDateTextLabel().setText(this.value);
		this.display.getTimeTextLabel().setText(this.value2);
		this.user = user;
		autoFillForm();
		bind();
	}
	
	private void bind() {	
		//handles when the submit button is clicked
		this.display.getSubmitButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doSubmitForm();
					}
				});
	}
	
	//Fills the medical report with the the patient's account information from the database of 
	private void autoFillForm() {
		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.getPatient(this.user.getEmail(), new AsyncCallback<Patient>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}

			public void onSuccess(Patient p) {
				if(p!= null) {
					//fill the form with the patient data
					display.getEmailTextLabel().setText(p.getEmail());
					display.getFirstNameTextLabel().setText(p.getFirstName());
					display.getLastNameTextLabel().setText(p.getLastName());
					display.getAddressTextLabel().setText(p.getAddress());
					display.getDateOfBirthTextLabel().setText(p.getDOB());
					display.getGenderTextLabel().setText(p.getGender());
					display.getContactNumberTextLabel().setText(p.getContactNo());
				}
			}
		});
	}
	
	//Collects the data from the form and sends it to the database
	private void doSubmitForm() {
		//a medical report object with form data
		MedicalReport m = new MedicalReport(display.getMedicalHistory(), display.getCurrentMedication(), display.getCondition(),
				          display.getHeight(), display.getWeight());
		
		//convert the date format (to sql format)
		DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy");
		Date appDate = format.parse(display.getDate());
		String appDateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(appDate);
		
		//an appointment object with form data
		Appointment app = new Appointment(appDateString, display.getTime());

		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.addReport(m, app, user.getEmail(), new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}

			public void onSuccess(String s) { //form data successfully stored
					Window.alert(s);
					eventBus.fireEvent(new BookAppointmentEvent());
			}
		});
	}
	
	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
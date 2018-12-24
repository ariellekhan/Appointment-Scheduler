package doctorappointmentscheduler.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.MedicalReport;
import doctorappointmentscheduler.shared.Patient;


/* This presenter is used for presenting a view of an Individual Patient's Medical Appointment Report */

public class PatientAppointmentPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		Label getDateTextLabel();
		Label getTimeTextLabel();
		Label getFirstNameTextLabel();
		Label getLastNameTextLabel();
		Label getEmailTextLabel();
		Label getAddressTextLabel();
		Label getDateOfBirthTextLabel();
		Label getGenderTextLabel();
		Label getContactNumberTextLabel();
		Label getWeightTextLabel();
		Label getHeightTextLabel();
		TextArea getMedicalHistoryTextArea();
		TextArea getCurrentMedicationTextArea();
		TextArea getConditionTextArea();
	}

	//Instance Variables
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	private final String email;
	private final Appointment appointment;
	private final Display display;
	
	
	//Constructor
	public PatientAppointmentPresenter(HandlerManager eventBus, Display display, String email, Appointment app) {
		this.display = display;
		this.eventBus = eventBus;
		this.email = email;
		this.appointment = app;
		setForm();
		bind();
	}
	
	private void bind() {
		//handlers
	}
	
	
	//Fills the Medical Report with the respective patient's appointment data from the database
	private void setForm() {
		display.getDateTextLabel().setText(appointment.getAppointmentDate());
		display.getTimeTextLabel().setText(appointment.getAppointmentTime());
		
		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.getPatient(this.email, new AsyncCallback<Patient>() {
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
		
		//Database RPC request
		dbService.getMedicalReport(email, this.appointment.getAppointmentNumber(), new AsyncCallback<MedicalReport>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}

			public void onSuccess(MedicalReport mr) {
				if(mr!= null) {
					//fill the form with the patient Medical report data
					display.getWeightTextLabel().setText(mr.getWeight());
					display.getHeightTextLabel().setText(mr.getHeight());
					display.getMedicalHistoryTextArea().setText(mr.getCondition());
					display.getCurrentMedicationTextArea().setText(mr.getCurrentMedication());
					display.getConditionTextArea().setText(mr.getCondition());
				}
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
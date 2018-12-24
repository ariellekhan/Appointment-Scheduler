package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;


import doctorappointmentscheduler.client.presenter.PatientAppointmentPresenter;

/* This view displays an individual patient's appointment report details */


public class PatientAppointmentView extends Composite implements PatientAppointmentPresenter.Display{

	//UiBinder
	private static PatientAppointmentViewUiBinder uiBinder = GWT.create(PatientAppointmentViewUiBinder.class);

	interface PatientAppointmentViewUiBinder extends UiBinder<Widget, PatientAppointmentView> {
	}
	
	@UiField
	Label dateTextLabel;
	@UiField
	Label timeTextLabel;
	@UiField
	Label emailTextLabel;
	@UiField
	Label fnameTextLabel;
	@UiField
	Label lnameTextLabel;
	@UiField
	Label contactNoTextLabel;
	@UiField
	Label addressTextLabel;
	@UiField
	Label dobTextLabel;
	@UiField
	Label genderTextLabel;
	@UiField 
	Label heightTextLabel;
	@UiField 
	Label weightTextLabel;
	@UiField 
	TextArea medHistoryTextArea;
	@UiField 
	TextArea currMedTextArea;
	@UiField 
	TextArea conditionTextArea;
	
	//Constructor
	public PatientAppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
		medHistoryTextArea.setReadOnly(true);
		currMedTextArea.setReadOnly(true);
		conditionTextArea.setReadOnly(true);
	}

	//Accessors
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Label getDateTextLabel() {
		return dateTextLabel;
	}

	@Override
	public Label getTimeTextLabel() {
		return timeTextLabel;
	}

	@Override
	public Label getFirstNameTextLabel() {
		return fnameTextLabel;
	}

	@Override
	public Label getLastNameTextLabel() {
		return lnameTextLabel;
	}

	@Override
	public Label getEmailTextLabel() {
		return emailTextLabel;
	}

	@Override
	public Label getAddressTextLabel() {
		return addressTextLabel;
	}

	@Override
	public Label getDateOfBirthTextLabel() {
		return dobTextLabel;
	}

	@Override
	public Label getGenderTextLabel() {
		return genderTextLabel;
	}

	@Override
	public Label getContactNumberTextLabel() {
		return contactNoTextLabel;
	}

	@Override
	public Label getWeightTextLabel() {
		return weightTextLabel;
	}

	@Override
	public Label getHeightTextLabel() {
		return heightTextLabel;
	}
	
	@Override
	public TextArea getMedicalHistoryTextArea() {
		return medHistoryTextArea;
	}

	@Override
	public TextArea getCurrentMedicationTextArea() {
		return currMedTextArea;
	}

	@Override
	public TextArea getConditionTextArea() {
		return conditionTextArea;
	}
	
}

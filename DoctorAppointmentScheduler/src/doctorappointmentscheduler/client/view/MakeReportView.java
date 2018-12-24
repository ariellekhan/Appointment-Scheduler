package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import doctorappointmentscheduler.client.presenter.MakeReportPresenter;

/* This view displays a Medical form */


public class MakeReportView extends Composite implements MakeReportPresenter.Display{

	//UiBinder
	private static MakeReportViewUiBinder uiBinder = GWT.create(MakeReportViewUiBinder.class);

	interface MakeReportViewUiBinder extends UiBinder<Widget, MakeReportView> {
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
	TextArea medHistoryTextBox;
	@UiField 
	TextArea currMedTextBox;
	@UiField 
	TextBox heightTextBox;
	@UiField 
	TextBox weightTextBox;
	@UiField 
	TextArea conditionTextBox;
	@UiField
	FileUpload file;
	@UiField
	Button submitButton;
	@UiField
	HorizontalPanel buttonHPanel;
	
	//Constructor
	public MakeReportView() {
		initWidget(uiBinder.createAndBindUi(this));
		buttonHPanel.setWidth("325px");
	    buttonHPanel.setCellHorizontalAlignment(submitButton,HasHorizontalAlignment.ALIGN_RIGHT);
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
	public HasClickHandlers getSubmitButtonClickHandler() {
		return submitButton;
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
	public String getDate() {
		return dateTextLabel.getText().toString().trim();
	}

	@Override
	public String getTime() {
		return timeTextLabel.getText().toString().trim();
	}

	@Override
	public String getMedicalHistory() {
		return medHistoryTextBox.getText().toString();
	}

	@Override
	public String getWeight() {
		return weightTextBox.getText().toString().trim();
	}

	@Override
	public String getHeight() {
		return heightTextBox.getText().toString().trim();
	}

	@Override
	public String getCurrentMedication() {
		return currMedTextBox.getText().toString();
	}

	@Override
	public String getCondition() {
		return conditionTextBox.getText().toString();
	}

}

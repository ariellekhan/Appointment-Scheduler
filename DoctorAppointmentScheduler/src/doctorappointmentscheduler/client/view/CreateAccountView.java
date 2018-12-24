package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.presenter.CreateAccountPresenter;

/* This view displays a form for creating a user account */


public class CreateAccountView extends Composite implements CreateAccountPresenter.Display  {

	//UiBinder
	private static CreateAccountViewUiBinder uiBinder = GWT.create(CreateAccountViewUiBinder.class);

	interface CreateAccountViewUiBinder extends UiBinder<Widget, CreateAccountView> {
	}

	@UiField
	Button createAccountButton;
	@UiField
	TextBox emailTextBox;
	@UiField
	TextBox fNameTextBox;
	@UiField
	TextBox lNameTextBox;
	@UiField
	PasswordTextBox passwordTextBox;
	@UiField
	TextBox addressTextBox;
	@UiField
	TextBox contactNoTextBox;
	@UiField
	TextBox DOBTextBox;
	@UiField
	TextBox codeTextBox;
	@UiField
	ListBox usertypeListBox;
	@UiField
	ListBox genderListBox;
	@UiField 
	Label codeLabel;

	//Constructor
	public CreateAccountView() {
		initWidget(uiBinder.createAndBindUi(this));
		codeTextBox.setVisible(false);
		codeLabel.setVisible(false);
	}
	
	//Accessors
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCreateAccountButtonClickHandler() {
		return createAccountButton;
	}

	@Override
	public String getEmailText() {
		return emailTextBox.getText().toString().trim();
	}

	@Override
	public String getPasswordText() {
		return passwordTextBox.getText().toString().trim();
	}

	@Override
	public String getFirstNameText() {
		return fNameTextBox.getText().toString().trim();
	}

	@Override
	public String getLastNameText() {
		return lNameTextBox.getText().toString().trim();
	}

	@Override
	public String getContactNumberText() {
		return contactNoTextBox.getText().toString().trim();
	}

	@Override
	public String getDateOfBirthText() {
		return DOBTextBox.getText().toString().trim();
	}

	@Override
	public String getGenderText() {
		return genderListBox.getSelectedItemText().toString().trim().toLowerCase();
	}

	@Override
	public String getAddressText() {
		return addressTextBox.getText().toString().trim();
	}

	@Override
	public String getUserTypeText() {
		return usertypeListBox.getSelectedItemText().toString().trim().toLowerCase();
	}

	@Override
	public TextBox getCodeTextBox() {
		return codeTextBox;
	}

	@Override
	public ListBox getUserTypeList() {
		return usertypeListBox;
	}

	@Override
	public Label getStaffCodeLabel() {
		return codeLabel;
	}

	@Override
	public String getCode() {
		return codeTextBox.getText().toString().trim();
	}
	
}

package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.presenter.LoginPresenter;

/* This view displays a basic login form */


public class LoginView extends Composite implements LoginPresenter.Display {

	
	//UiBinder
	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	@UiField
	Button loginButton;
	@UiField
	Button signUpButton;
	@UiField
	HorizontalPanel buttonHPanel;
	@UiField
	TextBox emailBox;
	@UiField
	TextBox passwordBox;
	
	//Constructor
	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));
		buttonHPanel.setWidth("325px");
	    buttonHPanel.setCellHorizontalAlignment(loginButton,HasHorizontalAlignment.ALIGN_LEFT);
	}
	
	//Accessors

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getLoginButtonClickHandler() {
		return loginButton;
	}

	@Override
	public String getUsername() {
		return emailBox.getText().toString().trim().toLowerCase();
	}

	@Override
	public String getPassword() {
		return passwordBox.getText().toString();
	}

	@Override
	public HasClickHandlers getSignUpButtonClickHandler() {
		return signUpButton;
	}

}

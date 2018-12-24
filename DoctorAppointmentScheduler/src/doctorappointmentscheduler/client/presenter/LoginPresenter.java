package doctorappointmentscheduler.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.client.event.LoginEvent;
import doctorappointmentscheduler.client.event.SignUpEvent;
import doctorappointmentscheduler.shared.User;

/* This presenter is used for presenting a view of a Login Form */

public class LoginPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		HasClickHandlers getLoginButtonClickHandler();
		HasClickHandlers getSignUpButtonClickHandler();
		String getUsername();
		String getPassword();

	}

	//Instance Variables
	private final Display display;
	private final HandlerManager eventBus;
	
	//Constructor
	public LoginPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind() {
		//handles when the login button is clicked
		this.display.getLoginButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doLogin();
					}
				});
		
		//handles when the Sign Up button is clicked
		this.display.getSignUpButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doSignUp();
					}
				});
	}
	
	private void doLogin() {	
		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.authenticateUser(display.getUsername(),display.getPassword(), new AsyncCallback<User>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}

			public void onSuccess(User user1) {
				if(user1!= null) {
					Window.alert("Welcome to our online office! Logging in...");
					eventBus.fireEvent(new LoginEvent(user1));
				}
				else {
					Window.alert("Incorrect email/password combination!");
				}
			}
		});
	}
	
	//does the sign up event
	private void doSignUp() {
		eventBus.fireEvent(new SignUpEvent());
	}
	
	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}

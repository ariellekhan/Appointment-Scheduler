package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

import doctorappointmentscheduler.shared.User;

/*The event that a user signs into the application*/


public class LoginEvent extends GwtEvent<LoginEventHandler> {
	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	
	private User user;

	//Constructor
	public LoginEvent(User user) {
		this.user = user;
	}

	//Accessors
	public User getUser() {
		return this.user;
	}

	@Override
	public Type<LoginEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.onLogin(this);		
	}
}

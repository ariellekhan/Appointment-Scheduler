package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a person wants to sign up for an account*/


public interface SignUpEventHandler extends EventHandler {
	void onSignUp(SignUpEvent event);
}

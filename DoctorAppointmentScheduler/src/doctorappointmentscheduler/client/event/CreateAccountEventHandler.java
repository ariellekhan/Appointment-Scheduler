package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a person creates an account*/


public interface CreateAccountEventHandler extends EventHandler {
	void onCreateAccount(CreateAccountEvent event);
}

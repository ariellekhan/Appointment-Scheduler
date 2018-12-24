package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a user signs into the application*/


public interface LoginEventHandler extends EventHandler {
	void onLogin(LoginEvent event);
}

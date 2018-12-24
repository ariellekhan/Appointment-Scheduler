package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a user chooses an appointment date and time*/


public interface ChooseAppointmentDateEventHandler extends EventHandler {
	void onDateChoose(ChooseAppointmentDateEvent event);
}

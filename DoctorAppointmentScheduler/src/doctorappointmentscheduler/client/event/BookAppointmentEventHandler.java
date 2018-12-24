package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a user books an appointment*/

public interface BookAppointmentEventHandler extends EventHandler {
	void onFormSubmit(BookAppointmentEvent event);
}
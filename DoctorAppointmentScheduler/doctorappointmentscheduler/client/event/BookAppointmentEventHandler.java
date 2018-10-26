package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BookAppointmentEventHandler extends EventHandler {
	void onValueSubmit(BookAppointmentEvent event);
}

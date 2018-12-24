package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

/*The event handler for when a user selects a patient's appointment*/


public interface SelectPatientAppointmentEventHandler extends EventHandler {

	void onPatientSelect(SelectPatientAppointmentEvent event);
}

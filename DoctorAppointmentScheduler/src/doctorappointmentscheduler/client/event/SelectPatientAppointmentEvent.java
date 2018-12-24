package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

import doctorappointmentscheduler.shared.Appointment;

/*The event that a user selects a patient's appointment*/


public class SelectPatientAppointmentEvent extends GwtEvent<SelectPatientAppointmentEventHandler> {
	public static Type<SelectPatientAppointmentEventHandler> TYPE = new Type<SelectPatientAppointmentEventHandler>();
	
	private final String email;
	private final Appointment appointment;

	//Constructor
	public SelectPatientAppointmentEvent(String email, Appointment app) {
		this.appointment = app;
		this.email = email;
	}

	//Accessors
	public String getEmail() {
		return email;
	}
	public Appointment getAppointment() {
		return appointment;
	}

	
	@Override
	public Type<SelectPatientAppointmentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SelectPatientAppointmentEventHandler handler) {
		handler.onPatientSelect(this);	
	}
}

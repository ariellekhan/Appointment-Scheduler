package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

/*The event that a user chooses an appointment date and time*/


public class ChooseAppointmentDateEvent extends GwtEvent<ChooseAppointmentDateEventHandler> {
	public static Type<ChooseAppointmentDateEventHandler> TYPE = new Type<ChooseAppointmentDateEventHandler>();
	private final String dateString;
	private final String timeString;

	//Constructor
	public ChooseAppointmentDateEvent(String dateString, String timeString) {
		this.dateString = dateString;
		this.timeString = timeString;
	}

	//Accessors
	public String getDate() {
		return dateString;
	}
	public String getTime() {
		return timeString;
	}

	
	@Override
	public Type<ChooseAppointmentDateEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChooseAppointmentDateEventHandler handler) {
		handler.onDateChoose(this);
	}
}
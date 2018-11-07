package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BookAppointmentEvent extends GwtEvent<BookAppointmentEventHandler> {
	public static Type<BookAppointmentEventHandler> TYPE = new Type<BookAppointmentEventHandler>();
	private final String dateString;
	private final String timeString;

	//Constructor
	public BookAppointmentEvent(String dateString, String timeString) {
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
	public Type<BookAppointmentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(BookAppointmentEventHandler handler) {
		handler.onValueSubmit(this);
	}
}

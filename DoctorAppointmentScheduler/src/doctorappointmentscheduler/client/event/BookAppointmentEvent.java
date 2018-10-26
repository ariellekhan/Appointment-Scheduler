package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BookAppointmentEvent extends GwtEvent<BookAppointmentEventHandler> {
	public static Type<BookAppointmentEventHandler> TYPE = new Type<BookAppointmentEventHandler>();
	private final String value;

	public BookAppointmentEvent(String value) {
		this.value = value;
	}

	public String getId() {
		return value;
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

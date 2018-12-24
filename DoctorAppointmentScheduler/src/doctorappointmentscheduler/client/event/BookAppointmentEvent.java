package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

/*The event that a user books an appointment*/

public class BookAppointmentEvent extends GwtEvent<BookAppointmentEventHandler> {
	
	public static Type<BookAppointmentEventHandler> TYPE = new Type<BookAppointmentEventHandler>();

	@Override
	public Type<BookAppointmentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(BookAppointmentEventHandler handler) {
		handler.onFormSubmit(this);		
	}
	
}


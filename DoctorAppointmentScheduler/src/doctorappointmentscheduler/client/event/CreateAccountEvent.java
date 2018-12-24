package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

/*The event that a person creates an account*/


public class CreateAccountEvent extends GwtEvent<CreateAccountEventHandler> {
	public static Type<CreateAccountEventHandler> TYPE = new Type<CreateAccountEventHandler>();

	@Override
	public Type<CreateAccountEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateAccountEventHandler handler) {
		handler.onCreateAccount(this);		
	}
}

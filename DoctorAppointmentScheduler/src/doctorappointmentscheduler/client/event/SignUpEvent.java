package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

/*The event that a person wants to sign up for an account*/


public class SignUpEvent  extends GwtEvent< SignUpEventHandler> {
	public static Type< SignUpEventHandler> TYPE = new Type< SignUpEventHandler>();

	@Override
	public Type< SignUpEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch( SignUpEventHandler handler) {
		handler.onSignUp(this);		
	}
	
}

package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ReportEvent extends GwtEvent<ReportEventHandler> {
	public static Type<ReportEventHandler> TYPE = new Type<ReportEventHandler>();

	@Override
	public Type<ReportEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ReportEventHandler handler) {
		handler.onValueSubmit(this);		
	}
}

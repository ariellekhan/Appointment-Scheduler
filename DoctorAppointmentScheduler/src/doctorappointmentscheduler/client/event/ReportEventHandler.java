package doctorappointmentscheduler.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ReportEventHandler extends EventHandler {
	void onValueSubmit(ReportEvent event);
}

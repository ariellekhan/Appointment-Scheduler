package doctorappointmentscheduler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/* The entry point of the application*/

public class DoctorAppointmentScheduler implements EntryPoint {
	
	public void onModuleLoad() {
		HandlerManager eventBus = new HandlerManager(null);
		PatientManagementSystem appViewer = new PatientManagementSystem(eventBus);
		appViewer.go(RootPanel.get("appPanel")); //a div container in the HTML
		
	}
}

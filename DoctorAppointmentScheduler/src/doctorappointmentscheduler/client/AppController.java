package doctorappointmentscheduler.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import doctorappointmentscheduler.client.event.BookAppointmentEvent;
import doctorappointmentscheduler.client.event.BookAppointmentEventHandler;
import doctorappointmentscheduler.client.event.ReportEvent;
import doctorappointmentscheduler.client.event.ReportEventHandler;
import doctorappointmentscheduler.client.presenter.BookAppointmentPresenter;
import doctorappointmentscheduler.client.presenter.Presenter;
import doctorappointmentscheduler.client.presenter.ReportPresenter;
import doctorappointmentscheduler.client.view.BookAppointmentView;
import doctorappointmentscheduler.client.view.ReportView;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private HasWidgets container;
	private BookAppointmentView bookAppointmentView = null;


	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(BookAppointmentEvent.TYPE, new BookAppointmentEventHandler() {
			public void onValueSubmit(BookAppointmentEvent event) {
				doReportView(event.getDate(), event.getTime());
			}
		});
		eventBus.addHandler(ReportEvent.TYPE, new ReportEventHandler() {
			public void onValueSubmit(ReportEvent event) {
				History.back();
			}
		});
	}


	private void doReportView(String dateString, String timeString) {
		History.newItem("ReportPage", false);
		Presenter presenter = new ReportPresenter(eventBus, new ReportView(), dateString, timeString);
		presenter.go(container);
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("BookAppointmentPage");
		}			
		else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals("BookAppointmentPage")) {
				if (bookAppointmentView == null) {
					bookAppointmentView = new BookAppointmentView();

				}
				new BookAppointmentPresenter(eventBus, bookAppointmentView).go(container);
			}
		}

	}
}

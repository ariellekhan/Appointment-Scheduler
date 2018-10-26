package doctorappointmentscheduler.client.presenter;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class BookAppointmentPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		HasClickHandlers getSubmitButtonClickHandler();
		HasValueChangeHandlers<Date> getValueChangeHandler();
		void setDateLabel(String dateString);
	
	}

	private final Display display;
	private final HandlerManager eventBus;

	public BookAppointmentPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		bind();
	}
	

	private void bind() {	
		this.display.getSubmitButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Appointment Booked");
					}
				});
		
		this.display.getValueChangeHandler().addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(date);
				display.setDateLabel(dateString);					
				
			}
	      });

	
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}

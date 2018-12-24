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

import doctorappointmentscheduler.client.event.ChooseAppointmentDateEvent;


/* This presenter is used for presenting a view of a Calendar for displaying and booking appointments */


public class BookAppointmentPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		HasClickHandlers getChooseButtonClickHandler();
		HasValueChangeHandlers<Date> getValueChangeHandler();
		void setDateLabel(String dateString);
		String getDateText();
		String getTimeText();
		String getDateStyle(Date d1);
		void setUnavailableDate(Date date);
		void removeTime();
		boolean isTimeListEmpty();
		Date getDateSelected();
	}

	//Instance Variables
	private final Display display;
	private final HandlerManager eventBus;
	
	//Constructor
	public BookAppointmentPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind() {	
		//handles when the choose button is clicked
		this.display.getChooseButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doDateTimeVerify();
						
					}
				});

		//handles when the date is changed
		this.display.getValueChangeHandler().addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				checkDateAvailability(date);
			}
	      });
	}


	//Checks whether a date supplied is available or unavailable for appointment then outputs appropriate message
	private void checkDateAvailability(Date date) {
		String dateString = DateTimeFormat.getFormat("dd-MM-yyyy").format(date);
		String dateStyle = (display.getDateStyle(date));
		dateStyle = dateStyle.trim();
		
		//available dates
		if(dateStyle.startsWith("available-date", 0)) {
			display.setDateLabel(dateString);
		}
		//unavailable dates
		else {
			display.setDateLabel(null);
			Window.alert("Sorry unavailable date! Please select a Green date");
		}
	}
	
	
	//Verifies whether the date and time were supplied before firing event
	private void doDateTimeVerify() {
		String dateString = display.getDateText();
		String timeString =  display.getTimeText();
		
		if(!dateString.equals("") && !timeString.equals("")) { //has date and has time
			removeAppointmentTime();
			eventBus.fireEvent(new ChooseAppointmentDateEvent(dateString, timeString));
		}
		else if(dateString.equals("") && !timeString.equals("")) { //no date but has time
			Window.alert("Please select a date!");
		}
		else if(!dateString.equals("") && timeString.equals("")) { //has date but not time
			Window.alert("Please select a time!");
		}
		else { //no date nor no time
			Window.alert("Please select a date and time");
		}	
	}
	
	//removes the chosen time from the presenter
	private void removeAppointmentTime() {
		display.removeTime();
		if(display.isTimeListEmpty()) {
			display.setUnavailableDate(display.getDateSelected());
		}
	}
	
	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}

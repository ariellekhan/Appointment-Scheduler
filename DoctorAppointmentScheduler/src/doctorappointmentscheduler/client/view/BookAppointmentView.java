package doctorappointmentscheduler.client.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.presenter.BookAppointmentPresenter;

/* This view displays a calendar of dates and time for booking an appointment */


public class BookAppointmentView extends Composite implements BookAppointmentPresenter.Display {

	//UiBinder
	private static BookAppointmentViewUiBinder uiBinder = GWT.create(BookAppointmentViewUiBinder.class);

	interface BookAppointmentViewUiBinder extends UiBinder<Widget, BookAppointmentView> {
	}

	@UiField
	VerticalPanel mainVPanel;
	@UiField
	VerticalPanel dateVPanel;
	@UiField
	Label appointmentDateLabel;
	@UiField
	VerticalPanel timeVPanel;
	@UiField
	Label timeLabel;
	@UiField
	Button chooseButton;
	@UiField
	HorizontalPanel mainHPanel;
	@UiField
	HorizontalPanel buttonHPanel;
	
	//Instance Variables
	private AppointmentCalendarCustomWidget appointmentCalendar;
	private AppointmentTimeListCustomWidget appointmentTime;
	
	
	//Constructor
	public BookAppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
		appointmentCalendar = new AppointmentCalendarCustomWidget();
		appointmentTime = new AppointmentTimeListCustomWidget();
		mainHPanel.setSpacing(40);
		mainVPanel.setSpacing(20);
		buttonHPanel.setWidth("375px");
	    buttonHPanel.setCellHorizontalAlignment(chooseButton,HasHorizontalAlignment.ALIGN_RIGHT);
		dateVPanel.add(appointmentCalendar);
		timeVPanel.add(appointmentTime);

	}
	
	//Mutators
	
	@Override
	public void setDateLabel(String dateString) {
        appointmentDateLabel.setText(dateString);
	}
	
	@Override
	public void setUnavailableDate(Date date) {
		this.appointmentCalendar.setUnavailableDate(date);
		
	}
	
	@Override
	public void removeTime() {
		this.appointmentTime.removeTime();
	}
	
	
	//Accessors
	
	@Override
	public Widget asWidget() {
		return this.mainVPanel;
	}

	@Override
	public HasClickHandlers getChooseButtonClickHandler() {
		return chooseButton;
	}

	@Override
	public HasValueChangeHandlers<Date> getValueChangeHandler() {
		return appointmentCalendar;
	}

	@Override
	public String getDateStyle(Date date) {
		return appointmentCalendar.getStyleOfDate(date);
	}

	@Override
	public String getDateText() {
		return appointmentDateLabel.getText();
	}

	@Override
	public String getTimeText() {
		return appointmentTime.getSelectedItemText();
	}

	@Override
	public Date getDateSelected() {
		return appointmentCalendar.getValue();
	}

	@Override
	public boolean isTimeListEmpty() {
		return this.appointmentTime.isEmpty();
	}

}

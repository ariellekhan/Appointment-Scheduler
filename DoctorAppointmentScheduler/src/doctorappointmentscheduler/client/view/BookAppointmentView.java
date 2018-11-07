package doctorappointmentscheduler.client.view;


import java.util.Date;

import doctorappointmentscheduler.client.presenter.BookAppointmentPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class BookAppointmentView extends Composite implements BookAppointmentPresenter.Display {

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
	Button submitButton;
	
	@UiField
	HorizontalPanel mainHPanel;
	
	
	
	private AppointmentCalendarCustomWidget appointmentCalendar;
	private AppointmentTimeListCustomWidget appointmentTime;
	
	

	public BookAppointmentView() {
	
		initWidget(uiBinder.createAndBindUi(this));
		
		
		
		
		appointmentCalendar = new AppointmentCalendarCustomWidget();
		appointmentTime = new AppointmentTimeListCustomWidget();
	
		
		mainHPanel.setSpacing(40);
		mainVPanel.setSpacing(20);
		dateVPanel.add(appointmentCalendar);
		
		timeVPanel.add(appointmentTime);
		
	
	}
	
	
	@Override
	public Widget asWidget() {
		return this.mainVPanel;
	}

	@Override
	public HasClickHandlers getSubmitButtonClickHandler() {
		return submitButton;
	}

	@Override
	public HasValueChangeHandlers<Date> getValueChangeHandler() {
		
		return appointmentCalendar;
		
	}

	@Override
	public void setDateLabel(String dateString) {
        appointmentDateLabel.setText(dateString);
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



}

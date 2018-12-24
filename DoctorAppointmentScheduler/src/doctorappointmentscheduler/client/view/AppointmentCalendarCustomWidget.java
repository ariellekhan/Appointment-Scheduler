package doctorappointmentscheduler.client.view;

/* A custom widget created using the DatePicker GWT original widget in order to display a calendar
 * view of available and unavailable dates for appointments.
 */

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarModel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.CalendarView;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.datepicker.client.MonthSelector;

public class AppointmentCalendarCustomWidget extends DatePicker {

	public AppointmentCalendarCustomWidget() {
		initCalendar();
	}

	public AppointmentCalendarCustomWidget(MonthSelector monthAndYearSelector, CalendarView view, CalendarModel model) {
		super(monthAndYearSelector, view, model);
	}
	
	
	//sets the color for the available dates in the calendar widget using a range
	public void setAvailableDates(Date startDate, Date endDate) {
		   
		   for (final Date date = startDate; date.compareTo(endDate) <= 0; CalendarUtil.addDaysToDate(date, 1)) {
			   this.removeStyleFromDates("unavailable-date", date);
			   this.addStyleToDates("available-date", date);
		      }
	   }
	   
	   //sets the color for a single available date in the calendar widget
	   public void setAvailableDate(Date date) {
		   this.removeStyleFromDates("unavailable-date", date);
		   this.addStyleToDates("available-date", date);
	   }
	   
	   //sets the color for the unavailable dates in the calendar widget using a range
	   public void setUnavailableDates(Date startDate, Date endDate) {
		   
		   for (final Date date = startDate; date.compareTo(endDate) <= 0; CalendarUtil.addDaysToDate(date, 1)) { 
			   this.removeStyleFromDates("available-date", date);
			   this.addStyleToDates("unavailable-date", date);
		      }
	   }
	   
	   //sets the color for a single unavailable date in the calendar widget
	   public void setUnavailableDate(Date date) {
		   this.removeStyleFromDates("available-date", date);
		   this.addStyleToDates("unavailable-date", date);
	   }
	   
	   //Default Appointment Calendar View
	   public void initCalendar() {
		   this.setValue(new Date(), true);
		   final DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");
		   
		   //range of unavailable and available days
		   final Date startUnavailableDate = format.parse("2000-01-01");
		   final Date endUnavailableDate = new Date();
		   CalendarUtil.addDaysToDate(endUnavailableDate, -1);
		   final Date startAvailableDate = new Date();
		   final Date endAvailableDate = format.parse("3000-12-31");
		   
		   //sets calendar
		   this.removeStyleFromDates("datePickerDayIsToday", startAvailableDate);
		   setUnavailableDates(startUnavailableDate,endUnavailableDate);   
		   setAvailableDates(startAvailableDate,endAvailableDate); 		     
	   }	
	   
	
}



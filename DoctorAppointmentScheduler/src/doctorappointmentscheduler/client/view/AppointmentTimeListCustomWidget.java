package doctorappointmentscheduler.client.view;

/* A custom widget created using ListBox GWT original widget in order 
 * to list available times for an appointment
 */


import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ListBox;

public class AppointmentTimeListCustomWidget extends ListBox {

	public AppointmentTimeListCustomWidget() {
		initTimeList();
	}

	public AppointmentTimeListCustomWidget(Element element) {
		super(element);
		// TODO Auto-generated constructor stub
	}
	
	public String getTimeRangeString(int timeStart, boolean isMorningFirst, int timeStop, boolean isMorningSecond) {
		
		//am
		if(isMorningFirst) {
			//am to am
			if(isMorningSecond) {
				if(timeStart < 10 && timeStop <10) {
					return "  " + timeStart +"am -   " + timeStop +"am";
				}
				else if(timeStart < 10 && timeStop >= 10) {
					return "  " + timeStart +"am - " + timeStop +"am";
				}
				else{
					return  timeStart +"am - " + timeStop +"am";
				}
				
			}
			//am to pm
			else {
				if(timeStart < 10 && timeStop < 10) {
					return "  " + timeStart +"am -   " + timeStop +"pm";
				}
				else if(timeStart < 10 && timeStop >= 10) {
					return "  " + timeStart +"am - " + timeStop +"pm";
				}
				else {
					return  timeStart +"am - " + timeStop +"pm";
				}
				
			}
		}
		//pm to pm
		else {
			if(timeStart < 10 && timeStop < 10) {
				return "  " + timeStart +"pm -  " + timeStop +"pm";
			}
			else if(timeStart < 10 && timeStop >= 10) {
				return "  " + timeStart +"pm - " + timeStop +"pm";
			}
			else{
				return  timeStart +"pm - " + timeStop +"pm";
			}
					
		}
	}
	
	
	public void initTimeList() {
		//Default office hours
		int openingTime = 9;
		int closingTime = 5;
		int interval= 1; //every hour
		
		int timeStart = openingTime;
		int timeStop = timeStart + interval;
		
		boolean isMorningFirst = true;
		boolean isMorningSecond = true;
		
		for(int i = 0; i < ((12-openingTime) + closingTime); i++ ) {
			
			if(timeStart >= 12 && isMorningFirst == true ) {
				isMorningFirst = false;
			}
			if(timeStart == 13) {
				timeStart = 1;
				timeStop = 2;
			}
			if(timeStop >= 12 && isMorningSecond == true ) {
				isMorningSecond = false;
			}
			if(timeStop == 13) {
				timeStop = 1;
			}
			
			this.insertItem( getTimeRangeString(timeStart, isMorningFirst,timeStop,isMorningSecond), i);
			
			timeStart = timeStart + 1;
			timeStop = timeStop + 1;
			
		}
		
	}
	
	public boolean isEmpty() {
		if(this.getItemCount() == 0) {
			return true;
		}
		return false;
	}
	
	public void removeTime() {
		if(!isEmpty()) {
			this.removeItem(this.getSelectedIndex());
		}
	}
	

}

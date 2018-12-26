package doctorappointmentscheduler.client.presenter;


import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.client.event.SelectPatientAppointmentEvent;
import doctorappointmentscheduler.shared.AppointmentInfo;

/* This presenter is used for presenting a view displaying all patient's appointments */


public class DisplayAppointmentPresenter implements Presenter {
	public interface Display {
		Widget asWidget();
		CellTable<AppointmentInfo> getTable();
		TextBox getSearchBox();
		HasClickHandlers getSearchButtonClickHandler();
		HasClickHandlers getRefreshButtonClickHandler();
	}

	//Instance Variables
	private final Display display;
	private final HandlerManager eventBus;
	private ArrayList<AppointmentInfo> appInfoList;

	//Constructor
	public DisplayAppointmentPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		appInfoList = new ArrayList<>();
		setUpTableColumns();
		setAppointmentsInfo();
		bind();
	}
	
	private void bind() {
	    // a selection model to handle user selection.
	    final SingleSelectionModel<AppointmentInfo> selectionModel = new SingleSelectionModel<AppointmentInfo>();
	    display.getTable().setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	    	public void onSelectionChange(SelectionChangeEvent event) {
	        AppointmentInfo selected = selectionModel.getSelectedObject();
	        if (selected != null) {//do the SelectPatientAppointment Event
				eventBus.fireEvent(new SelectPatientAppointmentEvent(selected.getPatientEmail(), selected.getAppointment()));
	        }
	      }
	    });
	    
	    this.display.getSearchButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doSearch();
					}
				});
	    
	    this.display.getRefreshButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						setAppointmentsInfo();
						display.getSearchBox().setText("");
					}
				});
	
	}
	
	//creates the table columns for values of a Patients' Appointments
	private void setUpTableColumns() {
		//Text column to show the Appointment Date
	    TextColumn<AppointmentInfo> dateColumn = new TextColumn<AppointmentInfo>() {
	      @Override
	      public String getValue(AppointmentInfo ai) {
	        return ai.getAppointment().getAppointmentDate();
	      }
	    };
	    display.getTable().addColumn(dateColumn, "Date"); //add column to table
	    
	    //Text column to show the Appointment Time
	    TextColumn<AppointmentInfo> timeColumn = new TextColumn<AppointmentInfo>() {
	      @Override
	      public String getValue(AppointmentInfo ai) {
	        return ai.getAppointment().getAppointmentTime();
	      }
	    };
	    display.getTable().addColumn(timeColumn, "Time"); //add column to table
	   
	    //Text column to show the Patient's Contact Number
	    TextColumn<AppointmentInfo> numberColumn = new TextColumn<AppointmentInfo>() {
	      @Override
	      public String getValue(AppointmentInfo ai) {
	        return ai.getContactNumber();
	      }
	    };
	    display.getTable().addColumn(numberColumn, "ContactNo"); //add column to table
	    
	     //Text column to show the Patient's Name
	    TextColumn<AppointmentInfo> nameColumn = new TextColumn<AppointmentInfo>() {
	      @Override
	      public String getValue(AppointmentInfo ai) {
	        return ai.getPatientName();
	      }
	    };
	    display.getTable().addColumn(nameColumn, "Name"); //add column to table
	}
	
	//fills the table with Patients' Appointments Information
	private void setAppointmentsInfo() {
		String dateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(new Date());
		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.getAppointmentsFromDateOnwards(dateString, new AsyncCallback<ArrayList<AppointmentInfo>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}
		
			public void onSuccess(ArrayList<AppointmentInfo> apps) { //sets the table with patients appointments data retrieved from database
				display.getTable().setRowCount(apps.size(), true);
				display.getTable().setRowData(0, apps);	  
				appInfoList = apps;
			}
		});
	}
	
	//search for the string supplied
	private void doSearch() {
		String search = display.getSearchBox().getText().trim().toLowerCase();
			if(search.isEmpty()) {
				display.getTable().setRowCount(appInfoList.size(), true);
				display.getTable().setRowData(0, appInfoList);	
			}
			else {
				ArrayList<AppointmentInfo> apps = new ArrayList<>();
				
				for(AppointmentInfo ai: appInfoList) {
					String name = ai.getPatientName().trim().toLowerCase();
					String date = ai.getAppointment().getAppointmentDate();
					String contact = ai.getContactNumber();
					String time = ai.getAppointment().getAppointmentTime();
					if(name.contains(search) || date.contains(search) || contact.contains(search) || time.contains(search)) {
						apps.add(ai);
					}
				}
				display.getTable().setRowCount(apps.size(), true);
				display.getTable().setRowData(0, apps);	
			}
		
	}

	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}

package doctorappointmentscheduler.client.presenter;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import doctorappointmentscheduler.client.DBConnection;
import doctorappointmentscheduler.client.DBConnectionAsync;
import doctorappointmentscheduler.shared.PatientRecord;


/* This presenter is used for presenting a view with Patient Records */

public class RecordsPresenter implements Presenter{
	public interface Display {
		Widget asWidget();
		CellTable<PatientRecord> getTable();
	}

	//Instance Variables
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	private final Display display;
	
	//Constructor
	public RecordsPresenter(HandlerManager eventBus, Display display) {
		this.display = display;
		this.eventBus = eventBus;
		setUpTableColumns();
		setRecordsInfo();
		bind();
	}
	
	private void bind() {
	    // a selection model to handle user selection.
	    final SingleSelectionModel<PatientRecord> selectionModel = new SingleSelectionModel<PatientRecord>();
	    display.getTable().setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	    	public void onSelectionChange(SelectionChangeEvent event) {
	    		PatientRecord selected = selectionModel.getSelectedObject();
	    		if (selected != null) {  
	    			Window.alert(selected.getPatient().getEmail()); //displays a record's email
	    		}
	    	}
	    });
	}
	
	//creates the table columns for values of a Patient Record
	private void setUpTableColumns() {
		
		//Text column to show the Record's Last Name
	    TextColumn<PatientRecord> lnameColumn = new TextColumn<PatientRecord>() {
	    	
	    	@Override
	    	public String getValue(PatientRecord pr) {
	    		return pr.getPatient().getLastName();
	    	}
	    };
	    display.getTable().addColumn(lnameColumn, "Last Name"); //add column to table
	    
	    //Text column to show the Record's First Name
	    TextColumn<PatientRecord> fnameColumn = new TextColumn<PatientRecord>() {
		      @Override
		      public String getValue(PatientRecord pr) {
		        return pr.getPatient().getFirstName();
		      }
		};
	    display.getTable().addColumn(fnameColumn, "FirstName"); //add column to table
	   
	    //Text column to show the Record's Date of Birth
	    TextColumn<PatientRecord> dobColumn = new TextColumn<PatientRecord>() {
		      @Override
		      public String getValue(PatientRecord pr) {
		        return pr.getPatient().getDOB();
		      }
		    };
	    display.getTable().addColumn(dobColumn, "Date-of-Birth"); //add column to table
	    
	    //Text column to show the Record's Contact Number
	    TextColumn<PatientRecord> contactColumn = new TextColumn<PatientRecord>() {
		      @Override
		      public String getValue(PatientRecord pr) {
		        return pr.getPatient().getContactNo();
		      }
		    };
	    display.getTable().addColumn(contactColumn, "ContactNo"); //add column to table
	}

	//Fills the table with Patient Records Information from data stored in the database
	private void setRecordsInfo() {
		DBConnectionAsync dbService = GWT.create(DBConnection.class);
		//Database RPC request
		dbService.getAllPatientsRecords( new AsyncCallback<ArrayList<PatientRecord>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert("RPC to database failed");
			}

			public void onSuccess(ArrayList<PatientRecord> apps) { //add patient records to table
				   display.getTable().setRowCount(apps.size(), true);
				   // Push the data into the widget.
				   display.getTable().setRowData(0, apps);
			}
		});
	}
	
	//Adds widgets to the container
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.presenter.RecordsPresenter;
import doctorappointmentscheduler.shared.PatientRecord;

/* This view displays the records of all patients in the system*/


public class RecordsView extends Composite implements RecordsPresenter.Display {

	//UiBinder
	private static RecordsViewUiBinder uiBinder = GWT.create(RecordsViewUiBinder.class);

	interface RecordsViewUiBinder extends UiBinder<Widget, RecordsView> {
	}
	
	@UiField
	VerticalPanel cellVPanel;
	@UiField
	VerticalPanel mainVPanel;
	
	//Instance Variables
    private SimplePager pager;
	private CellTable<PatientRecord> recordsTable;

	//Constructor
	public RecordsView() {
		initWidget(uiBinder.createAndBindUi(this));
		mainVPanel.setSpacing(20);
	    recordsTable = new CellTable<PatientRecord>();
	    recordsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		pager = new SimplePager();
		pager.setDisplay(recordsTable);
	    cellVPanel.add(recordsTable);
	}

	//Accessors
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public CellTable<PatientRecord> getTable() {
		return this.recordsTable;
	}

}

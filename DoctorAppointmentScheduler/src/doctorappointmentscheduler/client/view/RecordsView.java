package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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
	@UiField
	TextBox searchBox;
	@UiField
	Button searchButton;
	@UiField
	Button refreshButton; 
	@UiField
	Label appLabel;
	@UiField 
	HorizontalPanel appHPanel;
	
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
	    cellVPanel.add(pager);
		cellVPanel.setBorderWidth(1);
		appHPanel.setWidth("450px");
		appHPanel.setCellHorizontalAlignment(appLabel,HasHorizontalAlignment.ALIGN_CENTER);
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

	@Override
	public TextBox getSearchBox() {
		// TODO Auto-generated method stub
		return searchBox;
	}

	@Override
	public HasClickHandlers getSearchButtonClickHandler() {
		return searchButton;
	}

	@Override
	public HasClickHandlers getRefreshButtonClickHandler() {
		return refreshButton;
	}

}

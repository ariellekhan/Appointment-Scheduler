package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import doctorappointmentscheduler.client.presenter.DisplayAppointmentPresenter;
import doctorappointmentscheduler.shared.AppointmentInfo;

/* This view displays a listing of all the appointments booked */


public class DisplayAppointmentView extends Composite implements DisplayAppointmentPresenter.Display {

	//UiBinder
	private static DisplayAppointmentViewUiBinder uiBinder = GWT.create(DisplayAppointmentViewUiBinder.class);

	interface DisplayAppointmentViewUiBinder extends UiBinder<Widget, DisplayAppointmentView> {
	}
	
	@UiField
	VerticalPanel cellVPanel;
	@UiField
	VerticalPanel mainVPanel;
 
	//Instance Variables
	private CellTable<AppointmentInfo> appointmentsTable;
    private SimplePager pager;

    //Constructor
	public DisplayAppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
		mainVPanel.setSpacing(20); 
		appointmentsTable = new CellTable<AppointmentInfo>();
		appointmentsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		pager = new SimplePager();
		pager.setDisplay(appointmentsTable);
		cellVPanel.add(pager);
	    cellVPanel.add(appointmentsTable);
	}

	//Accessors
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public CellTable<AppointmentInfo> getTable() {
		return appointmentsTable;
	}
	
}

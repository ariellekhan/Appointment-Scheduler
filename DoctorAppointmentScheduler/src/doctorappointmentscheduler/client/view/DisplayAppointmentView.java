package doctorappointmentscheduler.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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
	private CellTable<AppointmentInfo> appointmentsTable;
    private SimplePager pager;

    //Constructor
	public DisplayAppointmentView() {
		initWidget(uiBinder.createAndBindUi(this));
		mainVPanel.setSpacing(20); 
		appointmentsTable = new CellTable<AppointmentInfo>();
		appointmentsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		appointmentsTable.setPageSize(15);
		pager = new SimplePager();
		pager.setDisplay(appointmentsTable);
	    cellVPanel.add(appointmentsTable);
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
	public CellTable<AppointmentInfo> getTable() {
		return appointmentsTable;
	}

	@Override
	public TextBox getSearchBox() {
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

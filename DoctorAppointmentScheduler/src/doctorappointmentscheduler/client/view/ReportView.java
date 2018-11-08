package doctorappointmentscheduler.client.view;

import doctorappointmentscheduler.client.presenter.ReportPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;



public class ReportView extends Composite implements ReportPresenter.Display{

	private static ReportViewUiBinder uiBinder = GWT.create(ReportViewUiBinder.class);

	interface ReportViewUiBinder extends UiBinder<Widget, ReportView> {
	}
	

	

	
	@UiField
	Label dateLabelText;
	
	@UiField
	Label timeLabelText;
	
	@UiField
	Button backButton;
	
	@UiField
	Button submitButton;
	
	public ReportView() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Label getDateTextLabel() {
		return dateLabelText;
	}


	@Override
	public Label getTimeTextLabel() {
		return timeLabelText;
	}

	@Override
	public HasClickHandlers getBackButtonClickHandler() {
		return backButton;
	}

	@Override
	public HasClickHandlers getSubmitButtonClickHandler() {
		return submitButton;
	}


}

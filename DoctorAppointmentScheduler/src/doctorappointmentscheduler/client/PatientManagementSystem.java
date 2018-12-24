package doctorappointmentscheduler.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import doctorappointmentscheduler.client.event.BookAppointmentEvent;
import doctorappointmentscheduler.client.event.BookAppointmentEventHandler;
import doctorappointmentscheduler.client.event.ChooseAppointmentDateEvent;
import doctorappointmentscheduler.client.event.ChooseAppointmentDateEventHandler;
import doctorappointmentscheduler.client.event.CreateAccountEvent;
import doctorappointmentscheduler.client.event.CreateAccountEventHandler;
import doctorappointmentscheduler.client.event.LoginEvent;
import doctorappointmentscheduler.client.event.LoginEventHandler;

import doctorappointmentscheduler.client.event.SelectPatientAppointmentEvent;
import doctorappointmentscheduler.client.event.SelectPatientAppointmentEventHandler;
import doctorappointmentscheduler.client.event.SignUpEvent;
import doctorappointmentscheduler.client.event.SignUpEventHandler;
import doctorappointmentscheduler.client.presenter.BookAppointmentPresenter;
import doctorappointmentscheduler.client.presenter.CreateAccountPresenter;
import doctorappointmentscheduler.client.presenter.DisplayAppointmentPresenter;
import doctorappointmentscheduler.client.presenter.LoginPresenter;
import doctorappointmentscheduler.client.presenter.PatientAppointmentPresenter;
import doctorappointmentscheduler.client.presenter.Presenter;
import doctorappointmentscheduler.client.presenter.RecordsPresenter;
import doctorappointmentscheduler.client.presenter.MakeReportPresenter;
import doctorappointmentscheduler.client.view.BookAppointmentView;
import doctorappointmentscheduler.client.view.CreateAccountView;
import doctorappointmentscheduler.client.view.DisplayAppointmentView;
import doctorappointmentscheduler.client.view.LoginView;
import doctorappointmentscheduler.client.view.PatientAppointmentView;
import doctorappointmentscheduler.client.view.RecordsView;
import doctorappointmentscheduler.client.view.MakeReportView;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.User;

/* This is the application controller.
 * This component contains the history management and view transition logic */

public class PatientManagementSystem implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private HasWidgets container;
	private User user;
	private Presenter presenter;

	public PatientManagementSystem(HandlerManager eventBus) {
		this.eventBus = eventBus;	
		this.user = null;
		this.presenter = null;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
		//on event that a user wants to sign up for an account
		eventBus.addHandler(SignUpEvent.TYPE, new SignUpEventHandler() {
			public void onSignUp(SignUpEvent event) {
				History.newItem("SignUpPage", true);
			}
		});
		
		//on event a user creates an account
		eventBus.addHandler(CreateAccountEvent.TYPE, new CreateAccountEventHandler() {
			public void onCreateAccount(CreateAccountEvent event) {
				doCreateAccount();
			}
		});
		
		//on event that a user logs in
		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onLogin(LoginEvent event) {
				user = event.getUser();
				doLogin();
			}
		});
		
		//on event a patient books an appointment
		eventBus.addHandler(BookAppointmentEvent.TYPE, new BookAppointmentEventHandler() {
			public void onFormSubmit(BookAppointmentEvent event) {
				doBookAppointmentView();
			}
		});
		
		//on event a user chooses an appointment date
		eventBus.addHandler(ChooseAppointmentDateEvent.TYPE, new ChooseAppointmentDateEventHandler() {
			public void onDateChoose(ChooseAppointmentDateEvent event) {
				if(user.getUserType().equalsIgnoreCase("patient")) { //allow patients
					doReportView(event.getDate(), event.getTime());
				}
				else {//restrict everyone else
					Window.alert("Only Patients' Account can book appointments");
				}
			}
		});
		
		//on event a user selects a patient appointment
		eventBus.addHandler(SelectPatientAppointmentEvent.TYPE, new SelectPatientAppointmentEventHandler() {
			public void onPatientSelect(SelectPatientAppointmentEvent event) {
				if(user.getUserType().equalsIgnoreCase("doctor")) {//allow doctors
					doDoctorMedicalReportView(event.getEmail(), event.getAppointment()); 
				}
				else {  //restrict everyone else
					Window.alert("Access Denied! - Doctors only");
				}
			}
		});
	}
	
	//directs to the sign up page
	private void doSignUp() {
		doCreateAccountView();
	}
	
	//redirects to login page after account created
	private void doCreateAccount() {
		doLoginView();
	}
	
	//directs users to specific pages depending on their user type
	private void doLogin() {
		if(user.getUserType().equalsIgnoreCase("doctor") || user.getUserType().equalsIgnoreCase("staff")) {
			doDisplayAppointmentView();
		}
		else {
			doBookAppointmentView();
		}
	}
	
	//displays a new CreateAccount View
	public void doCreateAccountView() {
		presenter = new CreateAccountPresenter(eventBus, new CreateAccountView());
		presenter.go(container);
	}

	
	//displays a new Login View
	private void doLoginView() {
		presenter = new LoginPresenter(eventBus, new LoginView());	
		presenter.go(container);
	}
	
	//displays a new BookAppointment View
	private void doBookAppointmentView() {
		History.newItem("BookAppointmentPage", true);
		presenter = new BookAppointmentPresenter(eventBus, new BookAppointmentView());
		presenter.go(container);
	}
	

	//displays a new Report View
	private void doReportView(String dateString, String timeString) {
		History.newItem("ReportPage", false);
		presenter = new MakeReportPresenter(eventBus, new MakeReportView(), dateString, timeString, user);
		presenter.go(container);
	}
	
	//display a new DisplayAppointment View
	private void doDisplayAppointmentView() {
		History.newItem("DisplayAppointmentPage", true);
		presenter = new DisplayAppointmentPresenter(eventBus, new DisplayAppointmentView());
		presenter.go(container);
	}
	
	//displays a new doctors' MedicalReport View
	private void doDoctorMedicalReportView(String email, Appointment app){
		History.newItem("DoctorMedicalReportPage", false);
		presenter = new PatientAppointmentPresenter(eventBus, new PatientAppointmentView(), email , app);
		presenter.go(container);
	}
	
	//display a new Records view
	private void doRecordsView() {
		presenter = new RecordsPresenter(eventBus, new RecordsView());
		presenter.go(container);
	}


	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("LoginPage");
		}			
		else {
			History.fireCurrentHistoryState();
		}
	}
	

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals("LoginPage")) {
				History.newItem("LoginPage", false);
				doLoginView();
			}
			else if (token.equals("SignUpPage")) {
				doSignUp();
			}
			else if(user == null) {
				Window.alert("Please login");
				History.newItem("LoginPage", false);
				doLoginView();
			}
			else if (token.equals("BookAppointmentPage")) {
				doBookAppointmentView();
			}
			else if (token.equals("DisplayAppointmentPage")) {//allows doctors and staff
				if(user.getUserType().equalsIgnoreCase("doctor") || user.getUserType().equalsIgnoreCase("staff") ) {
					doDisplayAppointmentView();
				}
				else {//restrict everyone else
					Window.alert("Access Denied! - Staff only");
				}
					
			}
			else if (token.equals("RecordsPage")) {//allows doctors and staff
				if(user.getUserType().equalsIgnoreCase("doctor") || user.getUserType().equalsIgnoreCase("staff") ) {
					doRecordsView();
				}
				else {//restrict everyone else
					Window.alert("Access Denied! - Staff only");
				}
			}	
		}
		else {
			
		}
	}
}

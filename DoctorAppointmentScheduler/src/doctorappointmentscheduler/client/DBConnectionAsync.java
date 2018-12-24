package doctorappointmentscheduler.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.AppointmentInfo;
import doctorappointmentscheduler.shared.MedicalReport;
import doctorappointmentscheduler.shared.Patient;
import doctorappointmentscheduler.shared.PatientRecord;
import doctorappointmentscheduler.shared.User;
import doctorappointmentscheduler.shared.UserType;

/*Interface for database connection asynchronous callback */

public interface DBConnectionAsync { 
	 //Corresponds to DBConnection
	 public void authenticateUser(String email, String pass, AsyncCallback<User> callback);
	 public void createUser(UserType userType,String password, AsyncCallback<String> callback);
	 public void getPatient(String email, AsyncCallback<Patient> callback);
	 public void addReport(MedicalReport medicalReport, Appointment appointment, String email, AsyncCallback<String> callback);
	 public void getMedicalReport(String email,int appNum, AsyncCallback<MedicalReport> callback);
	 public void getAppointmentsFromDateOnwards(String date, AsyncCallback<ArrayList<AppointmentInfo>> callback);
	 public void getAllPatientsRecords(AsyncCallback<ArrayList<PatientRecord>> callback);
}  
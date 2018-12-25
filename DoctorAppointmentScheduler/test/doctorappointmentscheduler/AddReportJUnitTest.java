package doctorappointmentscheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import doctorappointmentscheduler.server.MySQLConnection;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.MedicalReport;

public class AddReportJUnitTest {
	DatabaseTestModels db = new DatabaseTestModels();
	MySQLConnection mySQL = new MySQLConnection();

	//adds report to database and compares result message
	@Test
	public void addReportTest() {
		MedicalReport m = db.getMedicalReport();
		Appointment app = db.getAppointment();
		String email = db.getEmail();
		
		assertEquals("Form successfully submitted", mySQL.addReport(m, app, email));
	}

}


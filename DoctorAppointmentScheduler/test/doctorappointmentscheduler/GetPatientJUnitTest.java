package doctorappointmentscheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import doctorappointmentscheduler.server.MySQLConnection;
import doctorappointmentscheduler.shared.Patient;

public class GetPatientJUnitTest {
	DatabaseTestModels db = new DatabaseTestModels();
	MySQLConnection mySQL = new MySQLConnection(); 
	Patient patient;
	//gets patient from database using users's email

	@Test
	public void getPatientTest() {
		patient = mySQL.getPatient(db.getUser().getEmail());
		assertNotNull(patient);
	}

	@Test
	public void getPatientFirstNameTest() {
		String firstName = mySQL.getPatient(db.getUser().getEmail()).getFirstName();
		String expected = db.getPatient().getFirstName();
		assertEquals(expected, firstName); //compare first names
	}

	@Test
	public void getPatientLastNameTest() {
		String lastName =  mySQL.getPatient(db.getUser().getEmail()).getLastName();
		String expected = db.getPatient().getLastName();
		assertEquals(expected, lastName); //compare last names
	}

	@Test
	public void getPatientContactNumberTest() {
		String contactNo = mySQL.getPatient(db.getUser().getEmail()).getContactNo();
		String expected = db.getPatient().getContactNo();
		assertEquals(expected, contactNo ); //compare contact no
	}

	@Test
	public void getPatientAddressTest() {
		String address = mySQL.getPatient(db.getUser().getEmail()).getAddress();
		String expected = db.getPatient().getAddress();
		assertEquals(expected, address); //compare address
	}

	@Test
	public void getPatientGenderTest() {
		String gender =  mySQL.getPatient(db.getUser().getEmail()).getGender();
		String expected = db.getPatient().getGender();
		assertEquals(expected, gender); //compare genders
	}

	@Test
	public void getPatientEmailTest() {
		String email = mySQL.getPatient(db.getUser().getEmail()).getEmail();
		String expected = db.getPatient().getEmail();
		assertEquals(expected, email); //compare email
	}

	@Test
	public void getPatientDateOfBirthTest() {
		String dob = mySQL.getPatient(db.getUser().getEmail()).getGender();
		String expected = db.getPatient().getGender();
		assertEquals(expected, dob ); //compare DOB
	}

}
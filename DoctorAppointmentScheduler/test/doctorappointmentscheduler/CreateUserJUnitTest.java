package doctorappointmentscheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import doctorappointmentscheduler.server.MySQLConnection;

public class CreateUserJUnitTest {

	DatabaseTestModels db = new DatabaseTestModels();
	MySQLConnection mySQL = new MySQLConnection();
	
	//creates a new user in the database
	@Test
	public void createUserTest() {
		String result = mySQL.createUser(db.getUserType(), db.getPassword());
		assertEquals("User successfully created", result);
	}
}

package doctorappointmentscheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import doctorappointmentscheduler.server.MySQLConnection;

public class CreateExistingUserJUnitTest {

	DatabaseTestModels db = new DatabaseTestModels();
	MySQLConnection mySQL = new MySQLConnection();
	
	//attempts to create an existing user in database
	@Test
	public void createExistingUserTest() {
		String result = mySQL.createUser(db.getUserType(), db.getPassword());
		assertEquals("Email already exists, please use another", result);
	}

}

package doctorappointmentscheduler;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;

import doctorappointmentscheduler.server.MySQLConnection;
import doctorappointmentscheduler.shared.User;

public class AuthenticateUserJUnitTest {

	DatabaseTestModels db = new DatabaseTestModels();
	MySQLConnection mySQL = new MySQLConnection();
	
	//checks if user is in the database
	@Test
	public void authenticateUser() {
		User user = mySQL.authenticateUser(db.getEmail(), db.getPassword());
		assertNotNull(user);
	}

	//fake user info entered
	public void authenticateUserFake() {
		User user = mySQL.authenticateUser("fakemail@fake.com", "fake123");
		assertNull(user);
	}
}

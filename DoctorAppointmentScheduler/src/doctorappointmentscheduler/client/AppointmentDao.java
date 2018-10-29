package doctorappointmentscheduler;

import doctorappointmentscheduler.shared.Report;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.Result;
import java.sql.SQLException;

public interface AppointmentDao {
  Long createReport(Report report) throws SQLException;

  Report readReport(Long reportId) throws SQLException;

  void updateReport(Report report) throws SQLException;

  void deleteReport(Long reportId) throws SQLException;

  AppointmentsResult<Appointment> listAppointments() throws SQLException;
}
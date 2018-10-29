package doctorappointmentscheduler;

import doctorappointmentscheduler.shared.Report;
import doctorappointmentscheduler.shared.Appointment;
import doctorappointmentscheduler.shared.Result;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DatastoreDao implements AppointmentDao {

  // Constructor
  private Datastore datastore;
  private KeyFactory keyFactory;

  public DatastoreDao() {
    datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
    keyFactory = datastore.newKeyFactory().setKind("Report");      // Is used for creating keys later
  }


  public Report entityToReport(Entity entity) {
    Report report = new(Report( (entity.getKey().getId(), entity.getString(Report.name), entity.getValue(Report.appointmentTime), entity.getString(Report.medicalCondition), entity.getString(Report.currentMedication),   entity.getString(Report.medicalHistory), entity.getValue(Report.attachedImage), entity.getValue(Report.attachedVideo) ));
  }

  @Override
  public Long createReport(Report report) {
    IncompleteKey key = keyFactory.newKey();          // Key will be assigned once written
    FullEntity<IncompleteKey> incReportEntity = Entity.newBuilder(key)  // Create the Entity
        .set(Report.id, report.getId())
        .set(Report.name, report.getName())           // Add Property 
        .set(Report.time, report.getTime())
        .set(Report.medicalCondition, report.getCondition())
        .set(Report.currentMedication, report.getMedication())
        .set(Report.medicalHistory, report.getHistory())
        .set(Report.attachedImage, report.getImage())
        .set(Report.attachedVideo, report.getVideo())
        .build();
    Entity reportEntity = datastore.add(incReportEntity); // Save the Entity
    return reportEntity.getKey().getId();                 // The ID of the Key
  }


  @Override
  public Report readReport(Long reportId) {
    Entity reportEntity = datastore.get(keyFactory.newKey(reportId)); // Load an Entity for Key(id)
    return entityToReport(reportEntity);
  }


  @Override
  public void updateReport(Report report) {
    Key key = keyFactory.newKey(report.getId());  // From a report, create a Key
    Entity entity = Entity.newBuilder(key)         // Convert Report to an Entity
        .set(Report.id, report.getId())
        .set(Report.name, report.getName())           // Add Property 
        .set(Report.time, report.getTime())
        .set(Report.medicalCondition, report.getCondition())
        .set(Report.currentMedication, report.getMedication())
        .set(Report.medicalHistory, report.getHistory())
        .set(Report.attachedImage, report.getImage())
        .set(Report.attachedVideo, report.getVideo())
        .build();
    datastore.update(entity);                   // Update the Entity
  }


  @Override
  public void deleteReport(Long reportId) {
    Key key = keyFactory.newKey(reportId);        // Create the Key
    datastore.delete(key);                      // Delete the Entity
  }

}

package doctorappointmentscheduler;

import java.util.List;

public class Result<K> {

  public List<K> result;

  public Result(List<K> result) {
    this.result = result;
  }
}
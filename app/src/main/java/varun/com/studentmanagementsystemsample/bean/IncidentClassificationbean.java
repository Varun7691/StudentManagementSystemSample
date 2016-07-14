package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class IncidentClassificationbean {

    String incidentClassificationId, incidentClassificationName;

    public IncidentClassificationbean(String incidentClassificationId, String incidentClassificationName) {
        this.incidentClassificationId = incidentClassificationId;
        this.incidentClassificationName = incidentClassificationName;
    }

    public String getIncidentClassificationId() {
        return incidentClassificationId;
    }

    public void setIncidentClassificationId(String incidentClassificationId) {
        this.incidentClassificationId = incidentClassificationId;
    }

    public String getIncidentClassificationName() {
        return incidentClassificationName;
    }

    public void setIncidentClassificationName(String incidentClassificationName) {
        this.incidentClassificationName = incidentClassificationName;
    }
}

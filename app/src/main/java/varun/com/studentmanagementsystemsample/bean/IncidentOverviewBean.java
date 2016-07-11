package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 6/10/2016.
 */
public class IncidentOverviewBean {

    private String userTypeID, userID, incidentId, incidentName, incidentClassificationName, incidentClassificationId, isActive, schoolId;

    public IncidentOverviewBean(String userTypeID, String userID, String incidentId, String incidentName, String incidentClassificationName, String incidentClassificationId, String isActive, String schoolId) {
        this.userTypeID = userTypeID;
        this.userID = userID;
        this.incidentId = incidentId;
        this.incidentName = incidentName;
        this.incidentClassificationName = incidentClassificationName;
        this.incidentClassificationId = incidentClassificationId;
        this.isActive = isActive;
        this.schoolId = schoolId;
    }

    public String getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public String getIncidentClassificationName() {
        return incidentClassificationName;
    }

    public void setIncidentClassificationName(String incidentClassificationName) {
        this.incidentClassificationName = incidentClassificationName;
    }

    public String getIncidentClassificationId() {
        return incidentClassificationId;
    }

    public void setIncidentClassificationId(String incidentClassificationId) {
        this.incidentClassificationId = incidentClassificationId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}

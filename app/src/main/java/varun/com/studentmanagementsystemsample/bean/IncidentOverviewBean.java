package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Varun on 6/10/2016.
 */
public class IncidentOverviewBean implements Serializable {

    private String userTypeID;
    private String userID;
    private String incidentID;
    private String incidentName;
    private String incidentDescription;
    private String incidentRemarks;
    private String studentRegID;
    private String incidentClassificationName;
    private String incidentClassificationId;
    private String isActive;
    private String schoolID;
    private String incidentDate;
    private String incidentReportedDate;
    private String incidentStatusID;
    private String reportedAgainstStaffID;
    private String reportedAgainstStaffName;
    private String reportedAgainstStudentID;
    private String reportedAgainstStudentName;
    private String reportedByStudentID;
    private String reportedByStudentName;
    private String againstName;

    public IncidentOverviewBean(String userTypeID, String userID, String incidentID, String incidentName, String incidentDescription, String incidentRemarks, String studentRegID, String incidentClassificationName, String incidentClassificationId, String isActive, String schoolID, String incidentDate, String incidentReportedDate, String incidentStatusID, String reportedAgainstStaffID, String reportedAgainstStaffName, String reportedAgainstStudentID, String reportedAgainstStudentName, String reportedByStudentID, String reportedByStudentName, String againstName) {
        this.userTypeID = userTypeID;
        this.userID = userID;
        this.incidentID = incidentID;
        this.incidentName = incidentName;
        this.incidentDescription = incidentDescription;
        this.incidentRemarks = incidentRemarks;
        this.studentRegID = studentRegID;
        this.incidentClassificationName = incidentClassificationName;
        this.incidentClassificationId = incidentClassificationId;
        this.isActive = isActive;
        this.schoolID = schoolID;
        this.incidentDate = incidentDate;
        this.incidentReportedDate = incidentReportedDate;
        this.incidentStatusID = incidentStatusID;
        this.reportedAgainstStaffID = reportedAgainstStaffID;
        this.reportedAgainstStaffName = reportedAgainstStaffName;
        this.reportedAgainstStudentID = reportedAgainstStudentID;
        this.reportedAgainstStudentName = reportedAgainstStudentName;
        this.reportedByStudentID = reportedByStudentID;
        this.reportedByStudentName = reportedByStudentName;
        this.againstName = againstName;
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

    public String getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(String incidentID) {
        this.incidentID = incidentID;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getIncidentRemarks() {
        return incidentRemarks;
    }

    public void setIncidentRemarks(String incidentRemarks) {
        this.incidentRemarks = incidentRemarks;
    }

    public String getStudentRegID() {
        return studentRegID;
    }

    public void setStudentRegID(String studentRegID) {
        this.studentRegID = studentRegID;
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

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentReportedDate() {
        return incidentReportedDate;
    }

    public void setIncidentReportedDate(String incidentReportedDate) {
        this.incidentReportedDate = incidentReportedDate;
    }

    public String getIncidentStatusID() {
        return incidentStatusID;
    }

    public void setIncidentStatusID(String incidentStatusID) {
        this.incidentStatusID = incidentStatusID;
    }

    public String getReportedAgainstStaffID() {
        return reportedAgainstStaffID;
    }

    public void setReportedAgainstStaffID(String reportedAgainstStaffID) {
        this.reportedAgainstStaffID = reportedAgainstStaffID;
    }

    public String getReportedAgainstStaffName() {
        return reportedAgainstStaffName;
    }

    public void setReportedAgainstStaffName(String reportedAgainstStaffName) {
        this.reportedAgainstStaffName = reportedAgainstStaffName;
    }

    public String getReportedAgainstStudentID() {
        return reportedAgainstStudentID;
    }

    public void setReportedAgainstStudentID(String reportedAgainstStudentID) {
        this.reportedAgainstStudentID = reportedAgainstStudentID;
    }

    public String getReportedAgainstStudentName() {
        return reportedAgainstStudentName;
    }

    public void setReportedAgainstStudentName(String reportedAgainstStudentName) {
        this.reportedAgainstStudentName = reportedAgainstStudentName;
    }

    public String getReportedByStudentID() {
        return reportedByStudentID;
    }

    public void setReportedByStudentID(String reportedByStudentID) {
        this.reportedByStudentID = reportedByStudentID;
    }

    public String getReportedByStudentName() {
        return reportedByStudentName;
    }

    public void setReportedByStudentName(String reportedByStudentName) {
        this.reportedByStudentName = reportedByStudentName;
    }

    public String getAgainstName() {
        return againstName;
    }

    public void setAgainstName(String againstName) {
        this.againstName = againstName;
    }
}

package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 4/17/2016.
 */
public class IncidentBean {
    private String incidentAgainst, incidentFor, incidentNature, incidentDate, incidentReportingDate, incidentDescription, incidentFeedback, incidentRemarks, incidentStatus, incidentForDesignation, incidentAgainstDesignation, incidentForName, incidentTime;

    public IncidentBean(String incidentAgainst, String incidentFor, String incidentNature, String incidentDate) {
        this.incidentAgainst = incidentAgainst;
        this.incidentFor = incidentFor;
        this.incidentNature = incidentNature;
        this.incidentDate = incidentDate;
    }

    public IncidentBean(String incidentAgainst, String incidentFor, String incidentNature, String incidentDate, String incidentReportingDate, String incidentDescription, String incidentFeedback, String incidentRemarks, String incidentStatus, String incidentForDesignation, String incidentAgainstDesignation, String incidentForName, String incidentTime) {
        this.incidentAgainst = incidentAgainst;
        this.incidentFor = incidentFor;
        this.incidentNature = incidentNature;
        this.incidentDate = incidentDate;
        this.incidentReportingDate = incidentReportingDate;
        this.incidentDescription = incidentDescription;
        this.incidentFeedback = incidentFeedback;
        this.incidentRemarks = incidentRemarks;
        this.incidentStatus = incidentStatus;
        this.incidentForDesignation = incidentForDesignation;
        this.incidentAgainstDesignation = incidentAgainstDesignation;
        this.incidentForName = incidentForName;
        this.incidentTime = incidentTime;
    }

    public String getIncidentReportingDate() {
        return incidentReportingDate;
    }

    public void setIncidentReportingDate(String incidentReportingDate) {
        this.incidentReportingDate = incidentReportingDate;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getIncidentFeedback() {
        return incidentFeedback;
    }

    public void setIncidentFeedback(String incidentFeedback) {
        this.incidentFeedback = incidentFeedback;
    }

    public String getIncidentRemarks() {
        return incidentRemarks;
    }

    public void setIncidentRemarks(String incidentRemarks) {
        this.incidentRemarks = incidentRemarks;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public String getIncidentForDesignation() {
        return incidentForDesignation;
    }

    public void setIncidentForDesignation(String incidentForDesignation) {
        this.incidentForDesignation = incidentForDesignation;
    }

    public String getIncidentAgainstDesignation() {
        return incidentAgainstDesignation;
    }

    public void setIncidentAgainstDesignation(String incidentAgainstDesignation) {
        this.incidentAgainstDesignation = incidentAgainstDesignation;
    }

    public String getIncidentForName() {
        return incidentForName;
    }

    public void setIncidentForName(String incidentForName) {
        this.incidentForName = incidentForName;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getIncidentAgainst() {
        return incidentAgainst;
    }

    public void setIncidentAgainst(String incidentAgainst) {
        this.incidentAgainst = incidentAgainst;
    }

    public String getIncidentFor() {
        return incidentFor;
    }

    public void setIncidentFor(String incidentFor) {
        this.incidentFor = incidentFor;
    }

    public String getIncidentNature() {
        return incidentNature;
    }

    public void setIncidentNature(String incidentNature) {
        this.incidentNature = incidentNature;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }
}

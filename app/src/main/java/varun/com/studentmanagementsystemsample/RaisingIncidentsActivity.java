package varun.com.studentmanagementsystemsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RaisingIncidentsActivity extends AppCompatActivity {

    EditText incidentReportingFor, incidentDesignation, incidentNature, incidentReportingAgainst, incidentDate, incidentTime, incidentReportingDate, incidentDescription, incidentFeedback, incidentRemarks, incidentStatus, incidentReportedBy, incidentReportedByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raising_incidents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        incidentReportingFor = (EditText) findViewById(R.id.incident_reporting_for);
        incidentDesignation = (EditText) findViewById(R.id.incident_designation);
        incidentNature = (EditText) findViewById(R.id.incident_nature);
        incidentReportingAgainst = (EditText) findViewById(R.id.incident_reporting_against);
        incidentDate = (EditText) findViewById(R.id.incident_date);
        incidentTime = (EditText) findViewById(R.id.incident_time);
        incidentReportingDate = (EditText) findViewById(R.id.incident_reporting_date);
        incidentDescription = (EditText) findViewById(R.id.incident_description);
        incidentFeedback = (EditText) findViewById(R.id.incident_feedback);
        incidentRemarks = (EditText) findViewById(R.id.incident_remarks);
        incidentStatus = (EditText) findViewById(R.id.incident_status);
        incidentReportedBy = (EditText) findViewById(R.id.incident_reported_by);
        incidentReportedByName = (EditText) findViewById(R.id.incident_reported_by_name);

        //DROPDOWN ITEMS
        incidentReportingFor.setFocusable(false);
        incidentDesignation.setFocusable(false);
        incidentReportingAgainst.setFocusable(false);
        incidentNature.setFocusable(false);
        incidentDate.setFocusable(false);
        incidentTime.setFocusable(false);
        incidentReportingDate.setFocusable(false);
        incidentStatus.setFocusable(false);
        incidentReportedBy.setFocusable(false);

    }
}

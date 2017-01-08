package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import varun.com.studentmanagementsystemsample.bean.IncidentOverviewBean;

public class IncidentDetailsActivity extends AppCompatActivity {

    IncidentOverviewBean incidentBean;

    TextView incidentNature, incidentReportingAgainst, incidentDate, incidentTime, incidentReportingDate, incidentDescription, incidentStatus, incidentFeedback, incidentRemarks, incidentReportedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Incident");

        incidentBean = (IncidentOverviewBean) getIntent().getSerializableExtra("INCIDENT");

        incidentNature = (TextView) findViewById(R.id.incident_details_nature);
        incidentReportingAgainst = (TextView) findViewById(R.id.incident_details_reporting_against);
        incidentDate = (TextView) findViewById(R.id.incident_details_date);
        incidentTime = (TextView) findViewById(R.id.incident_details_time);
        incidentReportingDate = (TextView) findViewById(R.id.incident_details_reporting_date);
        incidentDescription = (TextView) findViewById(R.id.incident_details_description);
        incidentStatus = (TextView) findViewById(R.id.incident_details_status);
        incidentFeedback = (TextView) findViewById(R.id.incident_details_feedback);
        incidentRemarks = (TextView) findViewById(R.id.incident_details_remarks);
        incidentReportedBy = (TextView) findViewById(R.id.incident_details_reported_by);

        incidentNature.setText(incidentBean.getIncidentClassificationName());
        incidentReportingAgainst.setText(incidentBean.getAgainstName());
        incidentDate.setText(incidentBean.getIncidentDate());
        incidentTime.setText(incidentBean.getIncidentDate());
        incidentReportingDate.setText(incidentBean.getIncidentReportedDate());
        incidentDescription.setText(incidentBean.getIncidentDescription());
        incidentStatus.setText(incidentBean.getIncidentStatusID());
        incidentReportedBy.setText(incidentBean.getReportedByStudentName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import varun.com.studentmanagementsystemsample.bean.SelfAwarnessReportBean;

public class SelfAwarenessActivity extends AppCompatActivity {

    TextView mGoals, mStrength, mInterests, mResponsibilities;

    SelfAwarnessReportBean selfAwarnessReportBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_awareness);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Self Awareness");

        selfAwarnessReportBean = (SelfAwarnessReportBean) getIntent().getSerializableExtra("SELF AWARENESS BEAN");

        mGoals = (TextView) findViewById(R.id.goals_text);
        mStrength = (TextView) findViewById(R.id.strength_text);
        mInterests = (TextView) findViewById(R.id.interests_text);
        mResponsibilities = (TextView) findViewById(R.id.resp_text);

        mGoals.setText(selfAwarnessReportBean.getGoal());
        mStrength.setText(selfAwarnessReportBean.getStrength());
        mInterests.setText(selfAwarnessReportBean.getInterestHobbies());
        mResponsibilities.setText(selfAwarnessReportBean.getResponsibilityDischarged_exceptionalAchievements());
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
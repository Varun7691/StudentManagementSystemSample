
package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import varun.com.studentmanagementsystemsample.bean.HealthReportBean;

public class HealthStatusActivity extends AppCompatActivity {

    TextView mHeight, mWeight, mBloodGroup, mDental, mVisionLeft, mVisionRight;

    HealthReportBean healthReportBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Status");

        healthReportBean = (HealthReportBean) getIntent().getSerializableExtra("HEALTH BEAN");

        mHeight = (TextView) findViewById(R.id.height);
        mWeight = (TextView) findViewById(R.id.weight);
        mBloodGroup = (TextView) findViewById(R.id.blood_group);
        mDental = (TextView) findViewById(R.id.dental);
        mVisionLeft = (TextView) findViewById(R.id.vision_left);
        mVisionRight = (TextView) findViewById(R.id.vision_right);

        mHeight.setText(healthReportBean.getHieght() + " cm");
        mWeight.setText(healthReportBean.getWeight() + " kg");
        mBloodGroup.setText(healthReportBean.getBloodGroup());
        mDental.setText(healthReportBean.getDental());
        mVisionLeft.setText(healthReportBean.getVisionLeft());
        mVisionRight.setText(healthReportBean.getVisionRight());
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
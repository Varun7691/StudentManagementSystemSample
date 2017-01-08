package varun.com.studentmanagementsystemsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.bean.ScholasticBean;

public class ScholasticActivity extends AppCompatActivity {

    TableLayout tableLayout1, tableLayout2;

    String studentFirstName = null, academicYear = null, term = null, board = null, FA1 = null, FA2 = null, totalFA = null, SA1 = null, totalFASA1 = null;
    int studentID = -1, rank = -1;

    ArrayList<ScholasticBean> scholasticBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholastic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Scholastic");

        scholasticBean = (ArrayList<ScholasticBean>) getIntent().getSerializableExtra("SCHOLASTIC LIST");

        tableLayout1 = (TableLayout) findViewById(R.id.table_layout_1);
        tableLayout2 = (TableLayout) findViewById(R.id.table_layout_2);

        for (int i = 0; i < scholasticBean.size(); i++) {
            addTableLayout1RowsAcademics(tableLayout1, scholasticBean, i);

            addTableLayout2RowsAcademics(tableLayout2, scholasticBean, i);
        }
    }

    private void addTableLayout1RowsAcademics(TableLayout tableLayout1, ArrayList<ScholasticBean> academic, int position) {

        TableRow tableRow1 = new TableRow(ScholasticActivity.this);
        TextView textView1 = new TextView(ScholasticActivity.this);

        tableRow1.setWeightSum(1);
        tableRow1.setBackgroundColor(Color.parseColor("#ffffff"));

        TableRow.LayoutParams tlp1 = new TableRow.LayoutParams(0,
                TableLayout.LayoutParams.WRAP_CONTENT, 1);

        textView1.setText(academic.get(position).getSubject());
        textView1.setLayoutParams(tlp1);

        tableRow1.addView(textView1);

        tableLayout1.addView(tableRow1);
    }

    private void addTableLayout2RowsAcademics(TableLayout tableLayout2, ArrayList<ScholasticBean> academic, int position) {

        TableRow tableRow2 = new TableRow(ScholasticActivity.this);

        TextView textView22 = new TextView(ScholasticActivity.this);
        TextView textView23 = new TextView(ScholasticActivity.this);
        TextView textView24 = new TextView(ScholasticActivity.this);
        TextView textView25 = new TextView(ScholasticActivity.this);
        TextView textView26 = new TextView(ScholasticActivity.this);

        tableRow2.setWeightSum(6);
        tableRow2.setBackgroundColor(Color.parseColor("#ffffff"));

        TableRow.LayoutParams tlp21 = new TableRow.LayoutParams(0,
                TableLayout.LayoutParams.WRAP_CONTENT, 1);

        textView22.setText(academic.get(position).getFA1());
        textView22.setLayoutParams(tlp21);

        textView23.setText(academic.get(position).getFA2());
        textView23.setLayoutParams(tlp21);

        textView24.setText(academic.get(position).getTotalFA());
        textView24.setLayoutParams(tlp21);

        textView25.setText(academic.get(position).getSA1());
        textView25.setLayoutParams(tlp21);

        textView26.setText(academic.get(position).getTotalFASA1());
        textView26.setLayoutParams(tlp21);

        tableRow2.addView(textView22);
        tableRow2.addView(textView23);
        tableRow2.addView(textView24);
        tableRow2.addView(textView25);
        tableRow2.addView(textView26);

        tableLayout2.addView(tableRow2);
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

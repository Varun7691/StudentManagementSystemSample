package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import varun.com.studentmanagementsystemsample.adapter.MatrixTableAdapter;

public class ScholasticActivity extends AppCompatActivity {

    String[][] table = new String[][]{
            {
                    "Subject",
                    "Term 1",
                    "",
                    "",
                    "",
                    ""},
            {
                    "",
                    "FA1",
                    "FA2",
                    "Total(FA)",
                    "SA1",
                    "Total(FA+SA1)"},
            {
                    "Tamil",
                    "C1",
                    "C2",
                    "C2",
                    "C2",
                    "C2"},
            {
                    "English",
                    "C2",
                    "C2",
                    "C2",
                    "C2",
                    "C2"},
            {
                    "Hindi",
                    "C1",
                    "C1",
                    "C1",
                    "C1",
                    "C1"},
            {
                    "Maths",
                    "C1",
                    "C1",
                    "C1",
                    "C1",
                    "C1"},
            {
                    "Science",
                    "C1",
                    "C1",
                    "C1",
                    "C1",
                    "C1"},
            {
                    "Social Science",
                    "C1",
                    "C1",
                    "C1",
                    "C1",
                    "C1"},
            {
                    "Computer Science",
                    "C1",
                    "C1",
                    "C1",
                    "C1",
                    "C1"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholastic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.scholastic_table);
        MatrixTableAdapter<String> matrixTableAdapter = new MatrixTableAdapter<String>(ScholasticActivity.this, table);
        tableFixHeaders.setAdapter(matrixTableAdapter);
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

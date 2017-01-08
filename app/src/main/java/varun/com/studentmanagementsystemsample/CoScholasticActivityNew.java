package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.adapter.CoScholasticAdapter;
import varun.com.studentmanagementsystemsample.bean.CoScholasticChildItemBean;
import varun.com.studentmanagementsystemsample.utils.SimpleSectionedRecyclerViewAdapter;

public class CoScholasticActivityNew extends AppCompatActivity {

    RecyclerView coScholasticRecyclerView;

    CoScholasticAdapter adapter;

    ArrayList<CoScholasticChildItemBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_scholastic_activity_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Co-Scholastic");

        list = (ArrayList<CoScholasticChildItemBean>) getIntent().getSerializableExtra("COSCHOLASTIC LIST");

        coScholasticRecyclerView = (RecyclerView) findViewById(R.id.co_scholastic_rv);

        adapter = new CoScholasticAdapter(CoScholasticActivityNew.this, list);

        ArrayList<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

        String temp1, temp2 = null;
        int pos;

        for (int i = 0; i < list.size(); i++) {
            temp1 = list.get(i).getTypeDesc();
            if (!temp1.equals(temp2)) {
                temp2 = temp1;
                pos = i;
                if (temp2.equalsIgnoreCase("Life Skills")) {
                    //STATUS 1,2
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(pos, "Life Skills"));
                } else if (temp2.equalsIgnoreCase("Work Education")) {
                    //STATUS 3
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(pos, "Work Education"));
                } else if (temp2.equalsIgnoreCase("Visual and Performing Arts")) {
                    //STATUS 4,5
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(pos, "Visual and Performing Arts"));
                } else if (temp2.equalsIgnoreCase("Attitudes")) {
                    //STATUS 6
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(pos, "Attitudes"));
                } else if (temp2.equalsIgnoreCase("Value")) {
                    //STATUS 6
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(pos, "Value"));
                }
            }
        }

        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionAdapter = new SimpleSectionedRecyclerViewAdapter(CoScholasticActivityNew.this, R.layout.section, R.id.section_text, adapter);
        mSectionAdapter.setSections(sections.toArray(dummy));

        coScholasticRecyclerView.setAdapter(mSectionAdapter);
        coScholasticRecyclerView.setLayoutManager(new LinearLayoutManager(CoScholasticActivityNew.this));

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

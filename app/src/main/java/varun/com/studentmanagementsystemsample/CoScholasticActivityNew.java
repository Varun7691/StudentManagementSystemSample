package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.adapter.CoScholasticAdapter;
import varun.com.studentmanagementsystemsample.bean.CoScholasticChildItemBean;
import varun.com.studentmanagementsystemsample.utils.SimpleSectionedRecyclerViewAdapter;

public class CoScholasticActivityNew extends AppCompatActivity {

    RecyclerView coScholasticRecyclerView;

    CoScholasticAdapter adapter;

    ArrayList<CoScholasticChildItemBean> list;

    String coScholasticSample = "{\"CoScholasticResult\":[{\"description\":\"Self Awareness\",\"remark\":\"Test\",\"grade\":\"C\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Problem Solving\",\"remark\":\"Test\",\"grade\":\"C\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Decision Making\",\"remark\":\"Test\",\"grade\":\"B\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Critical Thinking\",\"remark\":\"Test\",\"grade\":\"A\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Creative Thinking\",\"remark\":\"Test\",\"grade\":\"A\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Interpersonal Relationships\",\"remark\":\"Test\",\"grade\":\"B\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Effective Communication\",\"remark\":\"Test\",\"grade\":\"B\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Empathy\",\"remark\":\"Test\",\"grade\":\"D\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Managing Emotions\",\"remark\":\"Test\",\"grade\":\"B\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Dealing With Stress\",\"remark\":\"Test\",\"grade\":\"C\",\"typeDesc\":\"Life Skills\"},{\"description\":\"Work Education\",\"remark\":\"Test\",\"grade\":\"C\",\"typeDesc\":\"Work Education\"},{\"description\":\"Visual and Performing Arts\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Visual and Performing Arts\"},{\"description\":\"Towards Teachers\",\"remark\":\"Good\",\"grade\":\"B\",\"typeDesc\":\"Attitudes\"},{\"description\":\"Towards School - Mates\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Attitudes\"},{\"description\":\"Towards School Programmes And Environment\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Attitudes\"},{\"description\":\"To abide by the constitution and respect its ideals and institutions,the National Flag and the National Anthem\",\"remark\":\"Good\",\"grade\":\"B\",\"typeDesc\":\"Value\"},{\"description\":\"To cherish and follow the noble ideals which inspired freedom struggle\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To uphold and protect the sovereignty, unity and integrity of India\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To defend the country and render national service when called upon to do so\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To promote harmony and spirit of unity and brotherhood amongst all the people of India transcending religious,linguistic and regional or sectional diversities; to remove the practices derogatory to the dignity of women\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To value and preserve the rich heritage of our culture\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To protect and improve natural environment\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To develop scientific temper and the spirit of enquiry\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To safeguard public property and to abjure violence\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"},{\"description\":\"To strive towards excellence in all spheres of individual and collective activity which leads to higher level of performance\",\"remark\":\"Good\",\"grade\":\"A\",\"typeDesc\":\"Value\"}]}";

    ArrayList<CoScholasticChildItemBean> coScholasticChildItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_scholastic_activity_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = (ArrayList<CoScholasticChildItemBean>) getIntent().getSerializableExtra("COSCHOLASTIC LIST");

        coScholasticRecyclerView = (RecyclerView) findViewById(R.id.co_scholastic_rv);

        try {
            coScholasticChildItemBean = new ArrayList<>();

            JSONObject rootObject = new JSONObject(coScholasticSample);

            JSONArray coScholasticArray = rootObject.getJSONArray("CoScholasticResult");
            for (int i = 0; i < coScholasticArray.length(); i++) {
                JSONObject coScholasticObject = coScholasticArray.getJSONObject(i);

                String description = coScholasticObject.getString("description");
                String remark = coScholasticObject.getString("remark");
                String grade = coScholasticObject.getString("grade");
                String typeDesc = coScholasticObject.getString("typeDesc");

                coScholasticChildItemBean.add(new CoScholasticChildItemBean(description, remark, grade, typeDesc));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new CoScholasticAdapter(CoScholasticActivityNew.this, coScholasticChildItemBean);

        ArrayList<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

        String temp1, temp2 = null;
        int pos;

        for (int i = 0; i < coScholasticChildItemBean.size(); i++) {
            temp1 = coScholasticChildItemBean.get(i).getTypeDesc();
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

package varun.com.studentmanagementsystemsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.adapter.HomeAdapter;
import varun.com.studentmanagementsystemsample.bean.HomeBean;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvHome;
    ArrayList<HomeBean> list;
    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvHome = (RecyclerView) findViewById(R.id.rvHome);

        list = new ArrayList<>();
        list.add(new HomeBean("Varun Barve", "", "75%", "50%"));
        list.add(new HomeBean("Varun Barve", "", "75%", "50%"));
        list.add(new HomeBean("Varun Barve", "", "75%", "50%"));
        list.add(new HomeBean("Varun Barve", "", "75%", "50%"));
        list.add(new HomeBean("Varun Barve", "", "75%", "50%"));

        adapter = new HomeAdapter(HomeActivity.this, list);
        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
//        RecyclerView.ItemDecoration itemDecoration = new
//                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        rvHome.addItemDecoration(itemDecoration);

    }
}

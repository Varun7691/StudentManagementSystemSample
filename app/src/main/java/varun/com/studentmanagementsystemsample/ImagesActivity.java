package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.adapter.ImageAdapter;
import varun.com.studentmanagementsystemsample.bean.ImagesBean;

public class ImagesActivity extends AppCompatActivity {

    RecyclerView galleryRecyclerView;
    ImageAdapter adapter;

    ArrayList<ImagesBean> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Images");

        imageList = (ArrayList<ImagesBean>) getIntent().getSerializableExtra("IMAGE LIST");

        galleryRecyclerView = (RecyclerView) findViewById(R.id.rvGallery);

        adapter = new ImageAdapter(ImagesActivity.this, imageList);

        galleryRecyclerView.setAdapter(adapter);
        galleryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

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

package varun.com.studentmanagementsystemsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import varun.com.studentmanagementsystemsample.bean.ImagesBean;

public class GalleryDetailActivity extends AppCompatActivity {

    ImageView galleryImageView;
    ImagesBean imagesBean;

    TextView imageCaption, imageDescription, imageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagesBean = (ImagesBean) getIntent().getSerializableExtra("IMAGE");

        galleryImageView = (ImageView) findViewById(R.id.galleryImageView);
        imageCaption = (TextView) findViewById(R.id.image_caption);
        imageDescription = (TextView) findViewById(R.id.image_description);
        imageDate = (TextView) findViewById(R.id.image_date);

        Picasso.with(GalleryDetailActivity.this).load("http://103.7.130.46:8082" + imagesBean.getImagePath()).into(galleryImageView);

        imageCaption.setText(imagesBean.getImageTitle());
        imageDescription.setText(imagesBean.getImageDescription());
        imageDate.setText(imagesBean.getImageTakenDate());
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

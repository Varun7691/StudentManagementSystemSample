package varun.com.studentmanagementsystemsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class GalleryDetailActivity extends AppCompatActivity {

    SubsamplingScaleImageView galleryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int image = getIntent().getIntExtra("IMAGE", 0);

        galleryImageView = (SubsamplingScaleImageView) findViewById(R.id.galleryImageView);

        galleryImageView.setImage(ImageSource.resource(image));

    }
}

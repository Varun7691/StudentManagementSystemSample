package varun.com.studentmanagementsystemsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.GalleryAdapter;
import varun.com.studentmanagementsystemsample.bean.GalleryBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class GalleryFragment extends Fragment {

    RecyclerView galleryRecyclerView;
    ArrayList<GalleryBean> list;
    GalleryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvGallery);

        list = new ArrayList<>();
        list.add(new GalleryBean(R.drawable.student_1, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_2, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_3, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_4, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_5, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_6, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_1, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_2, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_3, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_4, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_5, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_6, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_1, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_2, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_3, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_4, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_5, "", "Caption", "1", "Description"));
        list.add(new GalleryBean(R.drawable.student_6, "", "Caption", "1", "Description"));

        adapter = new GalleryAdapter(GalleryFragment.this.getActivity(), list);

        galleryRecyclerView.setAdapter(adapter);
        galleryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        return rootView;
    }
}

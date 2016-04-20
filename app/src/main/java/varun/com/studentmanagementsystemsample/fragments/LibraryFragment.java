package varun.com.studentmanagementsystemsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.LibraryAdapter;
import varun.com.studentmanagementsystemsample.bean.LibraryBean;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;

/**
 * Created by Varun on 4/2/2016.
 */
public class LibraryFragment extends Fragment {

    RecyclerView rvLibrary;
    ArrayList<LibraryBean> list;
    LibraryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        rvLibrary = (RecyclerView) rootView.findViewById(R.id.rvLibrary);

        list = new ArrayList<>();

        list.add(new LibraryBean("Physics", "", "07/06/2016", "08/06/2016"));
        list.add(new LibraryBean("Chemistry", "", "07/06/2016", "08/06/2016"));
        list.add(new LibraryBean("Maths", "", "07/06/2016", "08/06/2016"));
        list.add(new LibraryBean("Biology", "", "07/06/2016", "08/06/2016"));
        list.add(new LibraryBean("History", "", "07/06/2016", "08/06/2016"));
        list.add(new LibraryBean("Geography", "", "07/06/2016", "08/06/2016"));

        adapter = new LibraryAdapter(LibraryFragment.this.getActivity(), list);

        rvLibrary.setAdapter(adapter);
        rvLibrary.setLayoutManager(new LinearLayoutManager(LibraryFragment.this.getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(LibraryFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvLibrary.addItemDecoration(itemDecoration);

        return rootView;
    }
}

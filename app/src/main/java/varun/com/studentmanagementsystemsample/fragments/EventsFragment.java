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
import varun.com.studentmanagementsystemsample.adapter.EventRecyclerAdapter;
import varun.com.studentmanagementsystemsample.bean.EventBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class EventsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<EventBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        list = new ArrayList<>();
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));
        list.add(new EventBean("1", "Annual Sports Meet", "07/06/1991", "07/06/1991"));

        EventRecyclerAdapter adapter = new EventRecyclerAdapter(EventsFragment.this.getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventsFragment.this.getActivity()));


        return rootView;
    }
}

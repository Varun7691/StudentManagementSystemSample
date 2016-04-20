package varun.com.studentmanagementsystemsample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.RaisingIncidentsActivity;
import varun.com.studentmanagementsystemsample.adapter.IncidentAdapter;
import varun.com.studentmanagementsystemsample.bean.IncidentBean;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;

/**
 * Created by Varun on 4/2/2016.
 */
public class IncidentsFragment extends Fragment {

    RecyclerView incidentRecyclerView;
    FloatingActionButton incidentFab;
    ArrayList<IncidentBean> list;
    IncidentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_incidents, container, false);

        incidentRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvIncident);
        incidentFab = (FloatingActionButton) rootView.findViewById(R.id.incident_fab);

        incidentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncidentsFragment.this.getActivity(), RaisingIncidentsActivity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));
        list.add(new IncidentBean("Varun", "Dilip", "Fighting on playground", "07/06/1991"));

        adapter = new IncidentAdapter(IncidentsFragment.this.getActivity(), list);

        incidentRecyclerView.setAdapter(adapter);
        incidentRecyclerView.setLayoutManager(new LinearLayoutManager(IncidentsFragment.this.getActivity()));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(IncidentsFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
        incidentRecyclerView.addItemDecoration(itemDecoration);

        return rootView;
    }
}

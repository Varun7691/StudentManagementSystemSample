package varun.com.studentmanagementsystemsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.PerformanceAdapter;
import varun.com.studentmanagementsystemsample.bean.PerformanceBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class PerformanceFragment extends Fragment {

    RecyclerView performanceRV;
    ArrayList<PerformanceBean> list;
    PerformanceAdapter adapter;
    Spinner dateSpinner;
    String[] dateArray = {"Jan 2016 - Jun 2016", "Jun 2016 - Dec 2016", "Jan 2016 - Jun 2016"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_performance, container, false);

        dateSpinner = (Spinner) rootView.findViewById(R.id.date_spinner);
        performanceRV = (RecyclerView) rootView.findViewById(R.id.rvPerformance);

        list = new ArrayList<>();
        list.add(new PerformanceBean("FA1", "Workshop", "10", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Drawing", "12", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Computer", "20", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Drawing", "12", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Workshop", "10", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Drawing", "12", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Computer", "20", "c1", "17-11-2014"));
        list.add(new PerformanceBean("FA1", "Drawing", "12", "c1", "17-11-2014"));

        adapter = new PerformanceAdapter(PerformanceFragment.this.getActivity(), list);
        performanceRV.setAdapter(adapter);
        performanceRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        dateSpinner.setAdapter(new ArrayAdapter<String>(PerformanceFragment.this.getActivity(), R.layout.date_spinner_item, R.id.date_spinner_text_view, dateArray));

        return rootView;
    }
}

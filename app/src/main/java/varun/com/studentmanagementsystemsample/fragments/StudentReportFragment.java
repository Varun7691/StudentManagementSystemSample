package varun.com.studentmanagementsystemsample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.ScholasticActivity;

/**
 * Created by Varun on 4/2/2016.
 */
public class StudentReportFragment extends Fragment {

    RelativeLayout scholastic, coscholastic, healthStatus, selfAwareness;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student_report, container, false);

        scholastic = (RelativeLayout) rootView.findViewById(R.id.scholastic_container);
        coscholastic = (RelativeLayout) rootView.findViewById(R.id.co_scholastic_container);
        healthStatus = (RelativeLayout) rootView.findViewById(R.id.health_status_container);
        selfAwareness = (RelativeLayout) rootView.findViewById(R.id.self_awareness_container);

        scholastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentReportFragment.this.getActivity(), ScholasticActivity.class);
                startActivity(intent);
            }
        });

        coscholastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        healthStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selfAwareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}

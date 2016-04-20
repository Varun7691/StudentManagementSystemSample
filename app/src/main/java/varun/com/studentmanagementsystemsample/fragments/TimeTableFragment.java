package varun.com.studentmanagementsystemsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.MatrixTableAdapter;

/**
 * Created by Varun on 4/2/2016.
 */
public class TimeTableFragment extends Fragment {

    String[][] table = new String[][]{
            {
                    "Period",
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday"},
            {
                    "/Date",
                    "01/06/2016",
                    "02/06/2016",
                    "03/06/2016",
                    "04/06/2016",
                    "05/06/2016",
                    "06/06/2016",
                    "07/06/2016"},
            {
                    "09:00 - 09:45",
                    "English",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "09:45 - 10:30",
                    "Biology",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "10:30 - 11:15",
                    "Math",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "11:25 - 12:10",
                    "Physics",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "12:10 - 12:55",
                    "Chemistry",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "13:25 - 14:10",
                    "P.E",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "14:10 - 14:55",
                    "P.E",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
            {
                    "14:55 - 15:40",
                    "P.E",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""},
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_time_table, container, false);

        TableFixHeaders tableFixHeaders = (TableFixHeaders) rootView.findViewById(R.id.table);
        MatrixTableAdapter<String> matrixTableAdapter = new MatrixTableAdapter<String>(TimeTableFragment.this.getActivity(), table);
        tableFixHeaders.setAdapter(matrixTableAdapter);

        return rootView;
    }
}

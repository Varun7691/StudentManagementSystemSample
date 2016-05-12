package varun.com.studentmanagementsystemsample.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class AttendanceFragment extends Fragment {

    InputStream inputStream;
    StringBuilder stringBuilder;
    String attendanceResponse;

    SessionManager sessionManager;

    ArrayList<Date> disableDateList;

    CaldroidFragment caldroidFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(AttendanceFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_attendance, container, false);

        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        args.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, true);
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar_frame_container, caldroidFragment);
        t.commit();

        Date date = new Date();

        caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.date_bg), date);
        caldroidFragment.setTextColorForDate(R.color.colorRed, date);


        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(AttendanceFragment.this.getActivity(), "" + date, Toast.LENGTH_SHORT).show();
            }
        });

        new ForAttendance().execute();

        return rootView;
    }

    class ForAttendance extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer childJsonStringer = new JSONStringer();

            try {

                childJsonStringer.object().key(Constants.KEY_USER_ID).value(sessionManager.getUserDetails().getUserId()).key(Constants.KEY_ATTENDANCE_DETAIL).value("").endObject();

                URL url = new URL(Api.ATTENDANCE_URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-type",
                        "application/json");
                conn.setRequestProperty("Accept",
                        "application/json");

                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        os, "UTF-8"));
                writer.write(childJsonStringer.toString());

                writer.flush();
                writer.close();
                os.close();

                inputStream = conn.getErrorStream();
                if (inputStream == null) {
                    inputStream = conn.getInputStream();
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream), 1000);
                stringBuilder = new StringBuilder();
                stringBuilder.append(reader.readLine() + "\n");

                String line = "0";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                inputStream.close();
                attendanceResponse = stringBuilder.toString();

            } catch (Exception e) {

                Log.e("LOGIN ERROR: ", "LOGIN ERROR: " + e);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            int userId = -1;

            try {
                JSONObject rootObject = new JSONObject(attendanceResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray attendanceResponseArray = rootObject.getJSONArray(Constants.KEY_ATTENDANCE_RESULT);

                    for (int i = 0; i < attendanceResponseArray.length(); i++) {
                        JSONObject attendanceResponseObject = (JSONObject) attendanceResponseArray.get(i);

                        userId = attendanceResponseObject.getInt(Constants.KEY_USER_ID);

                        JSONArray attendanceDetailArray = attendanceResponseObject.getJSONArray(Constants.KEY_ATTENDANCE_DETAIL);

                        for (int j = 0; i < attendanceDetailArray.length(); i++) {

                        }

                        studentID = childResponseObject.getInt(Constants.KEY_STUDENT_ID);
                        schoolID = childResponseObject.getInt(Constants.KEY_SCHOOL_ID);
                        avrageAttendance = childResponseObject.getInt(Constants.KEY_AVG_ATTENDANCE);
                        avragePerformance = childResponseObject.getInt(Constants.KEY_AVG_PERFORMANCE);
                        studentFirstName = childResponseObject.getString(Constants.KEY_STUDENT_FIRST_NAME);

                    }
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

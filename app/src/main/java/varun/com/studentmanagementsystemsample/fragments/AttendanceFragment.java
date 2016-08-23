package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import varun.com.studentmanagementsystemsample.MainActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.AttendanceBean;
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

    TextView total_present, total_absent, total_holidays;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(AttendanceFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_attendance, container, false);

        total_present = (TextView) rootView.findViewById(R.id.total_present);
        total_absent = (TextView) rootView.findViewById(R.id.total_absent);
        total_holidays = (TextView) rootView.findViewById(R.id.total_holiday);

        caldroidFragment = new CaldroidFragment();

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
            progressDialog = new ProgressDialog(AttendanceFragment.this.getActivity());
            progressDialog.setMessage("Getting Attendance...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer childJsonStringer = new JSONStringer();

            try {

                String userID = null, studentId = null, schoolId = null, classId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

//                    userID = "" + sessionManager.getUserDetails().getUserID();
                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

//                    userID = "" + sessionManager.getUserDetails().getUserID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();
                    classId = "" + sessionManager.getUserDetails().getClassID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

//                    userID = "" + sessionManager.getUserDetails().getUserID();
//                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
//                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
//                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();
                    classId = "" + sessionManager.getUserDetails().getClassID();
                }

                childJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(studentId).key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_CLASS_ID).value(classId).endObject();

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
            String attendanceDateStr, attendanceStatus;

            ArrayList<AttendanceBean> attendanceList = new ArrayList<>();

            try {
                JSONObject rootObject = new JSONObject(attendanceResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray attendanceResponseArray = rootObject.getJSONArray(Constants.KEY_ATTENDANCE_RESULT);

                    for (int i = 0; i < attendanceResponseArray.length(); i++) {
                        JSONObject attendanceResponseObject = (JSONObject) attendanceResponseArray.get(i);

                        userId = attendanceResponseObject.getInt(Constants.KEY_STUDENT_ID);

                        JSONArray attendanceDetailArray = attendanceResponseObject.getJSONArray(Constants.KEY_ATTENDANCE_DETAIL);

                        for (int j = 0; j < attendanceDetailArray.length(); j++) {
                            JSONObject attendanceDetailObject = attendanceDetailArray.getJSONObject(j);

                            attendanceDateStr = attendanceDetailObject.getString(Constants.KEY_ATTENDANCE_DATE);
                            attendanceStatus = attendanceDetailObject.getString(Constants.KEY_ATTENDANCE_STATUS);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date attendanceDate = simpleDateFormat.parse(attendanceDateStr);

                            Log.e("DATE: ", "DATE: " + attendanceDate);

                            attendanceList.add(new AttendanceBean(attendanceDate, attendanceStatus));
                        }
                    }

                    Map<Date, Drawable> attendanceBackgroundColorMap = new HashMap<>();
                    Map<Date, Integer> attendanceTextColorMap = new HashMap<>();

                    int totalPresentCount = 0;
                    int totalAbsentCount = 0;
                    int totalHolidayCount = 0;

                    for (int i = 0; i < attendanceList.size(); i++) {

                        if (attendanceList.get(i).getAttendanceStatus().equals("P")) {
                            totalPresentCount++;
                            attendanceBackgroundColorMap.put(attendanceList.get(i).getAttendanceDate(), getResources().getDrawable(R.drawable.white));
                            attendanceTextColorMap.put(attendanceList.get(i).getAttendanceDate(), R.color.caldroid_black);
                        } else if (attendanceList.get(i).getAttendanceStatus().equals("A")) {
                            totalAbsentCount++;
                            attendanceBackgroundColorMap.put(attendanceList.get(i).getAttendanceDate(), getResources().getDrawable(R.drawable.red));
                            attendanceTextColorMap.put(attendanceList.get(i).getAttendanceDate(), R.color.caldroid_white);
                        } else if (attendanceList.get(i).getAttendanceStatus().equals("H")) {
                            totalHolidayCount++;
                            attendanceBackgroundColorMap.put(attendanceList.get(i).getAttendanceDate(), getResources().getDrawable(R.drawable.white));
                            attendanceTextColorMap.put(attendanceList.get(i).getAttendanceDate(), R.color.colorGrey);
                        }
                    }

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
                    caldroidFragment.setBackgroundDrawableForDates(attendanceBackgroundColorMap);
                    caldroidFragment.setTextColorForDates(attendanceTextColorMap);
                    caldroidFragment.setMaxDate(new Date());

                    total_present.setText("" + totalPresentCount);
                    total_absent.setText("" + totalAbsentCount);
                    total_holidays.setText("" + totalHolidayCount);
                } else {
                    Toast.makeText(AttendanceFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

import varun.com.studentmanagementsystemsample.MainActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class TimeTableFragment extends Fragment {

    TableLayout tableLayout1, tableLayout2;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String timeTableResponse;

    ProgressDialog progressDialog;

    ArrayList<String> periods = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(TimeTableFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_time_table, container, false);

        tableLayout1 = (TableLayout) rootView.findViewById(R.id.table_layout_1);
        tableLayout2 = (TableLayout) rootView.findViewById(R.id.table_layout_2);

        addTableLayout1Rows(tableLayout1);

        new ForTimeTable().execute();

        return rootView;
    }

    class ForTimeTable extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TimeTableFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer timeTableJsonStringer = new JSONStringer();

            try {

                String schoolId = null, userId = null, classId = null, academicYearID = null, termID = null, sectionID = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();
                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();
                    academicYearID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getAcademicYearID();
                    termID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getTermID();
                    sectionID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSectionID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    classId = "" + sessionManager.getUserDetails().getClassID();
                    academicYearID = "" + sessionManager.getUserDetails().getAcademicYearID();
                    termID = "" + sessionManager.getUserDetails().getTermID();
                    sectionID = "" + sessionManager.getUserDetails().getSectionID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    userId = "" + sessionManager.getUserDetails().getUserID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    classId = "" + sessionManager.getUserDetails().getClassID();
                    academicYearID = "" + sessionManager.getUserDetails().getAcademicYearID();
                    termID = "" + sessionManager.getUserDetails().getTermID();
                    sectionID = "" + sessionManager.getUserDetails().getSectionID();
                }

                timeTableJsonStringer.object().key(Constants.KEY_USER_ID).value(userId).key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_CLASS_ID).value(classId).key(Constants.KEY_ACADEMIC_YEAR_ID).value(academicYearID).key(Constants.KEY_TIME_TABLE_TERM_ID).value(termID).key(Constants.KEY_SECTION_ID).value(sectionID).endObject();

                URL url = new URL(Api.TIME_TABLE_URL);

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
                writer.write(timeTableJsonStringer.toString());

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
                timeTableResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String timeTableID = null, userID = null, userTypeID = null, studentRegID = null, schoolID = null, termID = null, classID = null, date = null, day = null, periodOrder = null, subject = null;
            try {
                JSONObject rootObject = new JSONObject(timeTableResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray timeTableResponseArray = rootObject.getJSONArray(Constants.KEY_TIME_TABLE_RESULT);

                    for (int i = 0; i < timeTableResponseArray.length(); i++) {
                        JSONObject timeTableResponseObject = (JSONObject) timeTableResponseArray.get(i);

                        userID = timeTableResponseObject.getString(Constants.KEY_USER_ID);
                        userTypeID = timeTableResponseObject.getString(Constants.KEY_USER_TYPE);
                        studentRegID = timeTableResponseObject.getString(Constants.KEY_STUDENT_ID);
                        schoolID = timeTableResponseObject.getString(Constants.KEY_SCHOOL_ID);
                        timeTableID = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_ID);
                        termID = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_TERM_ID);
                        classID = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_CLASS_ID);
                        date = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_DATE);
                        periodOrder = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_PERIOD_ORDER);
                        subject = timeTableResponseObject.getString(Constants.KEY_TIME_TABLE_SUBJECT);

                        int pos = i + 1;
                        periods.add(i, subject);
                    }
                    addTableLayout2Rows(tableLayout2, periods);
                }
                progressDialog.dismiss();
            } catch (Exception e) {
                progressDialog.dismiss();
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }

    private void addTableLayout1Rows(TableLayout tableLayout1) {

//        tableLayout1.removeAllViews();

        TableRow tableRow1 = new TableRow(TimeTableFragment.this.getActivity());
        TextView textView1 = new TextView(TimeTableFragment.this.getActivity());

        tableRow1.setWeightSum(1);
        tableRow1.setBackgroundColor(Color.parseColor("#ffffff"));

        TableRow.LayoutParams tlp1 = new TableRow.LayoutParams(0,
                TableLayout.LayoutParams.WRAP_CONTENT, 1);

        textView1.setGravity(Gravity.CENTER);

        textView1.setText("10-35");
        textView1.setLayoutParams(tlp1);

        textView1.setTextSize(14f);

        textView1.setPadding(10, 25, 10, 25);

        tableRow1.addView(textView1);

        tableLayout1.addView(tableRow1);
    }

    private void addTableLayout2Rows(TableLayout tableLayout2, ArrayList<String> periods) {
//        tableLayout2.removeAllViews();

        TableRow tableRow2 = new TableRow(TimeTableFragment.this.getActivity());

        TextView textView21 = new TextView(TimeTableFragment.this.getActivity());
        TextView textView22 = new TextView(TimeTableFragment.this.getActivity());
        TextView textView23 = new TextView(TimeTableFragment.this.getActivity());
        TextView textView24 = new TextView(TimeTableFragment.this.getActivity());
        TextView textView25 = new TextView(TimeTableFragment.this.getActivity());
        TextView textView26 = new TextView(TimeTableFragment.this.getActivity());

        tableRow2.setWeightSum(6);
        tableRow2.setBackgroundColor(Color.parseColor("#ffffff"));

        TableRow.LayoutParams tlp21 = new TableRow.LayoutParams(0,
                TableLayout.LayoutParams.WRAP_CONTENT, 1);

        textView21.setGravity(Gravity.CENTER);
        textView22.setGravity(Gravity.CENTER);
        textView23.setGravity(Gravity.CENTER);
        textView24.setGravity(Gravity.CENTER);
        textView25.setGravity(Gravity.CENTER);
        textView26.setGravity(Gravity.CENTER);

        textView21.setText(periods.get(0));
        textView21.setLayoutParams(tlp21);

        textView22.setText(periods.get(1));
        textView22.setLayoutParams(tlp21);

        textView23.setText(periods.get(2));
        textView23.setLayoutParams(tlp21);

        textView24.setText(periods.get(3));
        textView24.setLayoutParams(tlp21);

        textView25.setText(periods.get(4));
        textView25.setLayoutParams(tlp21);

        textView26.setText(periods.get(5));
        textView26.setLayoutParams(tlp21);

        textView21.setTextSize(14f);

        textView21.setPadding(10, 25, 10, 25);

        tableRow2.addView(textView21);
        tableRow2.addView(textView22);
        tableRow2.addView(textView23);
        tableRow2.addView(textView24);
        tableRow2.addView(textView25);
        tableRow2.addView(textView26);

        tableLayout2.addView(tableRow2);
    }
}

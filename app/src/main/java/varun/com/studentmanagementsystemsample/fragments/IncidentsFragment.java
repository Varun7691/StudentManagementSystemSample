package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import varun.com.studentmanagementsystemsample.RaisingIncidentsActivity;
import varun.com.studentmanagementsystemsample.adapter.IncidentAdapter;
import varun.com.studentmanagementsystemsample.bean.IncidentOverviewBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class IncidentsFragment extends Fragment {

    RecyclerView incidentRecyclerView;
    FloatingActionButton incidentFab;
    ArrayList<IncidentOverviewBean> list;
    IncidentAdapter adapter;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String incidentResponse;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_incidents, container, false);

        sessionManager = new SessionManager(IncidentsFragment.this.getActivity());

        incidentRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvIncident);
        incidentFab = (FloatingActionButton) rootView.findViewById(R.id.incident_fab);

        incidentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncidentsFragment.this.getActivity(), RaisingIncidentsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        new ForIncidents().execute();
    }

    class ForIncidents extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(IncidentsFragment.this.getActivity());
            progressDialog.setMessage("Getting incidents...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer incidentJsonStringer = new JSONStringer();

            try {
//                incidentJsonStringer.object().key(Constants.KEY_USER_ID).value(sessionManager.getUserDetails().getUserId()).key(Constants.KEY_USER_TYPE).value(sessionManager.getUserDetails().getUserType()).key(Constants.KEY_SCHOOL_ID).value(MainActivity.schoolId).endObject();

                String schoolId = null, studentId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();

//                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
//                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                }

                incidentJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(studentId).key(Constants.KEY_SCHOOL_ID).value(schoolId).endObject();

                URL url = new URL(Api.INCIDENT_OVERVIEW_URL);

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
                writer.write(incidentJsonStringer.toString());

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
                incidentResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            try {
                JSONObject rootObject = new JSONObject(incidentResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray incidentResponseArray = rootObject.getJSONArray(Constants.KEY_INCIDENT_RESULT);

                    for (int i = 0; i < incidentResponseArray.length(); i++) {
                        JSONObject incidentResponseObject = (JSONObject) incidentResponseArray.get(i);

                        String userTypeID = incidentResponseObject.getString("userTypeID");
                        String userID = incidentResponseObject.getString("userID");
                        String incidentID = incidentResponseObject.getString("incidentID");
                        String incidentName = incidentResponseObject.getString("incidentName");
                        String incidentDescription = incidentResponseObject.getString("incidentDescription");
                        String incidentRemarks = incidentResponseObject.getString("incidentRemarks");
                        String studentRegID = incidentResponseObject.getString("studentRegID");
                        String incidentClassificationName = incidentResponseObject.getString("incidentClassificationName");
                        String incidentClassificationId = incidentResponseObject.getString("incidentClassificationId");
                        String isActive = incidentResponseObject.getString("isActive");
                        String schoolID = incidentResponseObject.getString("schoolID");
                        String incidentDate = incidentResponseObject.getString("incidentDate");
                        String incidentReportedDate = incidentResponseObject.getString("incidentReportedDate");
                        String incidentStatusID = incidentResponseObject.getString("incidentStatusID");
                        String reportedAgainstStaffID = incidentResponseObject.getString("reportedAgainstStaffID");
                        String reportedAgainstStaffName = incidentResponseObject.getString("reportedAgainstStaffName");
                        String reportedAgainstStudentID = incidentResponseObject.getString("reportedAgainstStudentID");
                        String reportedAgainstStudentName = incidentResponseObject.getString("reportedAgainstStudentName");
                        String reportedByStudentID = incidentResponseObject.getString("reportedByStudentID");
                        String reportedByStudentName = incidentResponseObject.getString("reportedByStudentName");
                        String againstName = incidentResponseObject.getString("againstName");

                        list.add(new IncidentOverviewBean(userTypeID, userID, incidentID, incidentName, incidentDescription, incidentRemarks, studentRegID, incidentClassificationName, incidentClassificationId, isActive, schoolID, incidentDate, incidentReportedDate, incidentStatusID, reportedAgainstStaffID, reportedAgainstStaffName, reportedAgainstStudentID, reportedAgainstStudentName, reportedByStudentID, reportedByStudentName, againstName));
                    }

                    adapter = new IncidentAdapter(IncidentsFragment.this.getActivity(), list);
                    incidentRecyclerView.setAdapter(adapter);
                    incidentRecyclerView.setLayoutManager(new LinearLayoutManager(IncidentsFragment.this.getActivity()));

                    RecyclerView.ItemDecoration itemDecoration = new
                            DividerItemDecoration(IncidentsFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
                    incidentRecyclerView.addItemDecoration(itemDecoration);
                } else {
                    Toast.makeText(IncidentsFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            } catch (Exception e) {
                progressDialog.dismiss();
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

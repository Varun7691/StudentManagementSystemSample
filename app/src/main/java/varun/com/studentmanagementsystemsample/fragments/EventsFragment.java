package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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
import varun.com.studentmanagementsystemsample.adapter.EventRecyclerAdapter;
import varun.com.studentmanagementsystemsample.bean.EventBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class EventsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<EventBean> list;
    EventRecyclerAdapter adapter;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String eventResponse;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(EventsFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        new ForEvent().execute();

        return rootView;
    }

    class ForEvent extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(EventsFragment.this.getActivity());
            progressDialog.setMessage("Getting events...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer eventJsonStringer = new JSONStringer();

            try {
//                eventJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(MainActivity.studentId).key(Constants.KEY_CLASS_ID).value("3").endObject();

                String userID = null, studentId = null, userSpecificId = null, userType = null, classId = null, sectionId = null, schoolId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    userID = "" + sessionManager.getUserDetails().getUserID();
                    userSpecificId = "" + sessionManager.getUserDetails().getUserSpecificID();
                    userType = "" + sessionManager.getUserDetails().getUserTypeID();
                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();
                    sectionId = "41";

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    userID = "" + sessionManager.getStudentDetails().getUserID();
                    userSpecificId = "" + sessionManager.getStudentDetails().getUserSpecificID();
                    userType = "" + sessionManager.getUserDetails().getUserTypeID();
                    schoolId = "" + sessionManager.getStudentDetails().getSchoolID();
                    studentId = "" + sessionManager.getStudentDetails().getStudentRegID();
                    classId = "" + sessionManager.getStudentDetails().getClassID();
                    sectionId = "" + sessionManager.getStudentDetails().getSectionID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    userID = "" + sessionManager.getUserDetails().getUserID();
                    userSpecificId = "" + sessionManager.getUserDetails().getUserSpecificID();
                    userType = "" + sessionManager.getUserDetails().getUserTypeID();
                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();
                    sectionId = "41";
                }

                eventJsonStringer.object().key(Constants.KEY_USER_ID).value(userID).key(Constants.KEY_STUDENT_ID).value(studentId).key(Constants.KEY_USER_SPECIFIC_ID).value(userSpecificId).key(Constants.KEY_USER_TYPE).value(userType).key(Constants.KEY_CLASS_ID).value(classId).key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_SECTION_ID).value(sectionId).endObject();

                URL url = new URL(Api.EVENTS_URL);

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
                writer.write(eventJsonStringer.toString());

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
                eventResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String startDate = null, endDate = null, title = null, description = null;

            try {
                JSONObject rootObject = new JSONObject(eventResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray eventResponseArray = rootObject.getJSONArray(Constants.KEY_EVENT_RESULT);

                    for (int i = 0; i < eventResponseArray.length(); i++) {
                        JSONObject eventResponseObject = (JSONObject) eventResponseArray.get(i);

                        startDate = eventResponseObject.getString(Constants.KEY_EVENT_START_DATE);
                        endDate = eventResponseObject.getString(Constants.KEY_EVENT_END_DATE);
                        title = eventResponseObject.getString(Constants.KEY_EVENT_TITLE);
                        description = eventResponseObject.getString(Constants.KEY_EVENT_DESCRIPTION);

                        list.add(new EventBean("" + i, title, startDate, endDate));
                    }

                    adapter = new EventRecyclerAdapter(EventsFragment.this.getActivity(), list);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(EventsFragment.this.getActivity()));
                } else {
                    Toast.makeText(EventsFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

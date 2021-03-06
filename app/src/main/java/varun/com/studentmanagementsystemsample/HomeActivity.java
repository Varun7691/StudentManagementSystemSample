package varun.com.studentmanagementsystemsample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

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

import varun.com.studentmanagementsystemsample.adapter.HomeAdapter;
import varun.com.studentmanagementsystemsample.bean.HomeBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvHome;
    ArrayList<HomeBean> list;
    HomeAdapter adapter;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String childResponse;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Intelekt");

        sessionManager = new SessionManager(HomeActivity.this);

        rvHome = (RecyclerView) findViewById(R.id.rvHome);

        new ForChild().execute();

//        RecyclerView.ItemDecoration itemDecoration = new
//                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        rvHome.addItemDecoration(itemDecoration);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    class ForChild extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer childJsonStringer = new JSONStringer();

            try {

                childJsonStringer.object().key(Constants.KEY_USER_ID).value(sessionManager.getUserDetails().getUserID()).key(Constants.KEY_SCHOOL_ID).value(sessionManager.getUserDetails().getSchoolID()).endObject();

                URL url = new URL(Api.GET_CHILDREN_URL);

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
                childResponse = stringBuilder.toString();

            } catch (Exception e) {

                Log.e("CHILD ERROR: ", "CHILD ERROR: " + e);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String studentFirstName = null, avragePerformance = null;
            int studentID = -1, schoolID = -1, avrageAttendance = -1, classID = -1, academicYearID = -1, userId = -1, termID = -1, sectionID = -1;

            try {
                JSONObject rootObject = new JSONObject(childResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray childResponseArray = rootObject.getJSONArray(Constants.KEY_OBJ_CHILD_RESULT);

                    for (int i = 0; i < childResponseArray.length(); i++) {
                        JSONObject childResponseObject = (JSONObject) childResponseArray.get(i);

                        studentID = childResponseObject.getInt(Constants.KEY_STUDENT_ID);
                        schoolID = childResponseObject.getInt(Constants.KEY_SCHOOL_ID);
                        avrageAttendance = childResponseObject.getInt(Constants.KEY_AVG_ATTENDANCE);
//                        avragePerformance = childResponseObject.getInt(Constants.KEY_AVG_PERFORMANCE);
                        avragePerformance = childResponseObject.getString(Constants.KEY_AVG_PERFORMANCE);
                        studentFirstName = childResponseObject.getString(Constants.KEY_STUDENT_FIRST_NAME);
                        academicYearID = childResponseObject.getInt(Constants.KEY_ACADEMIC_YEAR_ID);
                        classID = childResponseObject.getInt(Constants.KEY_CLASS_ID);
                        userId = childResponseObject.getInt("userId");
                        termID = childResponseObject.getInt("termID");
                        sectionID = childResponseObject.getInt("sectionID");

                        list.add(new HomeBean(studentID, schoolID, avrageAttendance, avragePerformance, studentFirstName, academicYearID, classID, userId, termID, sectionID));
                    }

                    adapter = new HomeAdapter(HomeActivity.this, list);
                    rvHome.setAdapter(adapter);
                    rvHome.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

                    sessionManager.setStudentList(list);

                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }

            progressDialog.dismiss();
        }
    }
}

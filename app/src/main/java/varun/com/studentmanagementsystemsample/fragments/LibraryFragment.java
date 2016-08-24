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
import varun.com.studentmanagementsystemsample.adapter.LibraryAdapter;
import varun.com.studentmanagementsystemsample.bean.LibraryBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class LibraryFragment extends Fragment {

    RecyclerView rvLibrary;
    ArrayList<LibraryBean> list;
    LibraryAdapter adapter;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String libraryResponse;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(LibraryFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        rvLibrary = (RecyclerView) rootView.findViewById(R.id.rvLibrary);

        new ForLibrary().execute();

        return rootView;
    }

    class ForLibrary extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(LibraryFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer eventJsonStringer = new JSONStringer();

            try {

                String schoolId = null, userId = null, studentRegID = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentRegID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentRegID = "" + sessionManager.getUserDetails().getStudentRegID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentRegID = "" + sessionManager.getUserDetails().getStudentRegID();

                }


                eventJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(studentRegID).key(Constants.KEY_SCHOOL_ID).value(schoolId).endObject();

                URL url = new URL(Api.LIBRARY_URL);

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
                libraryResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String userID = null, userTypeID = null, studentRegID = null, schoolID = null, allocatedDate = null, DueDate = null, allocatedTo = null, lateCharges = null, authorName = null, extendedDay = null, bookName = null, lateReturnChergePerDay = null, defaulAllocatedDay = null, maximumNoOfExtn = null, allotedExtension = null;

            try {
                JSONObject rootObject = new JSONObject(libraryResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray LibraryResponseArray = rootObject.getJSONArray(Constants.KEY_LIBRARY_RESULT);

                    for (int i = 0; i < LibraryResponseArray.length(); i++) {
                        JSONObject libraryResponseObject = (JSONObject) LibraryResponseArray.get(i);

                        userID = libraryResponseObject.getString(Constants.KEY_USER_ID);
                        userTypeID = libraryResponseObject.getString(Constants.KEY_USER_TYPE);
                        studentRegID = libraryResponseObject.getString(Constants.KEY_STUDENT_ID);
                        schoolID = libraryResponseObject.getString(Constants.KEY_SCHOOL_ID);
                        allocatedDate = libraryResponseObject.getString(Constants.KEY_ALLOCATED_DATE);
                        DueDate = libraryResponseObject.getString(Constants.KEY_DUE_DATE);
                        allocatedTo = libraryResponseObject.getString(Constants.KEY_ALLOCATED_TO);
                        lateCharges = libraryResponseObject.getString(Constants.KEY_LATE_CHARGES);
                        authorName = libraryResponseObject.getString(Constants.KEY_AUTHOR_NAME);
                        extendedDay = libraryResponseObject.getString(Constants.KEY_EXTENDED_DAY);
                        bookName = libraryResponseObject.getString(Constants.KEY_BOOK_NAME);
                        lateReturnChergePerDay = libraryResponseObject.getString(Constants.KEY_LATE_RETURN_CHERGE_PER_DAY);
                        defaulAllocatedDay = libraryResponseObject.getString(Constants.KEY_DEFAUL_ALLOCATED_DAY);
                        maximumNoOfExtn = libraryResponseObject.getString(Constants.KEY_MAXIMUM_NO_OF_EXTN);
                        allotedExtension = libraryResponseObject.getString(Constants.KEY_ALLOTED_EXTENSION);

                        list.add(new LibraryBean(userID, userTypeID, studentRegID, schoolID, allocatedDate, DueDate, allocatedTo, lateCharges, authorName, extendedDay, bookName, lateReturnChergePerDay, defaulAllocatedDay, maximumNoOfExtn, allotedExtension));
                    }

                    adapter = new LibraryAdapter(LibraryFragment.this.getActivity(), list);

                    rvLibrary.setAdapter(adapter);
                    rvLibrary.setLayoutManager(new LinearLayoutManager(LibraryFragment.this.getActivity()));
                    RecyclerView.ItemDecoration itemDecoration = new
                            DividerItemDecoration(LibraryFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
                    rvLibrary.addItemDecoration(itemDecoration);
                } else {
                    Toast.makeText(LibraryFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

package varun.com.studentmanagementsystemsample.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
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
import android.widget.EditText;
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
import varun.com.studentmanagementsystemsample.adapter.TodoAdapter;
import varun.com.studentmanagementsystemsample.bean.LibraryBean;
import varun.com.studentmanagementsystemsample.bean.TodoBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.database.TodoListSQLHelper;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class ToDoListFragment extends Fragment {

    RecyclerView rvTodoList;
    ArrayList<TodoBean> list;
    TodoAdapter adapter;
    FloatingActionButton todo_fab;
    private TodoListSQLHelper todoListSQLHelper;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String todoListResponse;

    ProgressDialog progressDialog;

    String titleStr, descriptionStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        sessionManager = new SessionManager(ToDoListFragment.this.getActivity());

        rvTodoList = (RecyclerView) rootView.findViewById(R.id.rvTodoList);
        todo_fab = (FloatingActionButton) rootView.findViewById(R.id.todo_fab);

        new ForTodoList().execute();

        todo_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });

        return rootView;
    }

    class ForTodoList extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(ToDoListFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer eventJsonStringer = new JSONStringer();

            try {

                String userId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    userId = "" + sessionManager.getUserDetails().getUserID();
                }

                eventJsonStringer.object().key(Constants.KEY_USER_ID).value(userId).endObject();

                URL url = new URL(Api.TODO_LIST_URL);

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
                todoListResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String todoListID = null, userID = null, userTypeID = null, studentRegID = null, schoolID = null, title = null, description = null, status = null, isActive = null, dtCreated = null;

            try {
                JSONObject rootObject = new JSONObject(todoListResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray todoListResponseArray = rootObject.getJSONArray(Constants.KEY_TODO_LIST_RESULT);

                    for (int i = 0; i < todoListResponseArray.length(); i++) {
                        JSONObject todoListResponseObject = (JSONObject) todoListResponseArray.get(i);

                        todoListID = todoListResponseObject.getString(Constants.KEY_TODO_LIST_ID);
                        userID = todoListResponseObject.getString(Constants.KEY_USER_ID);
                        studentRegID = todoListResponseObject.getString(Constants.KEY_STUDENT_ID);
                        status = todoListResponseObject.getString(Constants.KEY_STATUS);
                        isActive = todoListResponseObject.getString(Constants.KEY_INCIDENT_IS_ACITVE);
                        dtCreated = todoListResponseObject.getString(Constants.KEY_DT_CREATED);
                        title = todoListResponseObject.getString(Constants.KEY_TODO_LIST_TITLE);
                        description = todoListResponseObject.getString(Constants.KEY_TODO_LIST_DESCRIPTION);

                        list.add(new TodoBean(todoListID, userID, studentRegID, status, isActive, title, description, dtCreated));
                    }

                    adapter = new TodoAdapter(ToDoListFragment.this.getActivity(), list);

                    rvTodoList.setAdapter(adapter);
                    rvTodoList.setLayoutManager(new LinearLayoutManager(ToDoListFragment.this.getActivity()));
                    RecyclerView.ItemDecoration itemDecoration = new
                            DividerItemDecoration(ToDoListFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
                    rvTodoList.addItemDecoration(itemDecoration);
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }

            progressDialog.dismiss();
        }
    }

    class ForAddingTodo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(ToDoListFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer addTodoJsonStringer = new JSONStringer();

            try {

                String userId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    userId = "" + sessionManager.getUserDetails().getUserID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    userId = "" + sessionManager.getUserDetails().getUserID();
                }


                addTodoJsonStringer.object().key(Constants.KEY_USER_ID).value(userId).key(Constants.KEY_TODO_LIST_TITLE).value(titleStr).key(Constants.KEY_TODO_LIST_DESCRIPTION).value(descriptionStr).key(Constants.KEY_STATUS).value("1").endObject();

                URL url = new URL(Api.ADD_TODO_URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setRequestProperty("Accept", "application/json");

                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        os, "UTF-8"));
                writer.write(addTodoJsonStringer.toString());

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
                todoListResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONObject rootObject = new JSONObject(todoListResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String statusMsg = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {

                    new ForTodoList().execute();

                    Toast.makeText(ToDoListFragment.this.getActivity(), "" + statusMsg, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etTitle = (EditText) alertLayout.findViewById(R.id.et_Title);
        final EditText etDescription = (EditText) alertLayout.findViewById(R.id.et_Description);

        AlertDialog.Builder alert = new AlertDialog.Builder(ToDoListFragment.this.getActivity());
        alert.setTitle("Add Task");
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                titleStr = etTitle.getText().toString();
                descriptionStr = etDescription.getText().toString();

                if (!titleStr.isEmpty() || !descriptionStr.isEmpty()) {
                    new ForAddingTodo().execute();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


}

package varun.com.studentmanagementsystemsample.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import varun.com.studentmanagementsystemsample.bean.TodoBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.fragments.ToDoListFragment;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    ArrayList<TodoBean> list;
    Context context;
    LayoutInflater inflater;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String todoListResponse;

    ProgressDialog progressDialog;

    String descriptionStr, titleStr, todoId;

    public TodoAdapter(Context context, ArrayList<TodoBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sessionManager = new SessionManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.todo_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDescription());
        holder.todoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog(position, list.get(position).getTitle(), list.get(position).getDescription());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void displayAlertDialog(final int position, final String title, final String desc) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etTitle = (EditText) alertLayout.findViewById(R.id.et_Title);
        final EditText etDescription = (EditText) alertLayout.findViewById(R.id.et_Description);

        etTitle.setText(title);
        etDescription.setText(desc);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Add Task");
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                todoId = list.get(position).getTodoListID();
                titleStr = etTitle.getText().toString();
                descriptionStr = etDescription.getText().toString();

                if (!title.isEmpty() || !descriptionStr.isEmpty()) {
                    new ForUpdatingTodo().execute();
                }
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public LinearLayout todoContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.todo_title);
            desc = (TextView) itemView.findViewById(R.id.todo_desc);

            todoContainer = (LinearLayout) itemView.findViewById(R.id.todo_container);
        }
    }

    class ForUpdatingTodo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(context);
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

                addTodoJsonStringer.object().key(userId).value(todoId).key(Constants.KEY_USER_ID).value(sessionManager.getUserDetails().getUserID()).key(Constants.KEY_TODO_LIST_TITLE).value(titleStr).key(Constants.KEY_TODO_LIST_DESCRIPTION).value(descriptionStr).key(Constants.KEY_STATUS).value("1").endObject();

                URL url = new URL(Api.UPDATE_TODO_URL);

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
                    Toast.makeText(context, "" + statusMsg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "" + statusMsg, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

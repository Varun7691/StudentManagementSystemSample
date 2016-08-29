package varun.com.studentmanagementsystemsample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;

import varun.com.studentmanagementsystemsample.adapter.IncidentClassificationSpinnerAdapter;
import varun.com.studentmanagementsystemsample.adapter.UserAutoCompleteAdapter;
import varun.com.studentmanagementsystemsample.bean.IncidentClassificationbean;
import varun.com.studentmanagementsystemsample.bean.UserTypeBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

public class RaisingIncidentsActivity extends AppCompatActivity {

    Spinner incident_user_type, incident_nature_type;
    TextView incident_date, incident_time;
    EditText incident_description, incident_remarks;
    AutoCompleteTextView incident_reporting_by, incident_reporting_against;
    Button incident_create_button;

    String[] userTypeArray = new String[]{"Student", "Staff"};

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String userResponse;

    String againstNameStr, incidentResponse;

    ArrayList<IncidentClassificationbean> incidentClassificationBeanArrayList;
    ArrayList<UserTypeBean> userAutoCompleteList;

    UserAutoCompleteAdapter adapter;
    IncidentClassificationSpinnerAdapter incidentClassificationSpinnerAdapter;

    boolean isUserAgainst;

    static ForUser exec;

    int year, month, day;

    ProgressDialog progressDialog;

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            // arg1 = year
            // arg2 = month
            // arg3 = day

            incident_date.setText(day + "/" + month + "/" + year);
        }
    };

    String userTypeIDStr, reportedAgainstStudentIDStr, incidentDescriptionStr, incidentRemarksStr, reportedAgainstStaffIDStr, reportedByStaffIDStr, incidentDateStr, incidentTimeStr, incidentNatureIdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raising_incidents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new ForIncidentsClassification().execute();

        sessionManager = new SessionManager(RaisingIncidentsActivity.this);

        incident_user_type = (Spinner) findViewById(R.id.incident_user_type);
        incident_nature_type = (Spinner) findViewById(R.id.incident_nature_type);
        incident_date = (TextView) findViewById(R.id.incident_date);
        incident_time = (TextView) findViewById(R.id.incident_time);
        incident_description = (EditText) findViewById(R.id.incident_description);
        incident_remarks = (EditText) findViewById(R.id.incident_remarks);
        incident_reporting_by = (AutoCompleteTextView) findViewById(R.id.incident_reporting_by);
        incident_reporting_against = (AutoCompleteTextView) findViewById(R.id.incident_reporting_against);
        incident_create_button = (Button) findViewById(R.id.incident_create_button);

        Calendar mcurrentTime = Calendar.getInstance();
        year = mcurrentTime.get(Calendar.YEAR);
        month = mcurrentTime.get(Calendar.MONTH);
        day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

        incident_nature_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IncidentClassificationbean incidentClassificationbean = (IncidentClassificationbean) parent.getItemAtPosition(position);
                incidentNatureIdStr = incidentClassificationbean.getIncidentClassificationId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RaisingIncidentsActivity.this, R.layout.incident_classification_spinner_layout, R.id.incident_classification_spinner_name, userTypeArray);
        incident_user_type.setAdapter(adapter);

        incident_user_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String userTypeStr = parent.getItemAtPosition(position).toString();
                Log.e("USER TYPE: ", "USER TYPE: " + userTypeStr);
                if (userTypeStr.equalsIgnoreCase("Student")) {
                    userTypeIDStr = "2";
                } else if (userTypeStr.equalsIgnoreCase("Staff")) {
                    userTypeIDStr = "1";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        incident_reporting_by.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserAgainst = false;
                againstNameStr = s.toString();
                if (exec != null) {
                    exec.cancel(true);
                }

                exec = new ForUser();
                exec.execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        incident_reporting_by.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                         @Override
                                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                             UserTypeBean userTypeBean = (UserTypeBean) parent.getItemAtPosition(position);
                                                             if (userTypeIDStr.equalsIgnoreCase("1")) {
                                                                 reportedByStaffIDStr = userTypeBean.getId();
                                                             } else if (userTypeIDStr.equalsIgnoreCase("2")) {
                                                                 //reportedAgainstStudentIDStr = userTypeBean.getId();
                                                             }
                                                             incident_reporting_by.setText(userTypeBean.getValue());

                                                         }
                                                     }
        );

        incident_reporting_against.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserAgainst = true;
                againstNameStr = s.toString();
                if (exec != null) {
                    exec.cancel(true);
                }

                exec = new ForUser();
                exec.execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        incident_reporting_against.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserTypeBean userTypeBean = (UserTypeBean) parent.getItemAtPosition(position);
                if (userTypeIDStr.equalsIgnoreCase("1")) {
                    reportedAgainstStaffIDStr = userTypeBean.getId();
                } else if (userTypeIDStr.equalsIgnoreCase("2")) {
                    reportedAgainstStudentIDStr = userTypeBean.getId();
                }
                incident_reporting_against.setText(userTypeBean.getValue());
            }
        });

        incident_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        incident_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(RaisingIncidentsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        incident_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Incident Time");
                mTimePicker.show();
            }
        });

        incident_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incidentDescriptionStr = incident_description.getText().toString();
                incidentRemarksStr = incident_remarks.getText().toString();
                incidentDateStr = incident_date.getText().toString();
                incidentTimeStr = incident_time.getText().toString();

                new ForRaisingIncident().execute();

            }
        });
    }

    class ForIncidentsClassification extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            incidentClassificationBeanArrayList = new ArrayList<>();
            progressDialog = new ProgressDialog(RaisingIncidentsActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer incidentJsonStringer = new JSONStringer();

            try {
                String schoolId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

//                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                }

                incidentJsonStringer.object().key(Constants.KEY_SCHOOL_ID).value(schoolId).endObject();

                URL url = new URL(Api.INCIDENT_CLASSIFICATION_URL);

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

            String incidentClassificationId = null, incidentClassificationName = null;

            try {
                JSONObject rootObject = new JSONObject(incidentResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray incidentResponseArray = rootObject.getJSONArray(Constants.KEY_INCIDENT_CLASSIFICATION_RESULT);

                    for (int i = 0; i < incidentResponseArray.length(); i++) {
                        JSONObject incidentResponseObject = (JSONObject) incidentResponseArray.get(i);

                        incidentClassificationId = incidentResponseObject.getString(Constants.KEY_ID);
                        incidentClassificationName = incidentResponseObject.getString(Constants.KEY_VALUE);

                        incidentClassificationBeanArrayList.add(new IncidentClassificationbean(incidentClassificationId, incidentClassificationName));
                    }
                    if (incidentClassificationBeanArrayList != null) {
                        incidentClassificationSpinnerAdapter = new IncidentClassificationSpinnerAdapter(RaisingIncidentsActivity.this, incidentClassificationBeanArrayList);
                        incident_nature_type.setAdapter(incidentClassificationSpinnerAdapter);
                    }
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
            progressDialog.dismiss();
        }
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    class ForUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            userAutoCompleteList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer incidentJsonStringer = new JSONStringer();

            try {

                String schoolId = null, userId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

//                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                }

                userId = userTypeIDStr;

                incidentJsonStringer.object().key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_USER_TYPE).value(userId).key("againstName").value(againstNameStr).endObject();

                URL url = new URL(Api.USER_AUTOCOMPLETE_URL);

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
                userResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String id = null, value = null;

            try {
                JSONObject rootObject = new JSONObject(userResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray incidentResponseArray = rootObject.getJSONArray(Constants.KEY_INCIDENT_AGAIST_NAME_RESULT);

                    for (int i = 0; i < incidentResponseArray.length(); i++) {
                        JSONObject incidentResponseObject = (JSONObject) incidentResponseArray.get(i);

                        id = incidentResponseObject.getString(Constants.KEY_ID);
                        value = incidentResponseObject.getString(Constants.KEY_VALUE);

                        userAutoCompleteList.add(new UserTypeBean(id, value));
                    }

                    adapter = new UserAutoCompleteAdapter(RaisingIncidentsActivity.this, userAutoCompleteList);
                    if (isUserAgainst) {
                        incident_reporting_against.setAdapter(adapter);
                    } else {
                        incident_reporting_by.setAdapter(adapter);
                    }
                    adapter.notifyDataSetChanged();

                    incident_reporting_by.showDropDown();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }

    class ForRaisingIncident extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            userAutoCompleteList = new ArrayList<>();
            progressDialog = new ProgressDialog(RaisingIncidentsActivity.this);
            progressDialog.setMessage("Creating incidents...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer incidentJsonStringer = new JSONStringer();

            try {

                String schoolId = null, userId = null, incidentID = null, userTypeID = null, reportedAgainstStudentID = null, incidentDescription = null, incidentRemarks = null, reportedAgainstStaffID = null, reportedByStaffID = null, incidentDate = null, incidentTime = null, studentRegID = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    userId = "" + sessionManager.getUserDetails().getUserID();
                    studentRegID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    userId = "" + sessionManager.getUserDetails().getUserID();
                    studentRegID = "" + sessionManager.getUserDetails().getStudentRegID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

//                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    userId = "" + sessionManager.getUserDetails().getUserID();
                    studentRegID = "" + sessionManager.getUserDetails().getStudentRegID();

                }

                incidentID = incidentNatureIdStr;
                userTypeID = userTypeIDStr;
                incidentDescription = incidentDescriptionStr;
                incidentRemarks = incidentRemarksStr;
                reportedAgainstStaffID = reportedAgainstStaffIDStr;
                reportedByStaffID = reportedByStaffIDStr;
                reportedAgainstStudentID = reportedAgainstStudentIDStr;
                incidentDate = "17-august-2016";
                incidentTime = "11:15 AM";

                if (reportedAgainstStaffID == null) {
                    reportedAgainstStaffID = "0";
                }

                if (reportedByStaffID == null) {
                    reportedByStaffID = "0";
                }

                if (reportedAgainstStudentID == null) {
                    reportedAgainstStudentID = "0";
                }

                incidentJsonStringer.object().key("incidentID").value(incidentID).key("userID").value(userId).key("userTypeID").value(userTypeID).key("reportedAgainstStudentID").value(reportedAgainstStudentID).key("incidentDescription").value(incidentDescription).key("incidentRemarks").value(incidentRemarks).key("reportedAgainstStaffID").value(reportedAgainstStaffID).key("reportedByStaffID").value(reportedByStaffID).key("incidentDate").value(incidentDate).key("incidentTime").value(incidentTime).key("studentRegID").value(studentRegID).key("schoolID").value(schoolId).endObject();

                URL url = new URL(Api.CREATE_INCIDENT_URL);

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
                userResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                JSONObject rootObject = new JSONObject(userResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_RECORD_INSERTED_SCCESSFULLY) {
                    Toast.makeText(RaisingIncidentsActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }

            progressDialog.dismiss();
        }
    }
}

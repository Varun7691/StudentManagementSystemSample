package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

import varun.com.studentmanagementsystemsample.CoScholasticActivityNew;
import varun.com.studentmanagementsystemsample.HealthStatusActivity;
import varun.com.studentmanagementsystemsample.MainActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.ScholasticActivity;
import varun.com.studentmanagementsystemsample.SelfAwarenessActivity;
import varun.com.studentmanagementsystemsample.bean.CoScholasticBean;
import varun.com.studentmanagementsystemsample.bean.CoScholasticChildItemBean;
import varun.com.studentmanagementsystemsample.bean.HealthReportBean;
import varun.com.studentmanagementsystemsample.bean.ScholasticBean;
import varun.com.studentmanagementsystemsample.bean.SelfAwarnessReportBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class StudentReportFragment extends Fragment {

    RelativeLayout scholastic, coscholastic, healthStatus, selfAwareness;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String studentReportResponse;

    ProgressDialog progressDialog;

    HealthReportBean healthReportBean;
    SelfAwarnessReportBean selfAwarnessReportBean;

    ArrayList<CoScholasticChildItemBean> coScholasticChildItemBean;
    ArrayList<CoScholasticBean> coScholasticBean;

    ArrayList<ScholasticBean> scholasticBean;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student_report, container, false);

        sessionManager = new SessionManager(StudentReportFragment.this.getActivity());

        scholastic = (RelativeLayout) rootView.findViewById(R.id.scholastic_container);
        coscholastic = (RelativeLayout) rootView.findViewById(R.id.co_scholastic_container);
        healthStatus = (RelativeLayout) rootView.findViewById(R.id.health_status_container);
        selfAwareness = (RelativeLayout) rootView.findViewById(R.id.self_awareness_container);

        scholastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentReportFragment.this.getActivity(), ScholasticActivity.class);
                intent.putExtra("SCHOLASTIC LIST", scholasticBean);
                startActivity(intent);
            }
        });

        coscholastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentReportFragment.this.getActivity(), CoScholasticActivityNew.class);
                intent.putExtra("COSCHOLASTIC LIST", coScholasticChildItemBean);
                startActivity(intent);
            }
        });

        healthStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentReportFragment.this.getActivity(), HealthStatusActivity.class);
                intent.putExtra("HEALTH BEAN", healthReportBean);
                startActivity(intent);
            }
        });

        selfAwareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentReportFragment.this.getActivity(), SelfAwarenessActivity.class);
                intent.putExtra("SELF AWARENESS BEAN", selfAwarnessReportBean);
                startActivity(intent);
            }
        });

        new ForStudentReport().execute();

        return rootView;
    }


    class ForStudentReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            healthReportBean = new HealthReportBean();
            selfAwarnessReportBean = new SelfAwarnessReportBean();

            coScholasticChildItemBean = new ArrayList<>();
            coScholasticBean = new ArrayList<>();

            scholasticBean = new ArrayList<>();

            progressDialog = new ProgressDialog(StudentReportFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer feePaymentJsonStringer = new JSONStringer();

            try {

                String schoolId = null, studentId = null, academicYearID = null, classId = null, sectionID = null, termId = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    academicYearID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getAcademicYearID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();
                    sectionID = "41";
                    termId = "1";

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getStudentDetails().getSchoolID();
                    studentId = "" + sessionManager.getStudentDetails().getStudentRegID();
                    academicYearID = "1";
                    classId = "" + sessionManager.getStudentDetails().getClassID();
                    sectionID = "41";
                    termId = "1";

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    academicYearID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getAcademicYearID();
                    classId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getClassID();
                    sectionID = "41";
                    termId = "1";
                }

                feePaymentJsonStringer.object().key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_STUDENT_ID).value(studentId).key(Constants.KEY_ACADEMIC_YEAR_ID).value(academicYearID).key(Constants.KEY_CLASS_ID).value(classId).key(Constants.KEY_SECTION_ID).value(sectionID).key(Constants.KEY_TIME_TABLE_TERM_ID).value(termId).endObject();

                URL url = new URL(Api.STUDENT_REPORT_URL);

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
                writer.write(feePaymentJsonStringer.toString());

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
                studentReportResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("STUDENT REPORT ERROR: ", "STUDENT REPORT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String studentRegID = null, subject = null, academicYear = null, term = null, board = null, FA1 = null, FA2 = null, totalFA = null, SA1 = null, totalFASA1 = null, rank = null, typeDesc = null, description = null, remark = null, grade = null, hieght = null, weight = null, bloodGroup = null, dental = null, visionLeft = null, visionRight = null, goal = null, strength = null, interestHobbies = null, responsibilityDischarged_exceptionalAchievements = null;

            try {
                JSONObject rootObject = new JSONObject(studentReportResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray scholasticResultResponseArray = rootObject.getJSONArray(Constants.KEY_SCHOLASTIC_RESULT);

                    for (int i = 0; i < scholasticResultResponseArray.length(); i++) {
                        JSONObject scholasticResultResponseObject = (JSONObject) scholasticResultResponseArray.get(i);

                        studentRegID = scholasticResultResponseObject.getString(Constants.KEY_STUDENT_ID);
                        subject = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_SUBJECT);
                        academicYear = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_ACADEMIC_YEAR);
                        term = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_TERM);
                        board = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_BOARD);
                        FA1 = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_FA1);
                        FA2 = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_FA2);
                        totalFA = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_TOTAL_FA);
                        SA1 = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_SA1);
                        totalFASA1 = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_TOTAL_FA_SA1);
                        rank = scholasticResultResponseObject.getString(Constants.KEY_PERFORMANCE_RANK);

                        scholasticBean.add(new ScholasticBean(studentRegID, subject, academicYear, term, board, FA1, FA2, totalFA, SA1, totalFASA1, rank));
                    }

                    JSONArray coscholasticResultResponseArray = rootObject.getJSONArray(Constants.KEY_COSCHOLASTIC_RESULT);
                    for (int i = 0; i < coscholasticResultResponseArray.length(); i++) {
                        JSONObject coscholasticResultResponseObject = (JSONObject) coscholasticResultResponseArray.get(i);

                        JSONArray childCoscholasticResultResponseArray = coscholasticResultResponseObject.getJSONArray(Constants.KEY_COSCHOLASTIC_LIST_RESULT);
                        typeDesc = coscholasticResultResponseObject.getString(Constants.KEY_TYPE_DESC);

                        for (int j = 0; j < childCoscholasticResultResponseArray.length(); j++) {
                            JSONObject lifeSkillcoscholasticResultResponseObject = (JSONObject) childCoscholasticResultResponseArray.get(j);

                            description = lifeSkillcoscholasticResultResponseObject.getString(Constants.KEY_DESCRIPTION);
                            remark = lifeSkillcoscholasticResultResponseObject.getString(Constants.KEY_REMARK);
                            grade = lifeSkillcoscholasticResultResponseObject.getString(Constants.KEY_GRADE);

//                            coScholasticChildItemBean.add(new CoScholasticChildItemBean(description, remark, grade));
                        }

                        coScholasticBean.add(new CoScholasticBean(typeDesc, coScholasticChildItemBean));
                    }

                    JSONArray healthResultResultResponseArray = rootObject.getJSONArray(Constants.KEY_HEALTH_RESULT);
                    for (int i = 0; i < healthResultResultResponseArray.length(); i++) {
                        JSONObject healthResultResultResponseObject = (JSONObject) healthResultResultResponseArray.get(i);

                        hieght = healthResultResultResponseObject.getString(Constants.KEY_HIEGHT);
                        weight = healthResultResultResponseObject.getString(Constants.KEY_WEIGHT);
                        bloodGroup = healthResultResultResponseObject.getString(Constants.KEY_BLOODGROUP);
                        dental = healthResultResultResponseObject.getString(Constants.KEY_DENTAL);
                        visionLeft = healthResultResultResponseObject.getString(Constants.KEY_VISION_LEFT);
                        visionRight = healthResultResultResponseObject.getString(Constants.KEY_VISION_RIGHT);

                        healthReportBean.setHieght(hieght);
                        healthReportBean.setWeight(weight);
                        healthReportBean.setBloodGroup(bloodGroup);
                        healthReportBean.setDental(dental);
                        healthReportBean.setVisionLeft(visionLeft);
                        healthReportBean.setVisionRight(visionRight);
                    }


                    JSONArray selfAwarenessResultResponseArray = rootObject.getJSONArray(Constants.KEY_SELF_AWARENESS_RESULT);
                    for (int i = 0; i < selfAwarenessResultResponseArray.length(); i++) {
                        JSONObject selfAwarenessResultResponseObject = (JSONObject) selfAwarenessResultResponseArray.get(i);

                        goal = selfAwarenessResultResponseObject.getString(Constants.KEY_GOAL);
                        strength = selfAwarenessResultResponseObject.getString(Constants.KEY_STRENGTH);
                        interestHobbies = selfAwarenessResultResponseObject.getString(Constants.KEY_INTEREST_HOBBIES);
                        responsibilityDischarged_exceptionalAchievements = selfAwarenessResultResponseObject.getString(Constants.KEY_RESPONSIBILITY_DISCHARGED_EXCEPTIONAL_ACHIEVEMENTS);

                        selfAwarnessReportBean.setGoal(goal);
                        selfAwarnessReportBean.setStrength(strength);
                        selfAwarnessReportBean.setInterestHobbies(interestHobbies);
                        selfAwarnessReportBean.setResponsibilityDischarged_exceptionalAchievements(responsibilityDischarged_exceptionalAchievements);
                    }
                } else {
                    Toast.makeText(StudentReportFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
            progressDialog.dismiss();
        }
    }
}

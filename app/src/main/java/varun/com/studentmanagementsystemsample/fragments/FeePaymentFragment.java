package varun.com.studentmanagementsystemsample.fragments;

import android.animation.ObjectAnimator;
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
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
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
import varun.com.studentmanagementsystemsample.adapter.FeePaymentAdapter;
import varun.com.studentmanagementsystemsample.bean.FeePaymentBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class FeePaymentFragment extends Fragment {

    RecyclerView feePaymentRV;
    ArrayList<FeePaymentBean> list;
    FeePaymentAdapter adapter;
    ProgressBar feeProgressBar;

    TableRow feePaymentTableRow;

    SessionManager sessionManager;

    InputStream inputStream;
    StringBuilder stringBuilder;
    String feePaymentResponse;

    ProgressDialog progressDialog;

    TextView mTotalFeePayment, mDueFeePayment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sessionManager = new SessionManager(FeePaymentFragment.this.getActivity());

        View rootView = inflater.inflate(R.layout.fragment_fee_payment, container, false);

        feeProgressBar = (ProgressBar) rootView.findViewById(R.id.fee_progress_bar);
        feePaymentRV = (RecyclerView) rootView.findViewById(R.id.rvFeePayment);
        feePaymentTableRow = (TableRow) rootView.findViewById(R.id.fee_payment_rows);
        mTotalFeePayment = (TextView) rootView.findViewById(R.id.total_fee_payment);
        mDueFeePayment = (TextView) rootView.findViewById(R.id.total_due_payment);

        new ForFeePayment().execute();

        return rootView;
    }

    class ForFeePayment extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            progressDialog = new ProgressDialog(FeePaymentFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer feePaymentJsonStringer = new JSONStringer();

            try {

                String studentId = null, schoolId = null, academicYearID = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getSchoolID();
                    studentId = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getStudentID();
                    academicYearID = "" + sessionManager.getStudentList().get(MainActivity.globalPosition).getAcademicYearID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();
                    academicYearID = "" + sessionManager.getUserDetails().getAcademicYearID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    schoolId = "" + sessionManager.getUserDetails().getSchoolID();
                    studentId = "" + sessionManager.getUserDetails().getStudentRegID();
                    academicYearID = "" + sessionManager.getUserDetails().getAcademicYearID();

                }

                feePaymentJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(studentId).key(Constants.KEY_SCHOOL_ID).value(schoolId).key(Constants.KEY_ACADEMIC_YEAR_ID).value(academicYearID).endObject();

                URL url = new URL(Api.FEE_PAYMENT_URL);

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
                feePaymentResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String studentRegID = null, schoolID = null, academicYearID = null, totalFees = null, totalFeesPaid = null, componentName = null, feesPaid = null, amountDue = null, dueDate = null;

            try {
                JSONObject rootObject = new JSONObject(feePaymentResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);
                String message = rootObject.getString(Constants.KEY_MESSAGE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray feePaymentResponseArray = rootObject.getJSONArray(Constants.KEY_FEE_PAYMENT_RESULT);

                    for (int i = 0; i < feePaymentResponseArray.length(); i++) {
                        JSONObject feePaymentResponseObject = (JSONObject) feePaymentResponseArray.get(i);

                        studentRegID = feePaymentResponseObject.getString(Constants.KEY_STUDENT_ID);
                        schoolID = feePaymentResponseObject.getString(Constants.KEY_SCHOOL_ID);
                        academicYearID = feePaymentResponseObject.getString(Constants.KEY_ACADEMIC_YEAR_ID);

                        JSONArray feePaymentComponentArray = feePaymentResponseObject.getJSONArray(Constants.KEY_FEE_PAYMENT_COMPONENT);
                        for (int j = 0; j < feePaymentComponentArray.length(); j++) {
                            JSONObject feePaymentComponent = (JSONObject) feePaymentComponentArray.get(j);

                            componentName = feePaymentComponent.getString(Constants.KEY_FEE_PAYMENT_COMPONENT_NAME);
                            feesPaid = feePaymentComponent.getString(Constants.KEY_FEE_PAYMENT_FEES_PAID);
                            amountDue = feePaymentComponent.getString(Constants.KEY_FEE_PAYMENT_AMOUNT_DUE);
                            dueDate = feePaymentComponent.getString(Constants.KEY_FEE_PAYMENT_DUE_DATE);

                            list.add(new FeePaymentBean(componentName, feesPaid, amountDue, dueDate));
                        }

                        totalFees = feePaymentResponseObject.getString(Constants.KEY_FEE_PAYMENT_TOTAL_FEES);
                        totalFeesPaid = feePaymentResponseObject.getString(Constants.KEY_FEE_PAYMENT_TOTAL_FEES_PAID);
                    }

                    float dueFee = Float.parseFloat(totalFees) - Float.parseFloat(totalFeesPaid);

                    mTotalFeePayment.setText("Total Fees: " + totalFees);
                    mDueFeePayment.setText("Fees Dues: " + dueFee);

                    int progressPercentage = (int) ((Float.parseFloat(totalFeesPaid) / Float.parseFloat(totalFees)) * 100);

                    ObjectAnimator progressAnimator = ObjectAnimator.ofInt(feeProgressBar, "progress", 0, progressPercentage);
                    progressAnimator.setDuration(3000);
                    progressAnimator.setInterpolator(new LinearInterpolator());
                    progressAnimator.start();

                    adapter = new FeePaymentAdapter(FeePaymentFragment.this.getActivity(), list);
                    feePaymentRV.setAdapter(adapter);
                    feePaymentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    Toast.makeText(FeePaymentFragment.this.getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
            progressDialog.dismiss();
        }
    }
}

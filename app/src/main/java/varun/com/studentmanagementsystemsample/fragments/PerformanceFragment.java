package varun.com.studentmanagementsystemsample.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;

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
import varun.com.studentmanagementsystemsample.adapter.PerformanceAdapter;
import varun.com.studentmanagementsystemsample.bean.AcademicBean;
import varun.com.studentmanagementsystemsample.bean.PerformanceBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;

/**
 * Created by Varun on 4/2/2016.
 */
public class PerformanceFragment extends Fragment {

    RecyclerView performanceRV;
    ArrayList<PerformanceBean> list;
    ArrayList<AcademicBean> academicsList;
    PerformanceAdapter adapter;
    Spinner dateSpinner;
    String[] dateArray = {"Jan 2016 - Jun 2016", "Jun 2016 - Dec 2016", "Jan 2016 - Jun 2016"};

    InputStream inputStream;
    StringBuilder stringBuilder;
    String performanceResponse;

    BarChart mPerformanceChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_performance, container, false);

        dateSpinner = (Spinner) rootView.findViewById(R.id.date_spinner);
        performanceRV = (RecyclerView) rootView.findViewById(R.id.rvPerformance);
        mPerformanceChart = (BarChart) rootView.findViewById(R.id.performance_chart);

        mPerformanceChart.setDescription("");
        mPerformanceChart.setPinchZoom(false);
        mPerformanceChart.setDrawBarShadow(false);
        mPerformanceChart.setDrawGridBackground(false);
        mPerformanceChart.setDragEnabled(true);
        mPerformanceChart.setDrawBorders(false);
        mPerformanceChart.setDoubleTapToZoomEnabled(false);

        dateSpinner.setAdapter(new ArrayAdapter<String>(PerformanceFragment.this.getActivity(), R.layout.date_spinner_item, R.id.date_spinner_text_view, dateArray));

        new ForPerformance().execute();

        return rootView;
    }

    class ForPerformance extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            academicsList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer performanceJsonStringer = new JSONStringer();

            try {

                performanceJsonStringer.object().key(Constants.KEY_STUDENT_ID).value(MainActivity.studentId).endObject();

                URL url = new URL(Api.PERFORMANCE_URL);

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
                writer.write(performanceJsonStringer.toString());

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
                performanceResponse = stringBuilder.toString();

            } catch (Exception e) {

                Log.e("CHILD ERROR: ", "CHILD ERROR: " + e);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String studentFirstName = null, subject = null, academicYear = null, term = null, board = null, FA1 = null, FA2 = null, totalFA = null, SA1 = null, totalFASA1 = null;
            int studentID = -1, rank = -1;

            try {
                JSONObject rootObject = new JSONObject(performanceResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONArray performanceResponseArray = rootObject.getJSONArray(Constants.KEY_PERFORMANCE_ACADEMIC);

                    for (int i = 0; i < performanceResponseArray.length(); i++) {
                        JSONObject performanceResponseObject = (JSONObject) performanceResponseArray.get(i);

                        studentID = performanceResponseObject.getInt(Constants.KEY_PERFORMANCE_STUDENT_ID);
                        subject = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_SUBJECT);
                        academicYear = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_ACADEMIC_YEAR);
                        term = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_TERM);
                        board = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_BOARD);
                        FA1 = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_FA1);
                        FA2 = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_FA2);
                        totalFA = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_TOTAL_FA);
                        SA1 = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_SA1);
                        totalFASA1 = performanceResponseObject.getString(Constants.KEY_PERFORMANCE_TOTAL_FA_SA1);
                        rank = performanceResponseObject.getInt(Constants.KEY_PERFORMANCE_RANK);

                        academicsList.add(new AcademicBean("" + studentID, subject, academicYear, term, board, FA1, FA2, totalFA, SA1, totalFASA1, "" + rank));
                    }

                    adapter = new PerformanceAdapter(PerformanceFragment.this.getActivity(), academicsList);
                    performanceRV.setAdapter(adapter);
                    performanceRV.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            } catch (Exception e) {
                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);
            }
        }
    }
}

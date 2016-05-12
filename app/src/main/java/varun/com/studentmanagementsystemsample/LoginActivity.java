package varun.com.studentmanagementsystemsample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    InputStream inputStream;
    StringBuilder stringBuilder;
    String loginResponse;

    SessionManager sessionManager;

    String userNameStr, passwordStr;

    EditText userNameEdit, passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        sessionManager = new SessionManager(LoginActivity.this);

        mLogin = (Button) findViewById(R.id.login);
        userNameEdit = (EditText) findViewById(R.id.et_Username);
        passwordEdit = (EditText) findViewById(R.id.et_Password);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameStr = userNameEdit.getText().toString();
                passwordStr = passwordEdit.getText().toString();

                if (userNameStr.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordStr.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                new ForLogin().execute();

//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    class ForLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer loginJsonStringer = new JSONStringer();

            try {

                loginJsonStringer.object().key(Constants.KEY_USER_NAME).value(userNameStr).key(Constants.KEY_PASSWORD).value(passwordStr).endObject();

                URL url = new URL(Api.LOGIN_URL);

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
                writer.write(loginJsonStringer.toString());

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
                loginResponse = stringBuilder.toString();

            } catch (Exception e) {

                Log.e("LOGIN ERROR: ", "LOGIN ERROR: " + e);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String userName = null;
            int userId = -1, roleId = -1, userType = -1;

            try {
                JSONObject rootObject = new JSONObject(loginResponse);

                int statusCode = rootObject.getInt(Constants.KEY_STATUS_CODE);

                if (statusCode == Constants.STATUS_CODE_SUCCESS) {
                    JSONObject loginResponseObject = rootObject.getJSONObject(Constants.KEY_OBJ_LOGIN_RESULT);

                    userId = loginResponseObject.getInt(Constants.KEY_USER_ID);
                    userName = loginResponseObject.getString(Constants.KEY_USER_NAME);
                    roleId = loginResponseObject.getInt(Constants.KEY_ROLE_ID);
                    userType = loginResponseObject.getInt(Constants.KEY_USER_TYPE);
                }

                sessionManager.createLoginSession(true, userId, userName, roleId, userType);

                if (userType == Constants.USER_TYPE_PARENT) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            } catch (Exception e) {

                Log.e(Constants.TAG, "JSON PARSE ERROR: " + e);

            }
        }
    }
}

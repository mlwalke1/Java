package edu.svsu.rentit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity   {

    private static final String TAG = "Testing: ";

    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnRegister = findViewById(R.id.button_Register);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText etFirstName = findViewById(R.id.txt_FirstName);
                final String User_FirstName = etFirstName.getEditableText().toString();
                EditText etLastName = findViewById(R.id.txt_LastName);
                final String User_LastName = etLastName.getEditableText().toString();
                EditText etEmail = findViewById(R.id.txt_Email);
                final String User_Email = etEmail.getEditableText().toString();
                EditText etPassword = findViewById(R.id.txt_VerifyPassword);
                final String User_Password = etPassword.getEditableText().toString();

                new RegisterTask().execute(User_FirstName, User_LastName, User_Email, User_Password);
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            HttpURLConnection httpURLConnection;
            String HttpURL = "http://18.224.109.190/rentit/scripts/register.php";

            try {
                url = new URL(HttpURL);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }

            try {

                String firstname = params[0];
                String lastname = params[1];
                String username = params[2];
                String password = params[3];

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&"
                        + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&"
                        + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                httpURLConnection.connect();
            }catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = httpURLConnection.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while((line = bufferedReader.readLine()) != null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();

                    return result;

                }
                else {
                    return("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                httpURLConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject response = new JSONObject(result);

                int success = response.getInt("success");
                String message = response.getString("message");


                if (success == 1) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            message,
                            Toast.LENGTH_SHORT);

                    toast.show();
                }

            } catch (Exception e) {

            }


        }

    }
}
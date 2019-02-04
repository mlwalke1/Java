package edu.svsu.rentit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

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

public class ProfileActivity extends AppCompatActivity {

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("RentIT - Profile");
        setSupportActionBar(toolbar);


        userid = getIntent().getStringExtra("USER_ID");


        Toast toast = Toast.makeText(getApplicationContext(),
                "User ID: " + userid,
                Toast.LENGTH_SHORT);

        toast.show();
        new ProfileActivity.GetProfileTask().execute();
    }

    public class GetProfileTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            HttpURLConnection httpURLConnection;
            String HttpURL = "http://18.224.109.190/rentit/scripts/get_profile.php";

            try {
                url = new URL(HttpURL);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }

            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");
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

                String first = response.getString("first");
                String last = response.getString("last");
                String bio = response.getString("bio");


                TextView txt_Name = findViewById(R.id.tv_Profilename);
                txt_Name.setText(first + " " + last);
                EditText txt_Bio = findViewById(R.id.eT_bio);
                txt_Bio.setText(bio);

            } catch (Exception e) {

            }


        }

    }

}
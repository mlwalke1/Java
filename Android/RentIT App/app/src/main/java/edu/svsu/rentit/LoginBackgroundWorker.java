package edu.svsu.rentit;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class LoginBackgroundWorker extends AsyncTask<String, String, String> {
    Context context;
    AlertDialog alertDialog;
    String HttpURL = "http://blakeengineers.com/caravanUpdate.php";




    LoginBackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        return "works";
    }


}
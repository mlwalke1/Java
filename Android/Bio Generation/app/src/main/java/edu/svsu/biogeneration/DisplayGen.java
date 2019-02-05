package edu.svsu.biogeneration;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class DisplayGen extends Activity {

    public static final String BIO_TEXT = "biotext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gen);
        Intent intent = getIntent();

        String bioText = intent.getStringExtra(BIO_TEXT);

        TextView bioView = (TextView)findViewById(R.id.bioView);
        bioView.setText(bioText);
    }

}

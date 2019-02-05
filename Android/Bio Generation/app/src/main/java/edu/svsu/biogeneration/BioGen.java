package edu.svsu.biogeneration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

public class BioGen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_gen);
    }

    public void onClickCreate(View view) {
        EditText firstNameView = (EditText) findViewById(R.id.firstName);
        EditText lastNameView = (EditText) findViewById(R.id.lastName);
        EditText yearView = (EditText) findViewById(R.id.year);
        EditText schoolView = (EditText) findViewById(R.id.school);
        EditText favoriteView = (EditText) findViewById(R.id.favorite);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);

        String bioText = firstNameView.getText().toString();
        bioText = bioText + " " + lastNameView.getText().toString();
        bioText = bioText + " graduated in " + yearView.getText().toString();
        bioText = bioText + " with a " + spinner2.getSelectedItem().toString();
        bioText = bioText + " in " + spinner.getSelectedItem().toString();
        bioText = bioText + " from " + schoolView.getText().toString() + ".";
        bioText = bioText + " Their favorite activity is " + favoriteView.getText().toString()
                + ".";

        Intent intent = new Intent(this,DisplayGen.class);
        intent.putExtra(DisplayGen.BIO_TEXT, bioText);
        startActivity(intent);
    }
}

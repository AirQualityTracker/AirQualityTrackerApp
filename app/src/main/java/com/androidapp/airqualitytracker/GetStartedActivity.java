package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class is used at the first time the app is used
 */
public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started_activity);
    }


    public void getStarted(View view) {
        // after this screen start the activity that asks for tha location
        //should change MainMenuActivity to the GetLocation activity
        Intent getLocation = new Intent(this, MainMenuActivity.class);

        startActivity(getLocation);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

    }
}
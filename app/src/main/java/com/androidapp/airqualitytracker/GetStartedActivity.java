package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started_activity);

    }


    public void getStarted(View view) {
        Intent getLocation = new Intent(this, LogInActivity.class);

        startActivity(getLocation);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

    }
}
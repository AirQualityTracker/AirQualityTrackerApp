package com.androidapp.airqualitytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class GetLocationActivity extends AppCompatActivity {
    //this code is used to check if the permission is granted by the user
    private final int REQUEST_LOCATION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_location_activity);


        Button buttonRequest = findViewById(R.id.findMyLocationButton);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the location permission is already granted
                if (ContextCompat.checkSelfPermission(GetLocationActivity.this,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(GetLocationActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                } else {

                    //if not then ask for location permission
                    ActivityCompat.requestPermissions(GetLocationActivity.this,
                            new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_LOCATION_CODE);
                }
            }
        });
    }

    //this method checks the result of a permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //check if the permission is granted
        if (requestCode == REQUEST_LOCATION_CODE)  {
            //if the grantResults contain the permission for location
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}


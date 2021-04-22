package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText objEditTextUsername , objEditTextPassword;
    Button objButtonLogIn, objButtonRegister;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 5) && (resultCode == RESULT_OK)) {
            CharSequence userTextNew = data.getExtras().getCharSequence("usernameText");
            objEditTextUsername.setText(userTextNew);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        objEditTextUsername = findViewById(R.id.editTextUsername);
        objEditTextPassword = findViewById(R.id.editTextPassword);
        objButtonLogIn = findViewById(R.id.button_log_in);
        objButtonRegister = findViewById(R.id.button_register);

    }

    public void createNewAccount(View view) {
        Intent i = new Intent(this, CreateNewAccountActivity.class);
        startActivityForResult(i ,5);
    }


    public void tryLogIn(View view) {
        //check valids with an SQL table and confirms log in or not

        if (true){
            //open main page
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Username or Password" , Toast.LENGTH_SHORT).show();
        }


    }
}
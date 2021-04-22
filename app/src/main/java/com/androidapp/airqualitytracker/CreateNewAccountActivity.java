package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateNewAccountActivity extends AppCompatActivity {

    EditText objEditTextEmailRegister , objEditTextUsernameRegister , objEditTextPasswordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account_activity);

        objEditTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        objEditTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
        objEditTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);

    }

    public void editNewAccount(View view) {
        //**should check if the email has already given**

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateNewAccountActivity.this);
        builder.setIcon(R.drawable.ic_baseline_check_24);
        builder.setMessage("Successfully Registration!");

        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        Intent i = new Intent(this, LogInActivity.class);
        CharSequence returnChars = objEditTextUsernameRegister.getText();
        i.putExtra("usernameText", returnChars);
        setResult(RESULT_OK , i);

        finish();
    }
}
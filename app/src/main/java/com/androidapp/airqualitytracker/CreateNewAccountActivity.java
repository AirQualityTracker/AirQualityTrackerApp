/*
package com.androidapp.airqualitytracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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


    //This method is used to create the users account when registered
    public void createNewAccount() {
        boolean userAddedSuccessfully = addUserToDb();
        //Try to add the user to the db

        //**should check if the email has already given**

        if (userAddedSuccessfully) {
            //show message of successfull registration on screen
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNewAccountActivity.this);
            builder.setIcon(R.drawable.ic_baseline_check_24);
            builder.setMessage("Successfully Registration!");

            //create an ok button that makes the message disappear
            builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            //return to the previous activity
            Intent i = new Intent(this, LogInActivity.class);
            CharSequence returnChars = objEditTextUsernameRegister.getText();
            i.putExtra("usernameText", returnChars);
            setResult(RESULT_OK, i);

            finish();
        } else {
            //
            Toast.makeText(getApplicationContext(),"Invalid Username or Password" , Toast.LENGTH_SHORT).show();
        }
    }


    //Method to add the user to the database
    public boolean addUserToDb(){
        //Create a new db handler
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        //get user info from the input fields
        String username = objEditTextUsernameRegister.getText().toString();
        String email = objEditTextEmailRegister.getText().toString();
        String password = objEditTextPasswordRegister.getText().toString();

        //check if the info are not empty
        if(!username.equals("") && !email.equals("") && !password.equals("")){
            //try to find the user in the db
            User found = dbHandler.findUser(username);

            //if the user is not found then create them and add them to the database
            if(found == null){
                User user = new User(username, email, password);
                dbHandler.addUser(user);
                //   objEditTextUsernameRegister.setText("");
                //   objEditTextEmailRegister.setText("");
                //   objEditTextPasswordRegister.setText("");
                return true;
            }
        }
        return false;
    }
}
*/

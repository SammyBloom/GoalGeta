package com.android.goalgeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    //objects of all edit fields
    private AutoCompleteTextView mEmail;
    private EditText mUsername, mPhoneNo, mPassword, mCnfPassword;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialize all edit fields
        mUsername = (EditText) findViewById(R.id.edit_username);
        mEmail = (AutoCompleteTextView) findViewById(R.id.edit_email);
        mPhoneNo = (EditText) findViewById(R.id.edit_phoneno);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mCnfPassword = (EditText) findViewById(R.id.edit_cnfpassword);
        checkBox = (CheckBox) findViewById(R.id.check_box);

        //Initialize clickable buttons and text
        findViewById(R.id.signup).setOnClickListener(this);
        findViewById(R.id.TOC).setOnClickListener(this);
        findViewById(R.id.signin).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                if (checkBox.isChecked()){
                    userSignUp();
                } else {
                 checkBox.setError("Accept terms and conditions before proceeding");
                 checkBox.requestFocus();
                 return;
                }
                break;
            case R.id.TOC:
                break;
            case R.id.signin:
                break;
        }
    }

    private void userSignUp(){

        String username = mUsername.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phoneNo = mPhoneNo.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String cnfPassword = mCnfPassword.getText().toString().trim();

        //Validations for username fields required
        if (username.isEmpty()){
            mUsername.setError("Username is required");
            mUsername.requestFocus();
            return;
        }

        //Validation for email
        if (email.isEmpty()){
            mEmail.setError("Email is required");
            mEmail.requestFocus();
            return;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Incorrect Email");
            mEmail.requestFocus();
            return;
        }

        //Validation for phone number
        if (phoneNo.isEmpty()){
            mPhoneNo.setError("Phone number is required");
            mPhoneNo.requestFocus();
            return;
        }
        if (phoneNo.length()<11 || phoneNo.length()>11){
            mPhoneNo.setError("Enter a correct phone number");
            mPhoneNo.requestFocus();
            return;
        }

        //Validation for Password
        if (password.isEmpty()){
            mPassword.setError("Password is required");
            mPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            mPassword.setError("Password should be more than 6 characters");
            mPassword.requestFocus();
            return;
        }

        //Validation for confirm password
        if (cnfPassword.isEmpty()){
            mCnfPassword.setError("Password is required");
            mCnfPassword.requestFocus();
            return;
        }
        if (!cnfPassword.equals(password)){
            mCnfPassword.setError("Password does not match");
            mCnfPassword.requestFocus();
            return;
        }
    }

}

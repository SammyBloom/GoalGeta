package com.android.goalgeta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.goalgeta.R;
import com.android.goalgeta.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

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
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }



    private void userSignUp(){

        final String username = mUsername.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
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
//        if (email.contains("@")){
//            mEmail.setError("Incorrect Email");
//            mEmail.requestFocus();
//            return;
//        }

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

        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().register(username, email, phoneNo, password);

//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                if (response.code() == 201){
//
//                    Response dr = response.body();
//                    Toast.makeText(SignupActivity.this, dr.getMsg(), Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(SignupActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//
//            }
//        });
        call.enqueue(new Callback<ResponseBody>() {
//            /**
//             * Invoked for a received HTTP response.
//             * <p>
//             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
//             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
//             *
//             * @param call
//             * @param response
//             */
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String s = null;

                try {
                    if (response.code() == 201){
                        s = response.body().string();
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                    } else {
                        s = response.errorBody().string();
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

//                if (s != null){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        Toast.makeText(SignupActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
////                        The data type used here depends on the the type of data used. Check your toast message
////                        boolean error = jsonObject.getBoolean("error");
//                    } catch (JSONException e){
//                        e.printStackTrace();
//                    }
//
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();
                Toast.makeText(SignupActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

}

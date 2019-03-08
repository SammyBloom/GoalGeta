package com.android.goalgeta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.goalgeta.R;
import com.android.goalgeta.api.RetrofitClient;
import com.android.goalgeta.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //objects of fields required
    private AutoCompleteTextView mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize edit text fields
        mUsername = (AutoCompleteTextView) findViewById(R.id.username_login);
        mPassword = (EditText) findViewById(R.id.password_login);

        //Initalize text buttons and buttons
        findViewById(R.id.loginform).setOnClickListener(this);
        findViewById(R.id.forgot_pass).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginform:
                userLogin();
                break;
            case R.id.forgot_pass:
                break;
            case R.id.sign_up:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin(){
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //Validation for username for login
        if (username.isEmpty()){
            mUsername.setError("Username is required");
            mUsername.requestFocus();
            return;
        }

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

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()){
//                    Proceed with the login, save user and open profile
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent dashintent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(dashintent);
                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
}

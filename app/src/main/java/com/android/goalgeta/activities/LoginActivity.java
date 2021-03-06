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
import com.android.goalgeta.models.ResponseObb;
import com.android.goalgeta.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //objects of fields required
    private AutoCompleteTextView mEmail;
    private EditText mPassword;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setFinishOnTouchOutside(false);

        //Initialize edit text fields
        mEmail = (AutoCompleteTextView) findViewById(R.id.email_login);
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

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin(){
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //Validation for username for login
        if (email.isEmpty()){
            mEmail.setError("Username is required");
            mEmail.requestFocus();
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

        Call<ResponseObb> call = RetrofitClient.getInstance().getApi().login(email, password);
        call.enqueue(new Callback<ResponseObb>() {
            @Override
            public void onResponse(Call<ResponseObb> call, Response<ResponseObb> response) {

                ResponseObb loginResponse = response.body();
                if (response.code() == 200){
//                    proceed with login

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                    String token = loginResponse.getData().getToken();

                    Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();

                    Intent dashboard = new Intent(LoginActivity.this,DashboardActivity.class);
                    dashboard.putExtra("token",token);
                    startActivity(dashboard);

               //     startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
               //     token = response.body().getToken().header("Authorization", token);

//                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                } else {
                    Toast.makeText(getApplicationContext(), String.valueOf(loginResponse.getData().getSuccess()), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObb> call, Throwable t) {

            }
        });

    }
}

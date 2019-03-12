package com.android.goalgeta.models;

public class LoginResponse {

    LoginResponse LoginResponseObject;


    // Getter Methods

    public LoginResponse getLoginResponse() {
        return LoginResponseObject;
    }

    // Setter Methods

    public void setLoginResponse(LoginResponse  loginResponseObject) {
        this.LoginResponseObject = loginResponseObject;
    }

    private boolean success;
    User UserObject;
    private String token;


    // Getter Methods

    public boolean getSuccess() {
        return success;
    }

    public User getUser() {
        return UserObject;
    }

    public String getToken() {
        return token;
    }

    // Setter Methods

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

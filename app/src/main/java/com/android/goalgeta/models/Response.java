package com.android.goalgeta.models;

public class Response {
    Response ResponseObject;


    // Getter Methods

    public Response getData() {
        return ResponseObject;
    }

    // Setter Methods

    public void setData(Response dataObject) {
        this.ResponseObject = dataObject;
    }

    User UserObject;
    private boolean success;
    private String Bearer;


    // Getter Methods

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return UserObject;
    }

    // Setter Methods

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }

    public String getBearer() {
        return Bearer;
    }

    public void setBearer(String Bearer) {
        this.Bearer = Bearer;
    }
}

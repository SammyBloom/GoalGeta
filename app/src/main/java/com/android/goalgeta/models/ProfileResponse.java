package com.android.goalgeta.models;

public class ProfileResponse {
    ProfileResponse DataObject;
    private boolean success;
    User UserObject;

    // Getter Methods

    public ProfileResponse getData() {
        return DataObject;
    }

    // Setter Methods

    public void setData(ProfileResponse dataObject) {
        this.DataObject = dataObject;
    }

    // Getter Methods

    public boolean getSuccess() {
        return success;
    }

    public User getUser() {
        return UserObject;
    }

    // Setter Methods

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }

}

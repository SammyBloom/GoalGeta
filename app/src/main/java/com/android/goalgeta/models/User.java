package com.android.goalgeta.models;

public class User {

    private int id;
    private String username, email, phoneNo;

    public User(int id, String username, String email, String phoneNo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}

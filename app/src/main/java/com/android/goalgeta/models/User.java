package com.android.goalgeta.models;

public class User {
    private String name;
    private String email;
    private String phone_number;
    private String updated_at;
    private String created_at;
    private float id;


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public float getId() {
        return id;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId(float id) {
        this.id = id;
    }

}

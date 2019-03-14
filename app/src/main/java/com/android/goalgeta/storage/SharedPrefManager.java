package com.android.goalgeta.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.goalgeta.models.User;

public class SharedPrefManager {

    public static final String SHARED_PREF_NAME = "my_shared_pref_name";
    public static SharedPrefManager myInstance;

    public static final String ID = "id";
    public static final String NAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE_NO = "phone_number";

    private Context mCtx;

    public SharedPrefManager(Context context) {
        this.mCtx = context;
    }


    public static synchronized SharedPrefManager getInstance(Context context){

        if (myInstance == null){
            myInstance = new SharedPrefManager(context);
        }
        return myInstance;
    }



    public void userLogin(User user){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (user != null){
            editor.putInt("id", user.getId());
            editor.putString("email", user.getEmail());
            editor.putString("username", user.getName());
            editor.putString("phone_number", user.getPhone_number());

            editor.apply();
        }
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(EMAIL, null) != null){
            return true;
        }return false;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(ID, 0),
                sharedPreferences.getString(EMAIL, null),
                sharedPreferences.getString(NAME, null),
                sharedPreferences.getString(PHONE_NO, null)
                );
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        return true;
    }
}

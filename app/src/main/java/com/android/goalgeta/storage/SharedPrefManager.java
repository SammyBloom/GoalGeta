package com.android.goalgeta.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.goalgeta.models.User;

public class SharedPrefManager {

    public static final String SHARED_PREF_NAME = "my_shared_pref_name";
    public static SharedPrefManager myInstance;

    public static final String Name = "username";

    private Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
    }


    public static synchronized SharedPrefManager getInstance(Context context){

        if (myInstance == null){
            myInstance = new SharedPrefManager(context);
        }
        return myInstance;
    }

    public void saveUser(User user){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (user != null){
            editor.putInt("id", user.getInt());
            editor.putString("email", user.getEmail());
            editor.putString("username", user.getName());
            editor.putString("phone_number", user.getPhone_number());

            editor.apply();
        }



    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public User getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("phone_number", null)
                );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }
}

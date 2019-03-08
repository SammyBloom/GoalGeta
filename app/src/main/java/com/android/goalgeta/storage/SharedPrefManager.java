package com.android.goalgeta.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.goalgeta.models.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_pref_name";
    private static SharedPrefManager myInstance;
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

    public void saveUsers(User user){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("usernmae", user.getUsername());
    }
}

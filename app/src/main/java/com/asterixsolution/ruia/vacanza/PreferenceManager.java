package com.asterixsolution.ruia.vacanza;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Wagle on 29-01-2018.
 */

public class PreferenceManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    private static final String PREF_NAME = "vacanza-main";
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";
    private static final String USER_ID_KEY = "USERID";
    private static int PRIVATE_MODE = 0;

    public PreferenceManager(Context ctx) {
        this.ctx = ctx;
        this.pref = ctx.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        this.editor = pref.edit();
    }

    public void setName(String name){
        editor.putString(NAME_KEY,name);
        editor.commit();
    }

    public String getName(){
        return pref.getString(NAME_KEY,null);
    }

    public void setEmail(String email){
        editor.putString(EMAIL_KEY,email);
        editor.commit();
    }

    public String getEmail(){
        return pref.getString(EMAIL_KEY,null);
    }

    public void setUserId(String id){
        editor.putString(USER_ID_KEY,id);
        editor.commit();
    }

    public String getUserId(){
        return pref.getString(USER_ID_KEY,null);
    }

}

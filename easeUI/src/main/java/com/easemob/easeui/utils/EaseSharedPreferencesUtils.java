package com.easemob.easeui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Corydon on 2016/3/7.
 */
public class EaseSharedPreferencesUtils {

    private static EaseSharedPreferencesUtils instance;
    private static SharedPreferences sharedPreferences;
    private static final String newAlertsName = "newAlerts";
    private static final String NEW_ALERTS_BUTTON_STATE = "state";

    private EaseSharedPreferencesUtils(Context context){
        sharedPreferences = context.getSharedPreferences(newAlertsName, Context.MODE_PRIVATE);
    }

    public static synchronized EaseSharedPreferencesUtils getInstance(Context context){
        if (instance == null){
            instance = new EaseSharedPreferencesUtils(context);
        }
        return instance;
    }

    public void setNewAlertsButtonState(Boolean onOff){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NEW_ALERTS_BUTTON_STATE, onOff);
        editor.apply();
    }

    public Boolean getNewAlertsButtonState(){
        return sharedPreferences.getBoolean(NEW_ALERTS_BUTTON_STATE, true);
    }
}

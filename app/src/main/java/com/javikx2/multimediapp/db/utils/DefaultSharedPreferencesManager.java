package com.javikx2.multimediapp.db.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DefaultSharedPreferencesManager {
    private SharedPreferences sharedPreferences;

    public DefaultSharedPreferencesManager(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setPreference(String key, String value) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }


    public void setPreference(String key, int value) {
        sharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }


    public void setPreference(String key, boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }


    public void setPreference(String key, long value) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    public String getStringPreference(String key) {
        return sharedPreferences.getString(key, null);
    }

    public int getIntPreference(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public Boolean getBooleanPreference(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public long getLongPreference(String key) {
        return sharedPreferences.getLong(key, -1);
    }

    public void removePreference(String key) {
        sharedPreferences.edit()
                .remove(key)
                .apply();
    }
}

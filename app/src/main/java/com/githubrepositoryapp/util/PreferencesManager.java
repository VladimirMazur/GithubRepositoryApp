package com.githubrepositoryapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vladimir on 02.03.2017.
 */

public class PreferencesManager {

    public static final String DEFAULT_STRING_VALUE = "";

    private static final String PREFS_ACCESS_TOKEN = "PREFS_ACCESS_TOKEN";

    private static final String PREFERENCES_NAME = "SETTINGS";

    private static PreferencesManager instance;
    private final SharedPreferences sharedPreferences;


    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeTaskInstance(..) method first.");
        }
        return instance;
    }

    public boolean clear() {
        return sharedPreferences.edit().clear().commit();
    }

    public String getGitHubAccessToken() {
        return sharedPreferences.getString(PREFS_ACCESS_TOKEN, DEFAULT_STRING_VALUE);
    }

    public void setGitHubAccessToken(String accessToken) {
        sharedPreferences.edit().putString(PREFS_ACCESS_TOKEN, accessToken).apply();
    }
}

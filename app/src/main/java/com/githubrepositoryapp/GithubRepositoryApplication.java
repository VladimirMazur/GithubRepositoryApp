package com.githubrepositoryapp;

import android.app.Application;

import com.githubrepositoryapp.util.PreferencesManager;

/**
 * Created by Vladimir on 02.04.2017.
 */

public class GithubRepositoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesManager.initializeInstance(getApplicationContext());
    }
}

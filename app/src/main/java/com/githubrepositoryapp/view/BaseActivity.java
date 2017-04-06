package com.githubrepositoryapp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.githubrepositoryapp.R;

import butterknife.ButterKnife;

/**
 * Created by Vladimir on 05.04.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager = getSupportFragmentManager();
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        createProgressDialog();
    }

    private void createProgressDialog() {
        progressDialog = new ProgressDialog(BaseActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.txt_loading));
    }

    public void replaceFragmentNonBackStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    public void addFragmentWithBackStack(Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
        fragmentManager.beginTransaction()
                .addToBackStack(fragment.getClass().getName())
                .setCustomAnimations(enter, exit, enter, exit)
                .add(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    public void showMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_LONG).show();
    }


    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public abstract int getActivityLayout();
}

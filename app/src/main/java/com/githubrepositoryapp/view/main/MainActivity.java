package com.githubrepositoryapp.view.main;

import android.os.Bundle;
import android.view.View;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.view.CustomToolbar;
import com.githubrepositoryapp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragmentNonBackStack(MainFragment.newInstance());
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        fragmentManager.popBackStack();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
    }
}

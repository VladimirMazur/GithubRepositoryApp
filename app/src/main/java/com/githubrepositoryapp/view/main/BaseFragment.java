package com.githubrepositoryapp.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.presenter.view.BaseView;
import com.githubrepositoryapp.view.BaseActivity;
import com.githubrepositoryapp.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Vladimir on 04.04.2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    @BindView(R.id.custom_toolbar)
    CustomToolbar customToolbar;

    protected Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setCustomToolbar();
    }

    private void setCustomToolbar() {
        customToolbar.setTitle(getTitle());
        customToolbar.setListenerButtonBack(isBackButton(), R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        customToolbar.setListenerButtonOptions(isCloseButton(), R.drawable.ic_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    protected ProgressDialog getProgressDialog() {
        return ((BaseActivity) getActivity()).getProgressDialog();
    }

    protected abstract int getFragmentLayout();
    protected abstract String getTitle();
    protected abstract boolean isBackButton();
    protected abstract boolean isCloseButton();
}

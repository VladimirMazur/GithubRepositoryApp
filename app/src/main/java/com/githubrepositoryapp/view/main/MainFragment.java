package com.githubrepositoryapp.view.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.pojo.User;
import com.githubrepositoryapp.presenter.impl.MainPresenterImpl;
import com.githubrepositoryapp.util.PreferencesManager;
import com.githubrepositoryapp.view.BaseActivity;
import com.githubrepositoryapp.view.authorization.AuthorizationActivity;
import com.githubrepositoryapp.presenter.view.MainView;
import com.githubrepositoryapp.view.main.repositories.RepositoriesFragment;
import com.githubrepositoryapp.view.main.search.SearchResultFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.imageAvatar)
    ImageView imageAvatar;
    @BindView(R.id.txtUserName)
    TextView txtUserName;
    @BindView(R.id.btnRepositories)
    Button btnRepositories;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MainPresenterImpl presenter;

    private String login;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MainPresenterImpl(this);
        presenter.user();
    }

    @OnClick(R.id.btnRepositories)
    void onClickRepositories() {
        ((MainActivity) getActivity()).addFragmentWithBackStack(RepositoriesFragment.newInstance(login),
                R.anim.slide_up, R.anim.slide_down);
    }

    @OnClick(R.id.btnSearch)
    void onClickSearch() {
        ((MainActivity) getActivity()).addFragmentWithBackStack(SearchResultFragment.newInstance(),
                R.anim.slide_up, R.anim.slide_down);
    }

    @OnClick(R.id.btnLogout)
    void onClickLogout() {
        PreferencesManager.getInstance().clear();

        Intent intent = new Intent(getContext(), AuthorizationActivity.class);
        intent.addFlags(IntentCompat.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean isBackButton() {
        return false;
    }

    @Override
    protected boolean isCloseButton() {
        return true;
    }

    @Override
    public void showProgress() {
        getProgressDialog().show();
    }

    @Override
    public void hideProgress() {
        getProgressDialog().dismiss();
    }

    @Override
    public void setUserInfo(User user) {
        login = user.getLogin();
        txtUserName.setText(login);

        Picasso.with(getContext()).load(user.getAvatarUrl())
                .into(imageAvatar, new Callback(){

                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void error(String error) {
        ((BaseActivity) getActivity()).showMessage(error);
    }
}

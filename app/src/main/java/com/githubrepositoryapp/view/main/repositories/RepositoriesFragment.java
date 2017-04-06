package com.githubrepositoryapp.view.main.repositories;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.presenter.impl.RepositoriesPresenterImpl;
import com.githubrepositoryapp.view.main.BaseFragment;
import com.githubrepositoryapp.view.BaseActivity;
import com.githubrepositoryapp.view.main.MainActivity;
import com.githubrepositoryapp.presenter.view.RepositoriesView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends BaseFragment implements RepositoriesView,
        RepositoryViewAdapter.OnRepositoryClickListener {

    private static final String ARG_LOGIN = "ARG_LOGIN";

    @BindView(R.id.repositoriesRecyclerView)
    RecyclerView repositoriesRecyclerView;

    private RepositoriesPresenterImpl presenter;

    private String login;

    public RepositoriesFragment() {
        // Required empty public constructor
    }

    public static RepositoriesFragment newInstance(String login) {
        RepositoriesFragment fragment = new RepositoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOGIN, login);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            login = getArguments().getString(ARG_LOGIN);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RepositoriesPresenterImpl(this);
        presenter.getRepositories(login);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_repositories;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.txt_repositories);
    }

    @Override
    protected boolean isBackButton() {
        return true;
    }

    @Override
    protected boolean isCloseButton() {
        return false;
    }

    @Override
    public void onRepositoriesItemClicked(Repository repository) {
        ((MainActivity) getActivity()).showMessage(repository.getName());
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
    public void setRepositories(List<Repository> repositories) {
        setRepositoryAdapter(repositories);
    }

    @Override
    public void error(String error) {
        ((BaseActivity) getActivity()).showMessage(error);
    }

    private void setRepositoryAdapter(List<Repository> repositories) {
        repositoriesRecyclerView.setHasFixedSize(true);
        repositoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repositoriesRecyclerView.setAdapter(new RepositoryViewAdapter(
                getActivity(), repositories, this));
    }
}

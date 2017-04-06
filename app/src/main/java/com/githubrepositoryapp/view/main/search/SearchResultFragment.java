package com.githubrepositoryapp.view.main.search;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.presenter.impl.SearchResultPresenterImpl;
import com.githubrepositoryapp.util.AppConstants;
import com.githubrepositoryapp.view.main.BaseFragment;
import com.githubrepositoryapp.view.BaseActivity;
import com.githubrepositoryapp.view.main.MainActivity;
import com.githubrepositoryapp.view.main.repositories.RepositoryViewAdapter;
import com.githubrepositoryapp.presenter.view.SearchResultView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends BaseFragment implements SearchResultView, SearchView.OnQueryTextListener,
        RepositoryViewAdapter.OnRepositoryClickListener {

    @BindView(R.id.repositoriesSearchView)
    SearchView repositoriesSearchView;
    @BindView(R.id.resultRecyclerView)
    RecyclerView resultRecyclerView;
    @BindView(R.id.txtNoResult)
    TextView txtNoResult;

    private RepositoryViewAdapter adapter;
    private SearchResultPresenterImpl presenter;

    private List<Repository> repositories = new ArrayList<>();

    public SearchResultFragment() {
        // Required empty public constructor
    }

    public static SearchResultFragment newInstance() {
        return new SearchResultFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new SearchResultPresenterImpl(this);
        setSearchView();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private Handler handler = new Handler();

    @Override
    public boolean onQueryTextChange(final String newText) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.search(newText);
            }
        }, AppConstants.SLEEP);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.txt_search_repositories);
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
    public void setResultRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        setRepositoryAdapter(repositories);
        txtNoResult.setVisibility(View.GONE);
    }

    @Override
    public void clearRecyclerView() {
        repositories.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            txtNoResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void error(String error) {
        ((BaseActivity) getActivity()).showMessage(error);
    }

    private void setRepositoryAdapter(List<Repository> repositories) {
        adapter = new RepositoryViewAdapter(getActivity(), repositories, this);
        resultRecyclerView.setHasFixedSize(true);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultRecyclerView.setAdapter(adapter);
    }

    private void setSearchView() {
        repositoriesSearchView.setOnQueryTextListener(this);
        repositoriesSearchView.onActionViewExpanded();
    }
}

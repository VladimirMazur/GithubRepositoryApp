package com.githubrepositoryapp.presenter.view;

import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.presenter.view.BaseView;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2017.
 */

public interface SearchResultView extends BaseView {

    void showProgress();
    void hideProgress();
    void setResultRepositories(List<Repository> repositories);
    void clearRecyclerView();
    void error(String error);
}

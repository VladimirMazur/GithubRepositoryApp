package com.githubrepositoryapp.presenter.view;

import com.githubrepositoryapp.pojo.Repository;

import java.util.List;

/**
 * Created by Vladimir on 05.04.2017.
 */

public interface RepositoriesView extends BaseView {

    void showProgress();
    void hideProgress();
    void setRepositories(List<Repository> repositories);
    void error(String error);
}

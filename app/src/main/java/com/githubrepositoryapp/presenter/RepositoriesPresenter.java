package com.githubrepositoryapp.presenter;

/**
 * Created by Vladimir on 05.04.2017.
 */

public interface RepositoriesPresenter {

    void getRepositories(String login);
    void onDestroy();
}

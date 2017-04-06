package com.githubrepositoryapp.presenter.view;

import com.githubrepositoryapp.pojo.User;

/**
 * Created by Vladimir on 05.04.2017.
 */

public interface MainView extends BaseView {

    void showProgress();
    void hideProgress();
    void setUserInfo(User user);
    void error(String error);
}

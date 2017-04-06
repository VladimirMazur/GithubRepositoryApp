package com.githubrepositoryapp.presenter.view;

import com.alorma.github.sdk.bean.dto.response.User;
import com.githubrepositoryapp.presenter.view.BaseView;

/**
 * Created by Vladimir on 05.04.2017.
 */

public interface AuthorizationView extends BaseView {

    void showProgress();
    void hideProgress();
    void navigateMain();
    void error(String error);
}

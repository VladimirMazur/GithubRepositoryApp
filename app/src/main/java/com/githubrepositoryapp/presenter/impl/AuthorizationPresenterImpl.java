package com.githubrepositoryapp.presenter.impl;

import android.net.Uri;

import com.alorma.github.sdk.bean.dto.response.Token;
import com.alorma.github.sdk.services.login.RequestTokenClient;
import com.alorma.github.sdk.services.user.GetAuthUserClient;
import com.githubrepositoryapp.util.AppConstants;
import com.githubrepositoryapp.util.PreferencesManager;
import com.githubrepositoryapp.presenter.AuthorizationPresenter;
import com.githubrepositoryapp.presenter.view.AuthorizationView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vladimir on 05.04.2017.
 */

public class AuthorizationPresenterImpl implements AuthorizationPresenter {

    private AuthorizationView view;

    public AuthorizationPresenterImpl(AuthorizationView view) {
        this.view = view;
    }

    @Override
    public void checkAuthorization() {
        String accessToken = PreferencesManager.getInstance().getGitHubAccessToken();
        if (accessToken != null && !accessToken.isEmpty()) {
            view.navigateMain();
        }
    }

    @Override
    public void login(Uri uri) {
        if (view != null) {
            view.showProgress();
        }

        String code = uri.getQueryParameter(AppConstants.PARAMETER_CODE);
        new RequestTokenClient(code, AppConstants.CLIENT_ID,
                AppConstants.CLIENT_SECRET, AppConstants.CALLBACK_URL)
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(Token token) {
                        if (token.access_token != null) {
                            getAuthClient(token.access_token);
                        } else if (token.error != null) {
                            view.error(token.error);
                        }
                    }
                });
    }

    private void getAuthClient(final String accessToken) {
        PreferencesManager.getInstance().setGitHubAccessToken(accessToken);

        new GetAuthUserClient(accessToken)
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.alorma.github.sdk.bean.dto.response.User>() {

                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(com.alorma.github.sdk.bean.dto.response.User user) {
                        view.navigateMain();
                    }
                });
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

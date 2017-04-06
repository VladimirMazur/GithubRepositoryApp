package com.githubrepositoryapp.presenter.impl;

import com.githubrepositoryapp.network.ApiManager;
import com.githubrepositoryapp.pojo.User;
import com.githubrepositoryapp.presenter.MainPresenter;
import com.githubrepositoryapp.presenter.view.MainView;
import com.githubrepositoryapp.util.PreferencesManager;
import com.githubrepositoryapp.presenter.BasePresenter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vladimir on 04.04.2017.
 */

public class MainPresenterImpl extends BasePresenter implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void user() {
        if (view != null) {
            view.showProgress();
        }

        String accessToken = PreferencesManager.getInstance().getGitHubAccessToken();

        Subscription subscription = ApiManager.getApi()
                .getUser(accessToken)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {

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
                    public void onNext(User user) {
                        view.setUserInfo(user);
                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

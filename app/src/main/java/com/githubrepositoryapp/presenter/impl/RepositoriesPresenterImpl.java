package com.githubrepositoryapp.presenter.impl;

import com.githubrepositoryapp.network.ApiManager;
import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.presenter.RepositoriesPresenter;
import com.githubrepositoryapp.presenter.view.RepositoriesView;
import com.githubrepositoryapp.presenter.BasePresenter;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vladimir on 05.04.2017.
 */

public class RepositoriesPresenterImpl extends BasePresenter implements RepositoriesPresenter {

    private RepositoriesView view;

    public RepositoriesPresenterImpl(RepositoriesView view) {
        this.view = view;
    }

    @Override
    public void getRepositories(String login) {
        if (view != null) {
            view.showProgress();
        }

        Subscription subscription = ApiManager.getApi()
                .getRepository(login)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

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
                    public void onNext(List<Repository> repositories) {
                        view.setRepositories(repositories);
                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}

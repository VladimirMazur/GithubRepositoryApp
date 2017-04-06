package com.githubrepositoryapp.presenter.impl;

import com.githubrepositoryapp.network.ApiManager;
import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.pojo.Search;
import com.githubrepositoryapp.presenter.BasePresenter;
import com.githubrepositoryapp.presenter.SearchResultPresenter;
import com.githubrepositoryapp.presenter.interactor.SearchInteractor;
import com.githubrepositoryapp.presenter.interactor.SearchInteractorImpl;
import com.githubrepositoryapp.presenter.view.SearchResultView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vladimir on 04.04.2017.
 */

public class SearchResultPresenterImpl extends BasePresenter implements SearchResultPresenter,
        SearchInteractor.OnSearchFinishedListener {

    private SearchResultView view;
    private SearchInteractorImpl interactor;

    public SearchResultPresenterImpl(SearchResultView view) {
        this.view = view;
        interactor = new SearchInteractorImpl();
    }

    @Override
    public void onError() {
        view.hideProgress();
        view.clearRecyclerView();
    }

    @Override
    public void onSuccess(String params) {
        Subscription subscription = ApiManager.getApi()
                .search(params)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Search>() {

                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                        view.hideProgress();
                        view.clearRecyclerView();
                    }

                    @Override
                    public void onNext(Search search) {
                        interactor.validateResult(search.getItems(), SearchResultPresenterImpl.this);
                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void setData(List<Repository> repositories) {
        view.setResultRepositories(repositories);
    }

    @Override
    public void search(String params) {
        if (view != null) {
            view.showProgress();
        }

        interactor.validateRequest(params, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}

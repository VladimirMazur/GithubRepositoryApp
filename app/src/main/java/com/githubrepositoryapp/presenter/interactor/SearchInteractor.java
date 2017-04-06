package com.githubrepositoryapp.presenter.interactor;

import com.githubrepositoryapp.pojo.Repository;

import java.util.List;

/**
 * Created by Vladimir on 06.04.2017.
 */

public interface SearchInteractor {

    interface OnSearchFinishedListener {
        void onError();
        void onSuccess(String params);
        void setData(List<Repository> repositories);
    }

    void validateRequest(String params, SearchInteractor.OnSearchFinishedListener listener);

    void validateResult(List<Repository> repositories, SearchInteractor.OnSearchFinishedListener listener);
}

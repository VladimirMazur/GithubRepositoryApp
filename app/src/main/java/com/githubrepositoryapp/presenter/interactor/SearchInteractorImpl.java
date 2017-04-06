package com.githubrepositoryapp.presenter.interactor;

import android.text.TextUtils;

import com.githubrepositoryapp.pojo.Repository;

import java.util.List;

/**
 * Created by Vladimir on 06.04.2017.
 */

public class SearchInteractorImpl implements SearchInteractor {

    @Override
    public void validateRequest(String params, OnSearchFinishedListener listener) {
        boolean error = false;

        if (TextUtils.isEmpty(params)){
            listener.onError();
            error = true;
        }

        if (!error){
            listener.onSuccess(params);
        }
    }

    @Override
    public void validateResult(List<Repository> repositories, OnSearchFinishedListener listener) {
        boolean error = false;

        if (repositories.isEmpty()){
            listener.onError();
            error = true;
        }

        if (!error){
            listener.setData(repositories);
        }
    }
}

package com.githubrepositoryapp.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vladimir on 05.04.2017.
 */

public class BasePresenter {

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void onStop() {
        compositeSubscription.unsubscribe();
    }
}

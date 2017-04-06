package com.githubrepositoryapp.presenter;

import android.net.Uri;

/**
 * Created by Vladimir on 05.04.2017.
 */

public interface AuthorizationPresenter {

    void login(Uri uri);
    void checkAuthorization();
    void onDestroy();
}

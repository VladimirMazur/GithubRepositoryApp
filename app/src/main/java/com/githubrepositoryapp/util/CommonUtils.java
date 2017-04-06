package com.githubrepositoryapp.util;

import com.squareup.okhttp.HttpUrl;

import java.util.Arrays;

/**
 * Created by Vladimir on 03.04.2017.
 */

public class CommonUtils {

    public static HttpUrl.Builder getHttpUrl(String scope) {
        return new HttpUrl.Builder()
                .scheme(AppConstants.SCHEME)
                .host(AppConstants.OAUTH_HOST)
                .addPathSegment(AppConstants.PARAMETER_LOGIN)
                .addPathSegment(AppConstants.PARAMETER_OAUTH)
                .addPathSegment(AppConstants.PARAMETER_AUTHORIZE)
                .addQueryParameter(AppConstants.PARAMETER_CLIENT_ID, AppConstants.CLIENT_ID)
                .addQueryParameter(AppConstants.PARAMETER_SCOPE, scope);
    }

}

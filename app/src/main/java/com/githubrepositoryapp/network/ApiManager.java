package com.githubrepositoryapp.network;

import com.githubrepositoryapp.util.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vladimir on 02.04.2017.
 */

public class ApiManager {

    private static ApiInterface api;
    private static Retrofit retrofit;

    private ApiManager() {}

    public static ApiInterface getApi() {
        if (api == null) {
            api = getRetrofit().create(ApiInterface.class);
        }
        return api;
    }

    // gson builder
    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    // gson
    private static Gson gson = gsonBuilder.setLenient().create();

    private static Retrofit getRetrofit() {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(getHeaderInterceptor())
                    .addInterceptor(getLoggingInterceptor()).build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    // Interceptor Logging
    private static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    // Interceptor Header content-type
    private static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(getRequestWithHeaders(chain.request()));
            }
        };
    }

    //add content-type to request
    private static Request getRequestWithHeaders(Request request) {
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.header("content-type", "application/json");
        return requestBuilder.build();
    }
}

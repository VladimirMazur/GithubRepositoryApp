package com.githubrepositoryapp.network;

import com.githubrepositoryapp.pojo.Auth;
import com.githubrepositoryapp.pojo.Repository;
import com.githubrepositoryapp.pojo.Search;
import com.githubrepositoryapp.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Vladimir on 02.04.2017.
 */

public interface ApiInterface {

    @GET("user")
    Observable<User> getUser(@Query("access_token") String accessToken);

    @GET("users/{user}/repos")
    Observable<List<Repository>> getRepository(@Path("user") String user);

    @GET("search/repositories")
    Observable<Search> search(@Query("q") String q);
}

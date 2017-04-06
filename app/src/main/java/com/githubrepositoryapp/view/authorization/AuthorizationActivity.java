/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.githubrepositoryapp.view.authorization;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.alorma.github.sdk.bean.dto.response.User;
import com.githubrepositoryapp.R;
import com.githubrepositoryapp.presenter.impl.AuthorizationPresenterImpl;
import com.githubrepositoryapp.util.CommonUtils;
import com.githubrepositoryapp.presenter.view.AuthorizationView;
import com.githubrepositoryapp.view.BaseActivity;
import com.githubrepositoryapp.view.main.MainActivity;
import com.squareup.okhttp.HttpUrl;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthorizationActivity extends BaseActivity implements AuthorizationView {

    private static final String[] scope = new String[]{"user", "public_repo"};
    public static final String INTENT_EXTRA_URL = "INTENT_EXTRA_URL";
    private static int REQUEST_CODE_LOGIN = 1;

    @BindView(R.id.login)
    Button login;

    private AuthorizationPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AuthorizationPresenterImpl(this);
        presenter.checkAuthorization();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    void onClickLogin() {
        navigateLoginWebView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.login(intent.getData());
    }

    private void navigateLoginWebView() {
        HttpUrl.Builder url = CommonUtils.getHttpUrl(Arrays.toString(scope));

        Intent intent = new Intent(this, LoginWebViewActivity.class);
        intent.putExtra(INTENT_EXTRA_URL, url.toString());
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_LOGIN && resultCode == RESULT_OK) {
            presenter.login(data.getData());
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void navigateMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void error(String error) {
        showMessage(error);
    }
}

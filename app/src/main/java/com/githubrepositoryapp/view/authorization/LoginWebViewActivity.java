package com.githubrepositoryapp.view.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.githubrepositoryapp.R;
import com.githubrepositoryapp.util.AppConstants;
import com.githubrepositoryapp.view.BaseActivity;

public class LoginWebViewActivity extends AppCompatActivity {

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createProgressDialog();

        WebView webView = new WebView(this);
        webView.loadUrl(getIntent().getStringExtra(AuthorizationActivity.INTENT_EXTRA_URL));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getQueryParameter(AppConstants.PARAMETER_CODE) != null && uri.getScheme() != null
                        && uri.getScheme().equalsIgnoreCase(AppConstants.SCHEME)) {
                    setActivityResultData(uri);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setContentView(webView);
    }

    private void setActivityResultData(Uri uri) {
        Intent data = new Intent();
        data.setData(uri);
        setResult(RESULT_OK, data);
        finish();
    }

    private void createProgressDialog() {
        progressDialog = new ProgressDialog(LoginWebViewActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.txt_loading));
    }
}

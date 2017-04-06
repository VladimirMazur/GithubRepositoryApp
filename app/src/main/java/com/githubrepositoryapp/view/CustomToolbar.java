package com.githubrepositoryapp.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubrepositoryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vladimir on 04.04.2017.
 */

public class CustomToolbar extends FrameLayout {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.txtOptions)
    TextView txtOptions;
    @BindView(R.id.btnOptions)
    ImageView btnOptions;

    public CustomToolbar(Context context) {
        super(context);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_toolbar, this, true);
        ButterKnife.bind(this);
    }

    /**
     * set title toolbar
     *
     * @param title
     */
    public void setTitle(String title) {
        txtTitle.setVisibility(VISIBLE);
        txtTitle.setText(title);
    }

    public void setTitle(boolean isShow, String title) {
        showTitle(isShow);
        txtTitle.setText(title);
    }

    public void showTitle(boolean isShow) {
        txtTitle.setVisibility(isShow ? VISIBLE : GONE);
    }


    /**
     * set back ImageView drawable toolbar
     *
     * @param resId
     */
    public void setButtonBack(int resId) {
        btnBack.setVisibility(VISIBLE);
        btnBack.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setButtonBack(boolean isShow, int resId) {
        showButtonBack(isShow);
        btnBack.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void showButtonBack(boolean isShow) {
        btnBack.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * set back ImageView drawable and add click listener toolbar
     *
     * @param resId
     * @param listener
     */
    public void setListenerButtonBack(int resId, OnClickListener listener) {
        setButtonBack(resId);
        btnBack.setOnClickListener(listener);
    }

    public void setListenerButtonBack(boolean isShow, int resId, OnClickListener listener) {
        setButtonBack(isShow, resId);
        btnBack.setOnClickListener(listener);
    }

    /**
     * set more options TextView toolbar
     *
     * @param text
     */
    public void setTextOptionMore(String text) {
        txtOptions.setVisibility(VISIBLE);
        txtOptions.setText(text);
    }

    public void setTextOptionMore(boolean isShow, String text) {
        showButtonOptionMore(isShow);
        txtOptions.setText(text);
    }

    public void showButtonOptionMore(boolean isShow) {
        txtOptions.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * set text and add click listener toolbar
     *
     * @param text
     * @param listener
     */
    public void setListenerTextOptionMore(String text, OnClickListener listener) {
        setTextOptionMore(text);
        txtOptions.setOnClickListener(listener);
    }

    public void setListenerTextOptionMore(boolean isShow, String text, OnClickListener listener) {
        setTextOptionMore(isShow, text);
        txtOptions.setOnClickListener(listener);
    }

    /**
     * set options ImageView drawable toolbar
     *
     * @param resId
     */
    public void setButtonOptions(int resId) {
        btnOptions.setVisibility(VISIBLE);
        btnOptions.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setButtonOptions(boolean isShow, int resId) {
        showButtonOptions(isShow);
        btnOptions.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void showButtonOptions(boolean isShow) {
        btnOptions.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * set options ImageView drawable and add click listener toolbar
     *
     * @param resId
     * @param listener
     */
    public void setListenerButtonOptions(int resId, OnClickListener listener) {
        setButtonOptions(resId);
        btnOptions.setOnClickListener(listener);
    }

    public void setListenerButtonOptions(boolean isShow, int resId, OnClickListener listener) {
        setButtonOptions(isShow, resId);
        btnOptions.setOnClickListener(listener);
    }

}

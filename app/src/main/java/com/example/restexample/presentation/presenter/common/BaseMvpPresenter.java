package com.example.restexample.presentation.presenter.common;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

/**
 * Created by grishberg on 13.11.16.
 */
public class BaseMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    private static final String TAG = BaseMvpPresenter.class.getSimpleName();
}

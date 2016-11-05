package com.example.mvpframework.presenters;

import com.example.mvpframework.view.MvpView;

/**
 * Created by grishberg on 04.11.16.
 */
public class BaseMvpPresenter<View extends MvpView> {
    private static final String TAG = BaseMvpPresenter.class.getSimpleName();
    private View view;

    public void attachView(final View view) {
        this.view = view;
    }

    public void detachView(final View view) {
        this.view = null;
    }
}

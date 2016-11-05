package com.example.mvpframework.view;

import com.example.mvpframework.presenters.BaseMvpPresenter;

/**
 * Created by grishberg on 04.11.16.
 */
public abstract class MvpViewHolder<V extends MvpView, Presenter extends BaseMvpPresenter<V>> implements MvpView {
    private static final String TAG = MvpViewHolder.class.getSimpleName();

    private Presenter presenter;

    public void attachView() {
        presenter.attachView((V) this);
    }

    public void detachView() {
        presenter.detachView((V) this);
    }

    protected abstract Presenter injectPresenter();
}

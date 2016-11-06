package com.example.mvpframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvpframework.presenters.BaseMvpPresenter;

/**
 * Created by grishberg on 04.11.16.
 */
public abstract class MvpActivity<Presenter extends BaseMvpPresenter> extends AppCompatActivity implements MvpView {
    private static final String TAG = MvpActivity.class.getSimpleName();

    private Presenter presenter;

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = injectPresenter();
        presenter = injectPresenter();
        if (presenter == null) {
            throw new IllegalStateException("Presenter must not be null!");
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected abstract Presenter injectPresenter();
}

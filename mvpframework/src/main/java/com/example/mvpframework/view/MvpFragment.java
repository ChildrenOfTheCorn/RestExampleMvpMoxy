package com.example.mvpframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpframework.presenters.BaseMvpPresenter;

/**
 * Created by grishberg on 04.11.16.
 */
public abstract class MvpFragment<V extends MvpView, Presenter extends BaseMvpPresenter<V>> extends Fragment implements MvpView {
    private static final String TAG = MvpFragment.class.getSimpleName();

    private Presenter presenter;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = injectPresenter();
        if (presenter == null) {
            throw new IllegalStateException("Presenter must not be null!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter.attachView((V) this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView((V) this);
    }

    protected abstract Presenter injectPresenter();
}

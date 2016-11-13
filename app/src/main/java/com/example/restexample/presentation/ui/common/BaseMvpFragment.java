package com.example.restexample.presentation.ui.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpDelegate;

/**
 * Created by grishberg on 13.11.16.
 */
public abstract class BaseMvpFragment extends Fragment {
    private static final String TAG = BaseMvpFragment.class.getSimpleName();

    private MvpDelegate<? extends BaseMvpFragment> mMvpDelegate;

    public BaseMvpFragment() {
    }

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        getMvpDelegate().onAttach();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getMvpDelegate().onDestroy();
    }

    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        getMvpDelegate().onSaveInstanceState(outState);
    }

    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }

        return mMvpDelegate;
    }
}
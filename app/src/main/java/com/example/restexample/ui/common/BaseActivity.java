package com.example.restexample.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpDelegate;

/**
 * Created by grishberg on 20.10.16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private MvpDelegate<? extends BaseActivity> mMvpDelegate;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getMvpDelegate().onAttach();
    }

    @Override
    protected void onStop() {
        super.onStop();

        getMvpDelegate().onDetach();
    }

    /**
     * @return The {@link MvpDelegate} being used by this Activity.
     */
    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }
        return mMvpDelegate;
    }
}

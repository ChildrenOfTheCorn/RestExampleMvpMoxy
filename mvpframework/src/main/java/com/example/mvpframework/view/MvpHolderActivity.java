package com.example.mvpframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishberg on 04.11.16.
 */
public class MvpHolderActivity extends AppCompatActivity implements MvpView {
    private static final String TAG = MvpHolderActivity.class.getSimpleName();
    private List<MvpViewHolder> views;

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new ArrayList<>();
    }

    protected void attachViewHolder(final MvpViewHolder viewHolder) {
        views.add(viewHolder);
        viewHolder.attachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (final MvpViewHolder viewHolder : views) {
            viewHolder.detachView();
        }
    }
}

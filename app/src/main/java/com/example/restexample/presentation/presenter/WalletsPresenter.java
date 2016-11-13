package com.example.restexample.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.restexample.App;
import com.example.restexample.presentation.presenter.common.BaseNetworkInteractionPresenter;
import com.example.restexample.presentation.view.WalletsView;

/**
 * Created by grishberg on 13.11.16.
 */
@InjectViewState
public class WalletsPresenter extends BaseNetworkInteractionPresenter<WalletsView> {
    private static final String TAG = WalletsPresenter.class.getSimpleName();

    public WalletsPresenter() {
        super();
    }
}

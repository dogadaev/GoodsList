package com.quaindinteractive.goodslist.presenter.implementation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void onDownloadClicked() {
        if (isInternetConnected()) {

        } else view.showDialog(R.string.no_internet_title, R.string.no_internet_message);
    }

    private boolean isInternetConnected() {
        ConnectivityManager cm = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } else {
            return false;
        }
    }
}

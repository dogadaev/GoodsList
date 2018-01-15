package com.quaindinteractive.goodslist.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.download_button) Button downloadButton;
    @BindView(R.id.show_goods_button) Button showButton;

    @Inject MainPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((DaggerApplication) getApplication()).getApplicationComponent(this).inject(this);
    }

    @OnClick({R.id.download_button, R.id.show_goods_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_button:
                presenter.onDownloadClicked();
                break;
            case R.id.show_goods_button:
                Intent intent = new Intent(this, GoodsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Пожалуйста, подождите.");
    }

    @Override
    public void hideProgress() {
        if(progressDialog != null) progressDialog.hide();
    }

    @Override
    public void disableDownloadButton() {
        downloadButton.setEnabled(false);
    }

    @Override
    public void enableDownloadButton() {
        downloadButton.setEnabled(true);
    }
}
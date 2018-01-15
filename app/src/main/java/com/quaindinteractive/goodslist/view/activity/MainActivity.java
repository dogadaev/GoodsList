package com.quaindinteractive.goodslist.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.view.MainView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter presenter;

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
                Intent intent = new Intent(this, GoodsAvtivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
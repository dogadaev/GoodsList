package com.quaindinteractive.goodslist.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.GoodsPresenter;
import com.quaindinteractive.goodslist.view.GoodsView;
import com.quaindinteractive.goodslist.view.ProductAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends BaseActivity implements GoodsView {

    @BindView(R.id.products_list) RecyclerView productsList;

    @Inject GoodsPresenter presenter;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        ButterKnife.bind(this);

        ((DaggerApplication) getApplication()).getApplicationComponent(this).inject(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productAdapter = new ProductAdapter();
        productsList.setLayoutManager(layoutManager);
        productsList.setAdapter(productAdapter);
    }

    @Override
    public void showGoods(List<Item> items) {
        productAdapter.setData(items);
    }
}

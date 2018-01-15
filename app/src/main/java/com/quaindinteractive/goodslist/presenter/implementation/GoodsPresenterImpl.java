package com.quaindinteractive.goodslist.presenter.implementation;

import android.util.Log;

import com.quaindinteractive.goodslist.model.ProductsModel;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.GoodsPresenter;
import com.quaindinteractive.goodslist.view.GoodsView;

import java.util.List;

public class GoodsPresenterImpl implements GoodsPresenter {

    private GoodsView view;
    private ProductsModel productsModel;

    public GoodsPresenterImpl(final GoodsView view, ProductsModel productsModel) {
        this.view = view;
        this.productsModel = productsModel;

        productsModel.loadProducts(new ProductsModel.LoadProductsCallback() {
            @Override
            public void onLoad(List<Item> products) {

//                Log.i("GoodsPresenter", products.isEmpty() + "");
                view.showGoods(products);
            }
        });
    }
}

package com.quaindinteractive.goodslist.view;

import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.implementation.GoodsPresenterImpl;

import java.util.List;

public interface GoodsView extends BaseView {

    void onEditClicked(int id, String oldName, float oldPrice, GoodsPresenterImpl.FieldEditedCallback editedCallback);
    void showGoods(List<Item> items);

}

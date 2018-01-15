package com.quaindinteractive.goodslist.presenter;

import com.quaindinteractive.goodslist.presenter.implementation.GoodsPresenterImpl;

public interface GoodsPresenter {

    void onEditClicked(int id, String newName, float newPrice, GoodsPresenterImpl.FieldEditedCallback editedCallback);
}

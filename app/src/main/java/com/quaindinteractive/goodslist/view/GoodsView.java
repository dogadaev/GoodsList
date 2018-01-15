package com.quaindinteractive.goodslist.view;

import com.quaindinteractive.goodslist.model.retrofit.Item;

import java.util.List;

public interface GoodsView extends BaseView {

    void showGoods(List<Item> items);

}

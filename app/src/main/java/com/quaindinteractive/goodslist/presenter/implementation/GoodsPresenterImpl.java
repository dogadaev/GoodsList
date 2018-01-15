package com.quaindinteractive.goodslist.presenter.implementation;

import android.content.ContentValues;
import android.util.Log;

import com.quaindinteractive.goodslist.model.ProductModel;
import com.quaindinteractive.goodslist.model.database.ProductsTable;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.GoodsPresenter;
import com.quaindinteractive.goodslist.view.GoodsView;

import java.util.List;

public class GoodsPresenterImpl implements GoodsPresenter {

    private GoodsView view;
    private ProductModel productModel;

    public interface FieldEditedCallback {
        void onComplete(String newName, float newPrice);
    }

    public GoodsPresenterImpl(final GoodsView view, ProductModel productModel) {
        this.view = view;
        this.productModel = productModel;

        productModel.loadProducts(new ProductModel.LoadProductsCallback() {
            @Override
            public void onLoad(List<Item> products) {
                view.showGoods(products);
            }
        });
    }

    @Override
    public void onEditClicked(int id, final String newName, final float newPrice, final FieldEditedCallback editedCallback) {
        ContentValues fieldsContentValues = new ContentValues();
        fieldsContentValues.put(ProductsTable.COLUMN.NAME, newName);
        fieldsContentValues.put(ProductsTable.COLUMN.PRICE, newPrice);
        ContentValues idContentValues = new ContentValues();
        idContentValues.put(ProductsTable.COLUMN.ID, id);

        productModel.updateProduct(fieldsContentValues, idContentValues, new ProductModel.UpdateProductCallback() {
            @Override
            public void onComplete() {
                editedCallback.onComplete(newName, newPrice);
            }
        });
    }
}

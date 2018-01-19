package com.quaindinteractive.goodslist.presenter.implementation;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.model.ProductModel;
import com.quaindinteractive.goodslist.model.database.ProductsTable;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.model.retrofit.MessagesApi;
import com.quaindinteractive.goodslist.model.retrofit.XmlList;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.view.MainView;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static com.quaindinteractive.goodslist.util.Config.BASE_URL;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private ProductModel productModel;

    private boolean areGoodsLoaded = false;

    public MainPresenterImpl(MainView view, ProductModel productModel) {
        this.view = view;
        this.productModel = productModel;

        productModel.loadProducts(new ProductModel.LoadProductsCallback() {
            @Override
            public void onLoad(List<Item> products) {
                if (products.isEmpty()) {
                    MainPresenterImpl.this.view.enableDownloadButton();
                    areGoodsLoaded = false;
                } else {
                    MainPresenterImpl.this.view.disableDownloadButton();
                    areGoodsLoaded = true;
                }
            }
        });
    }

    @Override
    public void onDownloadClicked() {
        if (isInternetConnected()) {
            if (!areGoodsLoaded) {
                view.showProgress();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).build();

                MessagesApi messagesApi = retrofit.create(MessagesApi.class);
                Call<XmlList> messages = messagesApi.messages();

                messages.enqueue(new Callback<XmlList>() {
                    @Override
                    public void onResponse(@NonNull Call<XmlList> call, @NonNull Response<XmlList> response) {
                        if (response.isSuccessful()) {

                            List<Item> products = response.body().getProducts().get(0).getItemsList();
                            final Iterator<Item> iterator = products.iterator();

                            while (iterator.hasNext()) {
                                ContentValues values = new ContentValues();
                                final Item item = iterator.next();
                                values.put(ProductsTable.COLUMN.ID, item.getId());
                                values.put(ProductsTable.COLUMN.NAME, item.getName());
                                values.put(ProductsTable.COLUMN.PRICE, item.getPrice());

                                productModel.addProduct(values, new ProductModel.AddProductCallback() {
                                    @Override
                                    public void onComplete() {
                                    }
                                });
                            }
                            view.disableDownloadButton();
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<XmlList> call, Throwable t) {
                        try {
                            view.hideProgress();
                            view.showDialog(R.string.error_title, t.getMessage());
                            throw t;
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });
            } else {
                view.disableDownloadButton();
            }

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
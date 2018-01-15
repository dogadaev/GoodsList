package com.quaindinteractive.goodslist.dagger.module;

import android.content.Context;

import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.model.ProductModel;
import com.quaindinteractive.goodslist.model.database.DatabaseHelper;
import com.quaindinteractive.goodslist.presenter.GoodsPresenter;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.presenter.implementation.GoodsPresenterImpl;
import com.quaindinteractive.goodslist.presenter.implementation.MainPresenterImpl;
import com.quaindinteractive.goodslist.view.GoodsView;
import com.quaindinteractive.goodslist.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private DaggerApplication daggerApplication;

    public ApplicationModule(DaggerApplication daggerApplication) {
        this.daggerApplication = daggerApplication;
    }

    @Provides MainPresenter provideMainPresenter(MainView view, ProductModel model) { return new MainPresenterImpl(view, model); }
    @Provides GoodsPresenter provideGoodsPresenter(GoodsView view, ProductModel model) { return new GoodsPresenterImpl(view, model); }

    @Provides MainView provideMainView() { return (MainView) daggerApplication.getView(); }
    @Provides GoodsView provideGoodsView() { return (GoodsView) daggerApplication.getView();}

    @Provides
    ProductModel provideUserModel(DatabaseHelper databaseHelper) { return new ProductModel(databaseHelper); }
    @Provides DatabaseHelper provideDatabaseHelper(Context context) { return new DatabaseHelper(context); }

    @Provides Context provideContext() { return  daggerApplication; }

}

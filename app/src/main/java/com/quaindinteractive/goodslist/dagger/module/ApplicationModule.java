package com.quaindinteractive.goodslist.dagger.module;

import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.presenter.MainPresenter;
import com.quaindinteractive.goodslist.presenter.implementation.MainPresenterImpl;
import com.quaindinteractive.goodslist.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private DaggerApplication daggerApplication;

    public ApplicationModule(DaggerApplication daggerApplication) {
        this.daggerApplication = daggerApplication;
    }

    @Provides MainPresenter provideMainPresenter(MainView view) {return new MainPresenterImpl(view);}

    @Provides MainView provideMainView() {return (MainView) daggerApplication.getView();}

}

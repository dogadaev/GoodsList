package com.quaindinteractive.goodslist.dagger;

import android.app.Application;

import com.quaindinteractive.goodslist.dagger.component.ApplicationComponent;
import com.quaindinteractive.goodslist.dagger.component.DaggerApplicationComponent;
import com.quaindinteractive.goodslist.dagger.module.ApplicationModule;
import com.quaindinteractive.goodslist.view.BaseView;

public class DaggerApplication extends Application {

    private BaseView view;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent(BaseView view) {
        this.view = view;
        return applicationComponent;
    }

    public BaseView getView() {
        return view;
    }
}
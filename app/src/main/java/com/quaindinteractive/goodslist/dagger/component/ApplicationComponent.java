package com.quaindinteractive.goodslist.dagger.component;

import com.quaindinteractive.goodslist.dagger.module.ApplicationModule;
import com.quaindinteractive.goodslist.view.activity.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
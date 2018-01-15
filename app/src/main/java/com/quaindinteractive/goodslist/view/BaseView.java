package com.quaindinteractive.goodslist.view;

import android.content.Context;

public interface BaseView {

    void showDialog(int titleId, int messageId);
    void showDialog(int titleId, String message);
    void showDialog(String title, String message);
    Context getContext();
}

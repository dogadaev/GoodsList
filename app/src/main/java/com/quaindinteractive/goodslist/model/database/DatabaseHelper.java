package com.quaindinteractive.goodslist.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.quaindinteractive.goodslist.model.database.ProductsTable.CREATE_SCRIPT;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "goods_list";
    private static final int VERSION = 1;

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SCRIPT);
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
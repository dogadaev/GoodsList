package com.quaindinteractive.goodslist.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.quaindinteractive.goodslist.model.database.ProductsTable.CREATE_SCRIPT;
import static com.quaindinteractive.goodslist.model.database.ProductsTable.TABLE;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NAME = "goods_list";
    public static final int VERSION = 1;

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

    public boolean isDataInDatabase(String dbfield, String fieldValue) {
        String Query = "SELECT PID FROM products WHERE PID=1";
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

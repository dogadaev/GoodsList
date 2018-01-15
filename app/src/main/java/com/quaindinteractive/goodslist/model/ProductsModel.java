package com.quaindinteractive.goodslist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import com.quaindinteractive.goodslist.model.database.DatabaseHelper;
import com.quaindinteractive.goodslist.model.database.ProductsTable;
import com.quaindinteractive.goodslist.model.retrofit.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductsModel {

    private static DatabaseHelper databaseHelper;

    public ProductsModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void loadProducts(LoadProductsCallback loadProductsCallback) {
        LoadProductsTask loadProductsTask = new LoadProductsTask(loadProductsCallback);
        loadProductsTask.execute();
    }

    public void addProduct(ContentValues contentValues, AddProductCallback addProductCallback) {
        AddProductTask addProductTask = new AddProductTask(addProductCallback);
        addProductTask.execute(contentValues);
    }

    public void clearProducts(ClearProductsCallback clearProductsCallback) {
        ClearProductsTask clearProductsTask = new ClearProductsTask(clearProductsCallback);

    }

    public interface LoadProductsCallback {
        void onLoad(List<Item> products);
    }

    public interface AddProductCallback {
        void onComplete();
    }

    public interface ClearProductsCallback {
        void onComplete();
    }

    static class LoadProductsTask extends AsyncTask<Void, Void, List<Item>> {

        private final LoadProductsCallback callback;

        LoadProductsTask(LoadProductsCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Item> doInBackground(Void... params) {
            List<Item> products = new LinkedList<>();

            Cursor cursor = databaseHelper.getReadableDatabase().query(ProductsTable.TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Item item = new Item();
                item.setId(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN.ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN.NAME)));
                item.setPrice(cursor.getFloat(cursor.getColumnIndex(ProductsTable.COLUMN.PRICE)));
                products.add(item);
            }
            cursor.close();
            return products;
        }

        @Override
        protected void onPostExecute(List<Item> products) {
            if (callback != null) {
                callback.onLoad(products);
            }
        }
    }

    static class AddProductTask extends AsyncTask<ContentValues, Void, Void> {

        private final AddProductCallback callback;

        AddProductTask(AddProductCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(ContentValues... params) {
            ContentValues cvUser = params[0];
            databaseHelper.getWritableDatabase().insert(ProductsTable.TABLE, null, cvUser);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    static class ClearProductsTask extends AsyncTask<Void, Void, Void> {

        private final ClearProductsCallback callback;

        ClearProductsTask(ClearProductsCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            databaseHelper.getWritableDatabase().delete(ProductsTable.TABLE, null, null);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}

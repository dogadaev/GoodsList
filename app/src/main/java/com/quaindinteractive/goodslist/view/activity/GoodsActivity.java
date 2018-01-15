package com.quaindinteractive.goodslist.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.dagger.DaggerApplication;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.GoodsPresenter;
import com.quaindinteractive.goodslist.presenter.implementation.GoodsPresenterImpl;
import com.quaindinteractive.goodslist.view.GoodsView;
import com.quaindinteractive.goodslist.view.ProductAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends BaseActivity implements GoodsView {

    @BindView(R.id.products_list) RecyclerView productsList;

    @Inject GoodsPresenter presenter;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        ButterKnife.bind(this);

        ((DaggerApplication) getApplication()).getApplicationComponent(this).inject(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productAdapter = new ProductAdapter(this);
        productsList.setLayoutManager(layoutManager);
        productsList.setAdapter(productAdapter);
    }

    @Override
    public void onEditClicked(int id, String oldName, float oldPrice, final GoodsPresenterImpl.FieldEditedCallback editedCallback) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Изменить поле №" + id);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText name = new EditText(this);
        name.setHint(R.string.edit_name_hint);
        name.setLayoutParams(lp);
        linearLayout.addView(name);

        final EditText price = new EditText(this);
        price.setHint(R.string.edit_price_hint);
        price.setLayoutParams(lp);
        price.setRawInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_FLAG_DECIMAL);
        linearLayout.addView(price);

        final int finalId = id;
        final String finalNewName = oldName;
        final float finalNewPrice = oldPrice;
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = finalNewName;
                float newPrice = finalNewPrice;

                if(!name.getText().toString().isEmpty()) newName = name.getText().toString();
                if(price.getText().length() > 0) newPrice = Float.valueOf(price.getText().toString().replace(',','.'));

                presenter.onEditClicked(finalId, newName, newPrice, editedCallback);
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setView(linearLayout);
        builder.show();
    }

    @Override
    public void showGoods(List<Item> items) {
        productAdapter.setData(items);
    }
}
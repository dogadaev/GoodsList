package com.quaindinteractive.goodslist.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.model.retrofit.Item;
import com.quaindinteractive.goodslist.presenter.implementation.GoodsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private static GoodsView view;
    private List<Item> data = new ArrayList<>();

    public ProductAdapter(GoodsView view) {
        this.view = view;
    }

    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Item> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    static class ProductHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product)
        TextView text;
        @BindView(R.id.edit_filed_button)
        ImageButton editButton;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Item item) {
            text.setText(String.format("ID: %s, НАЗВАНИЕ: '%s', ЦЕНА: %s", item.getId(), item.getName(), item.getPrice()));

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.onEditClicked(item.getId(), item.getName(), item.getPrice(), new GoodsPresenterImpl.FieldEditedCallback() {
                        @Override
                        public void onComplete(String newName, float newPrice) {
                            text.setText(String.format("ID: %s, НАЗВАНИЕ: '%s', ЦЕНА: %s", item.getId(), newName, newPrice));
                        }
                    });
                }
            });
        }
    }
}
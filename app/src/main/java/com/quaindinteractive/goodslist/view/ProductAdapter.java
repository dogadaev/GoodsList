package com.quaindinteractive.goodslist.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quaindinteractive.goodslist.R;
import com.quaindinteractive.goodslist.model.retrofit.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    List<Item> data = new ArrayList<>();

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

        @BindView(R.id.product) TextView text;

        public ProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Item item) {
            text.setText(String.format("id: %s, name: %s, price: %s", item.getId(), item.getName(), item.getPrice()));
        }
    }

}

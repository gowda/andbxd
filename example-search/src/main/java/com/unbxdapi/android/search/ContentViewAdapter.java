package com.unbxdapi.android.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unbxdapi.android.search.models.Product;

import java.util.ArrayList;

import com.unbxdapi.android.R;

public class ContentViewAdapter extends RecyclerView.Adapter <ContentViewAdapter.ProductViewHolder> {
    ArrayList<Product> mProducts;

    public void setProducts(ArrayList<Product> products) {
        mProducts = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.mTitleView.setText(String.valueOf(mProducts.get(position).get("title")));
        holder.mPriceView.setText(String.valueOf(mProducts.get(position).get("price")));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitleView;
        TextView mPriceView;

        public ProductViewHolder(View v) {
            super(v);

            mImageView = (ImageView) v.findViewById(R.id.image);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mPriceView = (TextView) v.findViewById(R.id.price);
        }
    }
}

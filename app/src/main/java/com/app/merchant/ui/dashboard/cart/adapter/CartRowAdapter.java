package com.app.merchant.ui.dashboard.cart.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.CartRowItemBinding;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class CartRowAdapter extends RecyclerView.Adapter<CartRowAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<ProductData> mCartList;
    private final AppCompatActivity activity;
    private OnAddToCart listener;

    public interface OnAddToCart {
        void addToCartClick(int pos, View view);
    }

    public CartRowAdapter(AppCompatActivity activity, ArrayList<ProductData> mCartList, OnAddToCart listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.mCartList = mCartList;
        this.listener = listener;
    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CartRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.cart_row_item, parent, false);
        return new CartHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(mCartList) ? mCartList.size() : 0;
    }

    class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CartRowItemBinding itemBinding;

        public CartHolder(CartRowItemBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
            itemView.ivAdd.setOnClickListener(this);
            itemView.ivSub.setOnClickListener(this);
            itemView.ivDeleteCart.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            View root = itemBinding.getRoot();
            root.setTag(view);
            if (view == itemBinding.ivAdd) {
                listener.addToCartClick(getAdapterPosition(), root);
            } else if (view == itemBinding.ivSub) {
                listener.addToCartClick(getAdapterPosition(), root);
            } else if (view == itemBinding.ivDeleteCart) {
                listener.addToCartClick(getAdapterPosition(), root);
            }
        }

        public void setData(int position) {
            if (CommonUtility.isNotNull(mCartList) && mCartList.size() > position) {
                itemBinding.setCartData(mCartList.get(position));
                itemBinding.tvQuantity.setText(String.valueOf(mCartList.get(position).getQty()));
                GlideUtils.loadImage(activity, mCartList.get(position).getIcon(), itemBinding.ivProductImage, null, R.drawable.icon_placeholder);

            }
        }
    }
}

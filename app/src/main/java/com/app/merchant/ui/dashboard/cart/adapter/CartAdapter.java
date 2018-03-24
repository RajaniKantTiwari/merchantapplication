package com.app.merchant.ui.dashboard.cart.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.ItemCartBinding;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ProductData> mDataList = new ArrayList<>();
    private OnAddToCart mListener;


    public CartAdapter(ArrayList<ProductData> cartDataList, OnAddToCart listener) {
        this.mDataList = cartDataList;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ItemCartBinding cartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false);
        return new CartAdapter.ItemCart(cartBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartAdapter.ItemCart itemCat = (CartAdapter.ItemCart) holder;
        itemCat.bind(position);
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }


    private class ItemCart extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemCartBinding mBinding;

        public ItemCart(ItemCartBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.layoutInfo.setOnClickListener(this);
            mBinding.layoutProduct.setOnClickListener(this);
            mBinding.ivPlus.setOnClickListener(this);
            mBinding.ivMinus.setOnClickListener(this);
            mBinding.tvSubscribe.setOnClickListener(this);
            mBinding.tvAddInventory.setOnClickListener(this);
        }

        public void bind(int position) {
            mBinding.ivMinus.setTag(position);
            mBinding.ivPlus.setTag(position);
            mBinding.layoutProduct.setTag(position);
            mBinding.layoutInfo.setTag(position);
            mBinding.tvSubscribe.setTag(position);
            mBinding.tvAddInventory.setTag(position);
            mBinding.tvProductName.setText(CommonUtility.setNameWithMrp(mDataList.get(position).getProductname(),mDataList.get(position).getProduct_mrp()));
            mBinding.tvProductPrice.setText(String.valueOf(mDataList.get(position).getProduct_mrp()));
            mBinding.tvQty.setText(String.valueOf(mDataList.get(position).getQty()));
            mBinding.tvProductQty.setText(mDataList.get(position).getMeasure());
            if (!TextUtils.isEmpty(mDataList.get(position).getIcon())) {
                GlideUtils.loadImage(mContext, mDataList.get(position).getIcon(), mBinding.ivProduct, null, R.drawable.app_icon);
            } else {
                mBinding.ivProduct.setImageResource(R.drawable.app_icon);
            }
        }


        @Override
        public void onClick(View view) {
            View root = mBinding.getRoot();
            root.setTag(view);
            int pos = (Integer) view.getTag();
            switch (view.getId()) {
                case R.id.ivPlus:
                    mListener.addToCartClick(pos, root);
                    break;
                case R.id.ivMinus:
                    mListener.addToCartClick(pos, root);
                    break;
                case R.id.layoutProduct:
                case R.id.layoutInfo:
                    mListener.addToCartClick(pos, root);
                    break;
                case R.id.tvAddInventory:
                    mListener.addToCartClick(pos, root);
                    break;
            }
        }
    }

    public interface OnAddToCart {
        void addToCartClick(int pos, View view);

    }
}


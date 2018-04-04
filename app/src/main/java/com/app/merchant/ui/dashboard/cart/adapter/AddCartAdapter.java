package com.app.merchant.ui.dashboard.cart.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.ItemAddCartBinding;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;

import java.util.ArrayList;


public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.AddItemCart> {
    private Context mContext;
    private ArrayList<Product> mDataList = new ArrayList<>();
    private OnAddToCart mListener;


    public AddCartAdapter(ArrayList<Product> cartDataList, OnAddToCart listener) {
        this.mDataList = cartDataList;
        mListener = listener;
    }

    @Override
    public AddItemCart onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ItemAddCartBinding cartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_add_cart, parent, false);
        return new AddItemCart(cartBinding);
    }

    @Override
    public void onBindViewHolder(AddItemCart holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }


     class AddItemCart extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemAddCartBinding mBinding;

        public AddItemCart(ItemAddCartBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.layoutInfo.setOnClickListener(this);
            mBinding.layoutProduct.setOnClickListener(this);
            mBinding.layoutInventory.setOnClickListener(this);
        }

        public void bind(int position) {
            mBinding.layoutInventory.setTag(position);
            mBinding.layoutProduct.setTag(position);
            mBinding.layoutInfo.setTag(position);
            mBinding.tvProductName.setText(CommonUtility.setNameWithMrp(mDataList.get(position).getProductname(),mDataList.get(position).getProduct_mrp()));
            mBinding.tvProductPrice.setText(String.valueOf(mDataList.get(position).getMrp()));
            mBinding.tvProductPrice.setPaintFlags(mBinding.tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mBinding.tvSellingPrice.setText(String.valueOf(mDataList.get(position).getAvg_price()));
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
                case R.id.layoutInventory:
                    mListener.addToCartClick(pos, root);
                    break;
            }
        }
    }

    public interface OnAddToCart {
        void addToCartClick(int pos, View view);

    }
}


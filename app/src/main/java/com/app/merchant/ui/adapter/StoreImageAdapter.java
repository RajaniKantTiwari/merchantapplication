package com.app.merchant.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.app.merchant.R;
import com.app.merchant.databinding.ProductRowBinding;
import com.app.merchant.network.request.dashboard.StoreImage;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class StoreImageAdapter extends RecyclerView.Adapter<StoreImageAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<StoreImage> storeList;
    private ImageListener listener;

    public interface ImageListener {
        void onItemClick(int position);
    }

    public StoreImageAdapter(AppCompatActivity activity, List<StoreImage> storeList, ImageListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.storeList = storeList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.product_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(storeList) && storeList.size() > position) {
            holder.setData(storeList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(storeList) ? storeList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ProductRowBinding mBinding;

        public ProductViewHolder(ProductRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.productImage.setOnClickListener(this);
        }

        public void setData(StoreImage product) {
            if (CommonUtility.isNotNull(product)) {
                mBinding.tvProductName.setText(product.getStoreName());
                if (CommonUtility.isNotNull(product.getImageUrl())) {
                    mBinding.cameraImage.setVisibility(View.GONE);
                    GlideUtils.loadImageRoundedCorner(activity, product.getImageUrl(), mBinding.productImage, null,
                            R.drawable.icon_placeholder, AppConstants.CORNER_RADIUS);
                } else {
                    mBinding.cameraImage.setVisibility(View.VISIBLE);
                    mBinding.productImage.setImageResource(0);
                }
            }

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}

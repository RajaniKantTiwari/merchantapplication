package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.ItemOrderRowBinding;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;
import com.app.merchant.widget.CustomEditText;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<Product> productList;
    private OrderListener listener;


    public interface OrderListener {
        void onEdit(CustomEditText edMrp, CustomEditText edSellingPrice, int position);
        void onCancel(int position);
    }

    public OrderListAdapter(AppCompatActivity activity, ArrayList<Product> productList, OrderListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList = productList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.item_order_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(productList) && productList.size() > position) {
            Product product=productList.get(position);
            GlideUtils.loadImage(activity, product.getIcon(), holder.ivProduct, null, R.drawable.icon_placeholder);
            holder.tvProductName.setText(product.getProductname());
            holder.tvQuantity.setText(String.valueOf(product.getQty()));
            holder.edMrp.setText(CommonUtility.twoDecimalPlace(product.getProduct_mrp()));
            holder.edSellingPrice.setText(CommonUtility.twoDecimalPlaceString(product.getSelling_price()));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(productList) ? productList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemOrderRowBinding mBinding;
        private final CustomTextView tvProductName;
        private final CustomEditText tvQuantity;
        private final CustomEditText edMrp;
        private final CustomEditText edSellingPrice;
        private ImageView ivProduct;

        public ProductViewHolder(ItemOrderRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            ivProduct = itemView.ivProduct;
            tvProductName = itemView.tvProductName;
            tvQuantity = itemView.tvQuantity;
            edMrp = itemView.edMrp;
            edSellingPrice = itemView.edSellingPrice;
            itemView.ivEditOrder.setOnClickListener(this);
            itemView.ivCancel.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (view == mBinding.ivEditOrder) {
                listener.onEdit(mBinding.edMrp,mBinding.edSellingPrice,getAdapterPosition());
            } else if (view == mBinding.ivCancel) {
                listener.onCancel(getAdapterPosition());

            }
        }
    }
}

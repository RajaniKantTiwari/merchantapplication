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
import com.app.merchant.databinding.ProductRowBinding;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<ProductData> productList;
    private OrderListener listener;



    public interface OrderListener {
        void onOrderClick(int position);
    }
    public OrderListAdapter(AppCompatActivity activity, ArrayList<ProductData> productList, OrderListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.productList=productList;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.item_order_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(productList)?productList.size():0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemOrderRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(ItemOrderRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }



        @Override
        public void onClick(View view) {
            listener.onOrderClick(getAdapterPosition());
        }
    }
}

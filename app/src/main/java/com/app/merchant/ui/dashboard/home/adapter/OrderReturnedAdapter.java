package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderReturnedCancelRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReturnedAdapter extends RecyclerView.Adapter<OrderReturnedAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;

    public OrderReturnedAdapter(AppCompatActivity activity) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReturnedCancelRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_returned_cancel_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final OrderReturnedCancelRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(OrderReturnedCancelRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

    }
}

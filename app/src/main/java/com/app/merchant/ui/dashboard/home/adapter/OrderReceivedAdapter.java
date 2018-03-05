package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderReceivedRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReceivedAdapter extends RecyclerView.Adapter<OrderReceivedAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OrderReceivedListener listener;
    public interface OrderReceivedListener {
        void onOrderStatusClick(int position);
    }
    public OrderReceivedAdapter(AppCompatActivity activity,OrderReceivedListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReceivedRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_received_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderReceivedRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(OrderReceivedRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.layoutOrderStatus.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            listener.onOrderStatusClick(getAdapterPosition());
        }
    }
}

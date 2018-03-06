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

public class OrderConfirmedAdapter extends RecyclerView.Adapter<OrderConfirmedAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OrderConfirmedListener listener;
    public interface OrderConfirmedListener {
        void onOrderConfirmClick(int position);
    }
    public OrderConfirmedAdapter(AppCompatActivity activity, OrderConfirmedListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReceivedRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_confirmed_row, parent, false);
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
            listener.onOrderConfirmClick(getAdapterPosition());
        }
    }
}

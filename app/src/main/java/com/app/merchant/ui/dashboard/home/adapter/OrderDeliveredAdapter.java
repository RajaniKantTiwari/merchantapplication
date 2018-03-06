package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderDeliveredRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderDeliveredAdapter extends RecyclerView.Adapter<OrderDeliveredAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    //private OrderOutForDeliveryListener listener;
    /*public interface OrderOutForDeliveryListener {
        void onRatingClick(int position);
    }*/
    public OrderDeliveredAdapter(AppCompatActivity activity/*, OrderOutForDeliveryListener listener*/) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        /*this.listener=listener;*/
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderDeliveredRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_delivered_row, parent, false);
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
        private final OrderDeliveredRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(OrderDeliveredRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            //itemView.layoutRating.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
        }
    }
}

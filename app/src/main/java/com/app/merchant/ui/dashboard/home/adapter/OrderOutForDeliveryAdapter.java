package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderOutForDeliveryRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDelivery;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderOutForDeliveryAdapter extends RecyclerView.Adapter<OrderOutForDeliveryAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderOutForDelivery> deliveryList;
    private OrderOutForDeliveryListener listener;
    public interface OrderOutForDeliveryListener {
        void onRatingClick(int position);
    }
    public OrderOutForDeliveryAdapter(AppCompatActivity activity, ArrayList<OrderOutForDelivery> deliveryList, OrderOutForDeliveryListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.deliveryList=deliveryList;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderOutForDeliveryRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_out_for_delivery_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(deliveryList)?deliveryList.size():0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderOutForDeliveryRowBinding mBinding;

        public ProductViewHolder(OrderOutForDeliveryRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.layoutRating.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            listener.onRatingClick(getAdapterPosition());
        }
    }
}

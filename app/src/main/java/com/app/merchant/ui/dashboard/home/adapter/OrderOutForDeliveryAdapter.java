package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderOutForDeliveryRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDelivery;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

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
        this.deliveryList = deliveryList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderOutForDeliveryRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_out_for_delivery_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(deliveryList) && deliveryList.size() > position) {
            OrderOutForDelivery delivery = deliveryList.get(position);
            if (CommonUtility.isNotNull(delivery)) {
                holder.tvInvoice.setText(delivery.getInvoiceNumber());
                holder.tvAmmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), delivery.getTotaldue()));
                holder.tvDeliveryBoy.setText(delivery.getDeliveryboy());
                holder.ratingBar.setRating(delivery.getRatings() == null ?
                        0 : Float.parseFloat(delivery.getRatings()));
            }
        }


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(deliveryList) ? deliveryList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderOutForDeliveryRowBinding mBinding;
        private final CustomTextView tvInvoice;
        private final CustomTextView tvAmmount;
        private final CustomTextView tvPaymentMode;
        private final CustomTextView tvDeliveryBoy;
        private final RatingBar ratingBar;

        public ProductViewHolder(OrderOutForDeliveryRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvInvoice = itemView.tvInvoice;
            tvAmmount = itemView.tvAmmount;
            tvPaymentMode = itemView.tvPaymentMode;
            tvDeliveryBoy = itemView.tvDeliveryBoy;
            ratingBar = itemView.ratingBar;
            itemView.layoutRating.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onRatingClick(getAdapterPosition());
        }
    }
}

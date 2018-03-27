package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderCancelRequestRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequest;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderCancelRequestAdapter extends RecyclerView.Adapter<OrderCancelRequestAdapter.OrderCancelRequestViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderCancelRequest> cancelList;
    private OrderreturnRequestListener listener;

    public interface OrderreturnRequestListener {
        void onRatingClick(int position);
        void orderDetailClick(int position);
    }

    public OrderCancelRequestAdapter(AppCompatActivity activity, ArrayList<OrderCancelRequest> deliveryList, OrderreturnRequestListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.cancelList = deliveryList;
        this.listener = listener;
    }

    @Override
    public OrderCancelRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderCancelRequestRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_cancel_request_row, parent, false);
        return new OrderCancelRequestViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OrderCancelRequestViewHolder holder, int position) {
        if (CommonUtility.isNotNull(cancelList) && cancelList.size() > position) {
            OrderCancelRequest delivery = cancelList.get(position);
            if (CommonUtility.isNotNull(delivery)) {
                holder.tvInvoice.setText(delivery.getInvoiceNumber());
                holder.tvAmmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), delivery.getTotaldue()));
                holder.tvStatus.setText(delivery.getPaymentStatus());
                if (delivery.getPaymentStatus().equalsIgnoreCase(activity.getResources().getString(R.string.paid))) {
                    holder.tvStatus.setTextColor(CommonUtility.getColor(activity, R.color.green_order));
                } else {
                    holder.tvStatus.setTextColor(CommonUtility.getColor(activity, R.color.black));
                }
                holder.tvDeliveryBoy.setText(delivery.getDeliveryboy());
                holder.ratingBar.setRating(delivery.getRatings() == null ?
                        0 : Float.parseFloat(delivery.getRatings()));
            }
        }


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(cancelList) ? cancelList.size() : 0;
    }

    class OrderCancelRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomTextView tvInvoice;
        private final CustomTextView tvAmmount;
        private final CustomTextView tvDeliveryBoy;
        private final RatingBar ratingBar;
        private final CustomTextView tvStatus;
        private final OrderCancelRequestRowBinding mBinding;

        public OrderCancelRequestViewHolder(OrderCancelRequestRowBinding itemView) {
            super(itemView.getRoot());
            mBinding=itemView;
            tvInvoice = itemView.tvInvoice;
            tvAmmount = itemView.tvAmmount;
            tvStatus=itemView.tvStatus;
            tvDeliveryBoy = itemView.tvDeliveryBoy;
            ratingBar = itemView.ratingBar;
            itemView.layoutRating.setOnClickListener(this);
            itemView.tvInvoice.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(CommonUtility.isNotNull(listener)){
                if(view==mBinding.layoutRating){
                    listener.onRatingClick(getAdapterPosition());
                }else if(view==mBinding.tvInvoice){
                    listener.orderDetailClick(getAdapterPosition());
                }
            }
        }
    }
}

package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderAssignDeliveryBoyRowBinding;
import com.app.merchant.databinding.OrderReturnedRequestRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy.AssignDeliveryBoy;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequest;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReturnedRequestAdapter extends RecyclerView.Adapter<OrderReturnedRequestAdapter.AssignDeliveryBoyViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderReturnRequest> deliveryList;
    private OrderreturnRequestListener listener;

    public interface OrderreturnRequestListener {
        void onRatingClick(int position);

        void orderDetailClick(int position);
    }

    public OrderReturnedRequestAdapter(AppCompatActivity activity, ArrayList<OrderReturnRequest> deliveryList, OrderreturnRequestListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.deliveryList = deliveryList;
        this.listener = listener;
    }

    @Override
    public AssignDeliveryBoyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReturnedRequestRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_returned_request_row, parent, false);
        return new AssignDeliveryBoyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(AssignDeliveryBoyViewHolder holder, int position) {
        if (CommonUtility.isNotNull(deliveryList) && deliveryList.size() > position) {
            OrderReturnRequest delivery = deliveryList.get(position);
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
        return CommonUtility.isNotNull(deliveryList) ? deliveryList.size() : 0;
    }

    class AssignDeliveryBoyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomTextView tvInvoice;
        private final CustomTextView tvAmmount;
        private final CustomTextView tvDeliveryBoy;
        private final RatingBar ratingBar;
        private final CustomTextView tvStatus;
        private final OrderReturnedRequestRowBinding mBinding;

        public AssignDeliveryBoyViewHolder(OrderReturnedRequestRowBinding itemView) {
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
            if(CommonUtility.isNotNull(tvInvoice)){
                if(view==mBinding.layoutRating){
                    listener.onRatingClick(getAdapterPosition());
                }else if(view==mBinding.tvInvoice){
                    listener.orderDetailClick(getAdapterPosition());
                }
            }
        }
    }
}

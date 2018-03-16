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
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDelivered;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderDeliveredAdapter extends RecyclerView.Adapter<OrderDeliveredAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderDelivered> deliveredList;

    //private OrderOutForDeliveryListener listener;
    /*public interface OrderOutForDeliveryListener {
        void onRatingClick(int position);
    }*/
    public OrderDeliveredAdapter(AppCompatActivity activity,/*, OrderOutForDeliveryListener listener*/ArrayList<OrderDelivered> deliveredList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.deliveredList = deliveredList;
        /*this.listener=listener;*/
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderDeliveredRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_delivered_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(deliveredList) && deliveredList.size() > position) {
            OrderDelivered delivered = deliveredList.get(position);
            if (CommonUtility.isNotNull(delivered)) {
                holder.tvOrderNumber.setText(delivered.getInvoiceNumber());
                //holder.tvReceivedTime.setText();
                //holder.tvPromiseTime.setText();
                //holder.tvAssignTo.setText();
                holder.tvPaymentStatus.setText(delivered.getPaymentStatus());
                holder.tvPaymentStatus.setTextColor(delivered.getPaymentStatus().equalsIgnoreCase(activity.getResources().getString(R.string.paid)) ?
                        CommonUtility.getColor(activity, R.color.green_order) : CommonUtility.getColor(activity, R.color.black));
            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(deliveredList) ? deliveredList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CustomTextView tvOrderNumber;
        private final CustomTextView tvReceivedTime;
        private final CustomTextView tvPromiseTime;
        private final CustomTextView tvAssignTo;
        private final CustomTextView tvPaymentStatus;

        public ProductViewHolder(OrderDeliveredRowBinding itemView) {
            super(itemView.getRoot());
            tvOrderNumber = itemView.tvOrderNumber;
            tvReceivedTime = itemView.tvReceivedTime;
            tvPromiseTime = itemView.tvPromiseTime;
            tvAssignTo = itemView.tvAssignTo;
            tvPaymentStatus = itemView.tvPaymentStatus;
            //itemView.layoutRating.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
        }

    }
}

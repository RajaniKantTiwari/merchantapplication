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
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceived;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReceivedAdapter extends RecyclerView.Adapter<OrderReceivedAdapter.OrderReceivedViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderReceived> orderReceivedList;
    private OrderReceivedListener listener;

    public interface OrderReceivedListener {
        void onOrderStatusClick(int position);
    }

    public OrderReceivedAdapter(AppCompatActivity activity, ArrayList<OrderReceived> orderReceivedList, OrderReceivedListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.orderReceivedList = orderReceivedList;
        this.listener = listener;
    }

    @Override
    public OrderReceivedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReceivedRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_received_row, parent, false);
        return new OrderReceivedViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OrderReceivedViewHolder holder, int position) {
        if (CommonUtility.isNotNull(orderReceivedList) && orderReceivedList.size() > position) {
            OrderReceived received = orderReceivedList.get(position);
            if (CommonUtility.isNotNull(received)) {
                holder.tvId.setText(received.getInvoiceNumber());
                holder.tvTime.setText(CommonUtility.formatTimeHHMM(received.getCreatedAt()));
                holder.tvAmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), received.getTotaldue()));
                holder.tvPaymentStatus.setText(received.getPaymentStatus());
                if (received.getPaymentStatus().equalsIgnoreCase(activity.getResources().getString(R.string.paid))) {
                    holder.tvPaymentStatus.setTextColor(CommonUtility.getColor(activity, R.color.green_order));
                } else {
                    holder.tvPaymentStatus.setTextColor(CommonUtility.getColor(activity, R.color.black));
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderReceivedList) ? orderReceivedList.size() : 0;
    }

    class OrderReceivedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomTextView tvId;
        private final CustomTextView tvTime;
        private final CustomTextView tvAmount;
        private final CustomTextView tvPaymentStatus;

        public OrderReceivedViewHolder(OrderReceivedRowBinding itemView) {
            super(itemView.getRoot());
            tvId = itemView.tvId;
            tvTime = itemView.tvTime;
            tvAmount = itemView.tvAmount;
            tvPaymentStatus = itemView.tvPaymentStatus;
            itemView.tvPaymentStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onOrderStatusClick(getAdapterPosition());
        }

    }
}

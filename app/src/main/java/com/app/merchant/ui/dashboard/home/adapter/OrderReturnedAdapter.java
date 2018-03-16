package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderReturnedCancelRowBinding;
import com.app.merchant.databinding.OrderReturnedRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderreturned.OrderReturned;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReturnedAdapter extends RecyclerView.Adapter<OrderReturnedAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderReturned> orderReturnedList;

    public OrderReturnedAdapter(AppCompatActivity activity, ArrayList<OrderReturned> orderReturnedList) {
        mInflater = LayoutInflater.from(activity);
        this.orderReturnedList = orderReturnedList;
        this.activity = activity;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReturnedRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_returned_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(orderReturnedList)&&orderReturnedList.size()>position) {
            OrderReturned orderReturned = orderReturnedList.get(position);
            if(CommonUtility.isNotNull(orderReturned)){
                holder.tvOrderNumber.setText(orderReturned.getInvoiceNumber());
                holder.tvReceivedTime.setText(CommonUtility.formatTimeHHMM(orderReturned.getCreatedAt()));
                holder.tvAmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), orderReturned.getTotaldue()));
                holder.tvStatus.setText(orderReturned.getPaymentStatus());
                if (orderReturned.getPaymentStatus().equalsIgnoreCase(activity.getResources().getString(R.string.paid))) {
                    holder.tvStatus.setTextColor(CommonUtility.getColor(activity, R.color.green_order));
                } else {
                    holder.tvStatus.setTextColor(CommonUtility.getColor(activity, R.color.black));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderReturnedList) ? orderReturnedList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final OrderReturnedRowBinding mBinding;
        private final CustomTextView tvOrderNumber;
        private final CustomTextView tvReceivedTime;
        private final CustomTextView tvAmount;
        private final CustomTextView tvStatus;

        public ProductViewHolder(OrderReturnedRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvOrderNumber=itemView.tvOrderNumber;
            tvReceivedTime=itemView.tvReceivedTime;
            tvAmount=itemView.tvAmount;
            tvStatus=itemView.tvStatus;
        }

    }
}

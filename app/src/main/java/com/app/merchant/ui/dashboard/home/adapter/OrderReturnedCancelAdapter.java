package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderReturnedCancelRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel.OrderReturnedCancel;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReturnedCancelAdapter extends RecyclerView.Adapter<OrderReturnedCancelAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderReturnedCancel> returnList;
    private OrderReturnedCancelListener listener;

    public interface OrderReturnedCancelListener {
        void orderDetailClick(int position);
    }

    public OrderReturnedCancelAdapter(AppCompatActivity activity,OrderReturnedCancelListener listener, ArrayList<OrderReturnedCancel> returnList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.returnList = returnList;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReturnedCancelRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_returned_cancel_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(returnList) && returnList.size() > position) {
            OrderReturnedCancel returnData = returnList.get(position);
            if (CommonUtility.isNotNull(returnData)) {
                holder.tvOrderNumber.setText(returnData.getInvoiceNumber());
                holder.tvReceivedTime.setText(CommonUtility.formatTimeHHMM(returnData.getCreatedAt()));
                holder.tvAmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), returnData.getTotaldue()));
                //holder.tvReason.setText(returnData.getInvoiceNumber());

            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(returnList) ? returnList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderReturnedCancelRowBinding mBinding;
        private final CustomTextView tvOrderNumber;
        private final CustomTextView tvReceivedTime;
        private final CustomTextView tvAmount;
        private final CustomTextView tvReason;

        public ProductViewHolder(OrderReturnedCancelRowBinding itemView) {
            super(itemView.getRoot());
            tvOrderNumber = itemView.tvOrderNumber;
            tvReceivedTime = itemView.tvReceivedTime;
            tvAmount = itemView.tvAmount;
            tvReason = itemView.tvReason;
            mBinding = itemView;
            mBinding.tvOrderNumber.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (CommonUtility.isNotNull(listener)) {
                if (view == mBinding.tvOrderNumber) {
                    listener.orderDetailClick(getAdapterPosition());
                }
            }
        }
    }
}

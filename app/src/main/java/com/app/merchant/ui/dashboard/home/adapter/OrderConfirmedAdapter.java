package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderConfirmedRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmed;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderConfirmedAdapter extends RecyclerView.Adapter<OrderConfirmedAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderConfirmed> orderList;
    private OrderConfirmedListener listener;

    public interface OrderConfirmedListener {
        void onOrderConfirmClick(int position);

        void orderDetailClick(int position);
    }

    public OrderConfirmedAdapter(AppCompatActivity activity, ArrayList<OrderConfirmed> orderList, OrderConfirmedListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.orderList = orderList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderConfirmedRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_confirmed_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(orderList) && orderList.size() > position) {
            OrderConfirmed confirmData = orderList.get(position);
            if (CommonUtility.isNotNull(confirmData)) {
                holder.tvOrderNumber.setText(confirmData.getInvoiceNumber());
                holder.tvAddress.setText(confirmData.getAddress() == null ? activity.getResources().getString(R.string.not_known) : confirmData.getAddress());
                holder.tvAmmount.setText(CommonUtility.setTotalDue(activity.getResources().getString(R.string.rs), confirmData.getTotaldue()));
                holder.tvStatus.setText(confirmData.getPaymentStatus());
                holder.tvStatus.setTextColor(confirmData.getPaymentStatus().equalsIgnoreCase(activity.getResources().getString(R.string.unpaid)) ?
                        CommonUtility.getColor(activity, R.color.black) : CommonUtility.getColor(activity, R.color.green_order));
            }
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderList) ? orderList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderConfirmedRowBinding mBinding;
        private final CustomTextView tvOrderNumber;
        private final CustomTextView tvStatus;
        private final CustomTextView tvAmmount;
        private final CustomTextView tvAddress;

        public ProductViewHolder(OrderConfirmedRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvOrderNumber = mBinding.tvOrderNumber;
            tvAddress = mBinding.tvAddress;
            tvAmmount = mBinding.tvAmmount;
            tvStatus = mBinding.tvStatus;
            itemView.tvStatus.setOnClickListener(this);
            itemView.tvOrderNumber.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (CommonUtility.isNotNull(listener)) {
                if (view == mBinding.tvStatus) {
                    listener.onOrderConfirmClick(getAdapterPosition());
                } else if (view == mBinding.tvOrderNumber) {
                    listener.orderDetailClick(getAdapterPosition());
                }
            }
        }
    }
}

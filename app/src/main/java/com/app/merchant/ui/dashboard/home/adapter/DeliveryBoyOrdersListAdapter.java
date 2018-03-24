package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.DeliveryBoyListRowBinding;
import com.app.merchant.databinding.DeliveryBoyOrderListRowBinding;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrders;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class DeliveryBoyOrdersListAdapter extends RecyclerView.Adapter<DeliveryBoyOrdersListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<DeliveryBoyOrders> orderList;


    public DeliveryBoyOrdersListAdapter(AppCompatActivity activity, ArrayList<DeliveryBoyOrders> orderList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.orderList = orderList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DeliveryBoyOrderListRowBinding itemView = DataBindingUtil.inflate(mInflater, R.layout.delivery_boy_order_list_row, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(orderList) && orderList.size() > position) {
            DeliveryBoyOrders orders=orderList.get(position);
            holder.tvNumberOfOrder.setText(orders.getOrder_amt());
            holder.tvOrderStatus.setText(orders.getCurrent_status());
            holder.tvPaymentStatus.setText(orders.getPayment_status());
            holder.tvInvoiceNumber.setText(orders.getInvoiceNumber());
            holder.tvOrderDate.setText(CommonUtility.formatTimeHHMM(orders.getOrder_date()));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderList) ? orderList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final CustomTextView tvNumberOfOrder;
        private final CustomTextView tvOrderStatus;
        private final CustomTextView tvPaymentStatus;
        private final CustomTextView tvInvoiceNumber;
        private final CustomTextView tvOrderDate;

        public ProductViewHolder(DeliveryBoyOrderListRowBinding itemView) {
            super(itemView.getRoot());
            tvNumberOfOrder = itemView.tvNumberOfOrder;
            tvOrderStatus = itemView.tvOrderStatus;
            tvPaymentStatus = itemView.tvPaymentStatus;
            tvInvoiceNumber = itemView.tvInvoiceNumber;
            tvOrderDate = itemView.tvOrderDate;
        }
    }
}

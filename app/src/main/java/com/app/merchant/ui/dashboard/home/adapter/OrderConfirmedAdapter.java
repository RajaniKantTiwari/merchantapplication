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
           holder.tvOrderNumber.setText(String.valueOf(orderList.get(position).getId()));
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderList) ? orderList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OrderConfirmedRowBinding mBinding;
        private final CustomTextView tvOrderNumber;

        public ProductViewHolder(OrderConfirmedRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvOrderNumber = mBinding.tvOrderNumber;
            itemView.layoutOrderStatus.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onOrderConfirmClick(getAdapterPosition());
        }
    }
}

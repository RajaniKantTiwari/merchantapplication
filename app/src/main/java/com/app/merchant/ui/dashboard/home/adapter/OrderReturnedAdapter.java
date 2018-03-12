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
        if (CommonUtility.isNotNull(orderReturnedList)) {
            holder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderReturnedList) ? orderReturnedList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final OrderReturnedRowBinding mBinding;

        public ProductViewHolder(OrderReturnedRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        public void setData(int position) {
            mBinding.setOrderReturned(orderReturnedList.get(position));
        }
    }
}

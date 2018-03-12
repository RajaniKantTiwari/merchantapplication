package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.OrderReturnedCancelRowBinding;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel.OrderReturnedCancel;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderReturnedCancelAdapter extends RecyclerView.Adapter<OrderReturnedCancelAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<OrderReturnedCancel> returnList;

    public OrderReturnedCancelAdapter(AppCompatActivity activity, ArrayList<OrderReturnedCancel> returnList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.returnList = returnList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReturnedCancelRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.order_returned_cancel_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(returnList) && returnList.size() > position) {
            holder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(returnList) ? returnList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final OrderReturnedCancelRowBinding mBinding;

        public ProductViewHolder(OrderReturnedCancelRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        public void setData(int position) {
            mBinding.setOrderReturned(returnList.get(position));
        }
    }
}

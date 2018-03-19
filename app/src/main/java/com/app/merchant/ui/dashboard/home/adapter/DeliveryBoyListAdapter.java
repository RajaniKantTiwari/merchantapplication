package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.DeliveryBoyListRowBinding;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class DeliveryBoyListAdapter extends RecyclerView.Adapter<DeliveryBoyListAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<DeliveryBoy> orderList;
    private DeliveryBoyListener listener;

    public interface DeliveryBoyListener {
        void onOrderConfirmClick(int position);
    }

    public DeliveryBoyListAdapter(AppCompatActivity activity, ArrayList<DeliveryBoy> orderList, DeliveryBoyListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.orderList = orderList;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DeliveryBoyListRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.delivery_boy_list_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(orderList) ? orderList.size()+5 : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final DeliveryBoyListRowBinding mBinding;
        private final CustomTextView tvOrderNumber;
        private final CustomTextView tvStatus;
        private final CustomTextView tvAmmount;
        private final CustomTextView tvAddress;

        public ProductViewHolder(DeliveryBoyListRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvOrderNumber = mBinding.tvOrderNumber;
            tvAddress = mBinding.tvAddress;
            tvAmmount = mBinding.tvAmmount;
            tvStatus = mBinding.tvStatus;
            itemView.tvStatus.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onOrderConfirmClick(getAdapterPosition());
        }
    }
}

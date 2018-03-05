package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.MyOrderRowBinding;
import com.app.merchant.network.request.dashboard.home.MyOrder;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OrderListener listener;
    private ArrayList<MyOrder> myOrderList;


    public interface OrderListener {
        void onViewClick(int position);
    }

    public MyOrderAdapter(AppCompatActivity activity, OrderListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
    }

    public void setOrderList(ArrayList<MyOrder> myOrderList) {
        this.myOrderList = myOrderList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyOrderRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.my_order_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.setOrderData(position);

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(myOrderList) ? myOrderList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MyOrderRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(MyOrderRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.layoutView.setOnClickListener(this);
        }

        public void setOrderData(int position) {
            if (CommonUtility.isNotNull(myOrderList) && myOrderList.size() > position) {
                mBinding.setMyorder(myOrderList.get(position));
            }
        }


        @Override
        public void onClick(View view) {
            CommonUtility.clicked(mBinding.layoutView);
            listener.onViewClick(getAdapterPosition());
        }
    }
}

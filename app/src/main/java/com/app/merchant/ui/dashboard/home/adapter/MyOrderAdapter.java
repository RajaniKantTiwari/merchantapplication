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
    private final ArrayList<MyOrder> orderList;
    private final AppCompatActivity activity;
    private OrderListener listener;
    public interface OrderListener {
        void onViewClick(int position);
    }

    public MyOrderAdapter(AppCompatActivity activity, ArrayList<MyOrder> orderList, OrderListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity=activity;
        this.orderList=orderList;
        this.listener = listener;
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
        return CommonUtility.isNotNull(orderList) ? orderList.size() : 0;
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
            if (CommonUtility.isNotNull(orderList) && orderList.size() > position) {
                MyOrder myOrder=orderList.get(position);
                mBinding.setMyorder(myOrder);
                mBinding.tvOrderCount.setText(String.valueOf(myOrder.getOrder_count()));
                setColor(myOrder.getOrder_status());
            }
        }

        private void setColor(String order_status) {
            switch (order_status){
                case "Order Received":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.yellow_order));
                    break;
                case "Order Confirmed":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.green_order));
                    break;
                case "Order Out for delivery":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.tex_color));
                    break;
                case "Order Delivered":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.blue_order));
                    break;
                case "Order Canceled":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.not_con_color));
                    break;
                case "Order Returned":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.voilet_order));
                    break;
                case "Assign Delivery Boy":
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.deal_color_product));
                    break;
                default:
                    mBinding.tvOrderCount.setBackgroundColor(CommonUtility.getColor(activity,R.color.deal_color_product));
                    break;
            }
        }


        @Override
        public void onClick(View view) {
            CommonUtility.clicked(mBinding.layoutView);
            listener.onViewClick(getAdapterPosition());
        }
    }
}

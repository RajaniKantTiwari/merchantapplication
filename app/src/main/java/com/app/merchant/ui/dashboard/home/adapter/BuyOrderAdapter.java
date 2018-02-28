package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.BuyProductRowBinding;
import com.app.merchant.databinding.MyOrderRowBinding;
import com.app.merchant.network.request.dashboard.home.MyOrder;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class BuyOrderAdapter extends RecyclerView.Adapter<BuyOrderAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OrderListener listener;
    private ArrayList<MyOrder> myOrderList;


    public interface OrderListener {

        void onBuyClick(int position);
    }
    public BuyOrderAdapter(AppCompatActivity activity, OrderListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }
    public void setOrderList(ArrayList<MyOrder> myOrderList) {
      this.myOrderList=myOrderList;
      notifyDataSetChanged();
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BuyProductRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.buy_product_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final BuyProductRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(BuyProductRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            itemView.tvBuy.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            listener.onBuyClick(getAdapterPosition());
        }
    }
}

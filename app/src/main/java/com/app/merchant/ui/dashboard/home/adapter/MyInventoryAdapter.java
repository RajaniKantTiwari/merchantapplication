package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.MyInventoryRowBinding;
import com.app.merchant.databinding.ProductRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class MyInventoryAdapter extends RecyclerView.Adapter<MyInventoryAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ProductListener listener;
    public interface ProductListener{
        void onItemClick(int position);
    }
    public MyInventoryAdapter(AppCompatActivity activity, ProductListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyInventoryRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.my_inventory_row, parent, false);
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
        private final MyInventoryRowBinding mBinding;
        private ImageView productImage;

        public ProductViewHolder(MyInventoryRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }



        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}

package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.app.merchant.R;
import com.app.merchant.databinding.MyInventoryRowBinding;
import com.app.merchant.network.response.dashboard.AllMerchant;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;
import com.app.merchant.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class MyInventoryAdapter extends RecyclerView.Adapter<MyInventoryAdapter.ProductViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private InventoryListener listener;
    private ArrayList<AllMerchant> myInventoryList;

    public interface InventoryListener {
        void onAddInventoryClick(int position);

        void setInventoryStatus(int status, int position);
    }

    public MyInventoryAdapter(AppCompatActivity activity, InventoryListener listener, ArrayList<AllMerchant> myInventoryList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        this.myInventoryList = myInventoryList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyInventoryRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.my_inventory_row, parent, false);
        return new ProductViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (CommonUtility.isNotNull(myInventoryList) && myInventoryList.size() > position) {
            AllMerchant data = myInventoryList.get(position);
            GlideUtils.loadImage(activity,null,holder.productImage,null,R.drawable.icon_placeholder);
            holder.tvProductName.setText(null);
            holder.tvPrice.setText(data.getProduct_mrp());
            holder.tvQty.setText(null);
            holder.tvTotalQty.setText(null);
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(myInventoryList) ? myInventoryList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MyInventoryRowBinding mBinding;
        private final CustomTextView tvProductName;
        private final CustomTextView tvPrice;
        private final CustomTextView tvQty;
        private final CustomTextView tvTotalQty;
        private ImageView productImage;

        public ProductViewHolder(MyInventoryRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            productImage = itemView.productImage;
            tvProductName = itemView.tvProductName;
            tvPrice = itemView.tvPrice;
            tvQty = itemView.tvQty;
            tvTotalQty = itemView.tvTotalQty;
            itemView.layoutAdd.setOnClickListener(this);
            mBinding.tvOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
                    if (CommonUtility.isNotNull(listener)) {
                        if (flag) {
                            //0-on for stop selling
                            listener.setInventoryStatus(0, getAdapterPosition());
                        } else {
                            listener.setInventoryStatus(0, getAdapterPosition());
                        }
                    }

                }
            });
        }


        @Override
        public void onClick(View view) {
            if(CommonUtility.isNotNull(listener)){
                if(view==mBinding.layoutAdd){
                    listener.onAddInventoryClick(getAdapterPosition());
                }
            }
        }
    }
}

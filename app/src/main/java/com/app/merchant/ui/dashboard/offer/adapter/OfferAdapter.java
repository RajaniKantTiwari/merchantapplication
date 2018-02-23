package com.app.merchant.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.app.merchant.R;
import com.app.merchant.databinding.OfferRowBinding;
import com.app.merchant.network.response.dashboard.offer.Offer;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private final LayoutInflater mInflater;
    private OfferRowBinding mBinding;
    private OfferListener listener;
    private ArrayList<Offer> offersList;
    public void setOffersList(ArrayList<Offer> offersList) {
       this.offersList=offersList;
       notifyDataSetChanged();
    }

    public interface OfferListener {
        void onOfferClicked(int position);
    }

    public OfferAdapter(AppCompatActivity activity, OfferListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.listener = listener;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.offer_row, parent, false);
        return new OfferViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.layoutProduct.setVisibility(View.VISIBLE);
        } else {
            holder.layoutProduct.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(offersList)?offersList.size():0;
    }

    class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout layoutProduct;

        public OfferViewHolder(OfferRowBinding itemView) {
            super(itemView.getRoot());
            layoutProduct = itemView.layoutProduct;
            itemView.layoutOffer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtility.isNotNull(listener)) {
                listener.onOfferClicked(getAdapterPosition());
            }
        }
    }
}

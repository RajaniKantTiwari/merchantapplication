package com.app.merchant.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.PromoOfferRowItemBinding;
import com.app.merchant.widget.CustomTextView;


/**
 * Created by ashok on 25/12/17.
 */

public class PromoOfferAdapter extends RecyclerView.Adapter<PromoOfferAdapter.PromoOfferViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private PromoListener listener;
    public interface PromoListener {
        void onApplyClick(int position);
    }
    public PromoOfferAdapter(AppCompatActivity activity, PromoListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public PromoOfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PromoOfferRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.promo_offer_row_item, parent, false);
        return new PromoOfferViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(PromoOfferViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class PromoOfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final PromoOfferRowItemBinding mBinding;
        private CustomTextView tvApply;

        public PromoOfferViewHolder(PromoOfferRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.tvApply.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            listener.onApplyClick(getAdapterPosition());
        }
    }
}

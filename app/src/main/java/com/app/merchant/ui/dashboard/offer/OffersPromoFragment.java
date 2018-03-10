package com.app.merchant.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOfferPromoBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseFragment;
import com.app.merchant.ui.dashboard.offer.adapter.PromoOfferAdapter;


/**
 * Created by rajnikant on 31/12/17.
 */

public class OffersPromoFragment extends BaseFragment implements PromoOfferAdapter.PromoListener {
    private FragmentOfferPromoBinding mBinding;
    private PromoOfferAdapter mOfferAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_offer_promo,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }
    private void initializeAdapter() {
        mOfferAdapter = new PromoOfferAdapter(getBaseActivity(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvPromo.setLayoutManager(layoutManager);
        mBinding.rvPromo.setAdapter(mOfferAdapter);
    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
     mBinding.tvApply.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return OffersPromoFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
       if(view==mBinding.tvApply){
          getBaseActivity().onBackPressed();
       }
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    public static Fragment newInstance() {
        OffersPromoFragment fragment = new OffersPromoFragment();
        return fragment;
    }

    @Override
    public void onApplyClick(int position) {
        getBaseActivity().onBackPressed();
    }
}

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
import com.app.merchant.databinding.FragmentOfferBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.offer.Offer;
import com.app.merchant.network.response.dashboard.offer.OfferType;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.offer.adapter.OfferAdapter;
import com.app.merchant.ui.dashboard.offer.adapter.OfferTypesAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class OfferFragment extends DashboardFragment implements
        OfferTypesAdapter.OfferTypeListener, OfferAdapter.OfferListener {

    @Inject
    CommonPresenter presenter;
    private FragmentOfferBinding mBinding;
    private OfferAdapter mOfferAdapter;
    private OfferTypesAdapter mOfferTypeAdapter;
    private ArrayList<OfferType> offerTypeList;
    private ArrayList<Offer> offersList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offer, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.offer));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        mOfferAdapter = new OfferAdapter(getDashboardActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOffer.setLayoutManager(layoutManager);
        mBinding.rvOffer.setAdapter(mOfferAdapter);
        offersList = new ArrayList<>();
        setOffers();


        offerTypeList = new ArrayList<>();
        setOfferList(offerTypeList);
        mOfferTypeAdapter = new OfferTypesAdapter(getDashboardActivity(), offerTypeList, this);
        LinearLayoutManager offerTypeManager = new LinearLayoutManager(getDashboardActivity());
        offerTypeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvOfferType.setLayoutManager(offerTypeManager);
        mBinding.rvOfferType.setAdapter(mOfferTypeAdapter);
    }

    private void setOffers() {
        Offer offer1 = new Offer();
        offersList.add(offer1);
        Offer offer2 = new Offer();
        offersList.add(offer2);
        Offer offer3 = new Offer();
        offersList.add(offer3);
        Offer offer4 = new Offer();
        offersList.add(offer4);
        Offer offer5 = new Offer();
        offersList.add(offer5);
        Offer offer6 = new Offer();
        offersList.add(offer6);
        mOfferAdapter.setOffersList(offersList);
    }

    private void setOfferList(ArrayList<OfferType> offerTypeList) {
        OfferType type1 = new OfferType();
        type1.setSelected(true);
        type1.setOfferType(getString(R.string.fab_deal));
        OfferType type2 = new OfferType();
        type2.setOfferType(getString(R.string.food_drinks));
        OfferType type3 = new OfferType();
        type3.setOfferType(getString(R.string.spas));
        OfferType type4 = new OfferType();
        type4.setOfferType(getString(R.string.saloons));
        offerTypeList.add(type1);
        offerTypeList.add(type2);
        offerTypeList.add(type3);
        offerTypeList.add(type4);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return OfferFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
        } else {
            CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOfferTypeItemClicked(int position) {
        for (int i = 0; i < offerTypeList.size(); i++) {
            OfferType type = offerTypeList.get(i);
            if (i == position) {
                type.setSelected(true);
            } else {
                type.setSelected(false);
            }
            offerTypeList.set(i, type);
        }
        mOfferTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOfferClicked(int position) {
        if (CommonUtility.isNotNull(offersList) && offersList.size() > position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.OFFER, offersList.get(position));
            //ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), OfferDetailsActivity.class, bundle);
        }
    }
}

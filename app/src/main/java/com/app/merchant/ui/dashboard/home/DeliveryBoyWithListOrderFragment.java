package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentShowDeliveryBoyBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrder;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrderData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.DeliveryBoyListAdapter;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReceivedFragment;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class DeliveryBoyWithListOrderFragment extends DashboardFragment implements DeliveryBoyListAdapter.DeliveryBoyListener {
    private DeliveryBoyListAdapter mAdapter;
    private FragmentShowDeliveryBoyBinding mBinding;
    private ArrayList<DeliveryBoyOrder> deliveryBoyList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_delivery_boy, container, false);
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.delivery_boys));
        initializeOrderData();
        return mBinding.getRoot();
    }

    private void initializeOrderData() {
        deliveryBoyList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvDeliveryBoy.setLayoutManager(layoutManager);
        mAdapter = new DeliveryBoyListAdapter(getDashboardActivity(), deliveryBoyList, this);
        mBinding.rvDeliveryBoy.setAdapter(mAdapter);
        getPresenter().getCountOrderPerDeliveryBoy(getDashboardActivity());
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {


    }

    @Override
    public String getFragmentName() {
        return DeliveryBoyWithListOrderFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 3) {
                DeliveryBoyOrderData data = (DeliveryBoyOrderData) response;
                setData(data);
            }
        }

    }

    private void setData(DeliveryBoyOrderData data) {
        deliveryBoyList.clear();
        deliveryBoyList.addAll(data.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void headerChangedCalled() {

    }

    @Override
    public void onDeliveryBoyOrderClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleConstants.DELIVERY_BOY_ID, deliveryBoyList.get(position).getDeliveryboy_id());
        bundle.putString(BundleConstants.DELIVERY_BOY_NAME, deliveryBoyList.get(position).getDeliveryboy());
        getDashboardActivity().addFragmentInContainer(new DeliveryBoyOrdersDetailsFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }
}

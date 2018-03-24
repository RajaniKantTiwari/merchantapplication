package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentDeliveryBoyOrdersDetailsBinding;
import com.app.merchant.network.request.dashboard.home.DeliveryBoyOrderDetailRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrders;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrdersData;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.DeliveryBoyOrdersListAdapter;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class DeliveryBoyOrdersDetailsFragment extends DashboardFragment {
    private DeliveryBoyOrdersListAdapter mAdapter;
    private FragmentDeliveryBoyOrdersDetailsBinding mBinding;
    private ArrayList<DeliveryBoyOrders> deliveryBoyList;
    private String deliveryBoyId;
    private String deliveryBoyName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_boy_orders_details, container, false);
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.delivery_boys));
        initializeOrderData();
        return mBinding.getRoot();
    }

    private void initializeOrderData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            deliveryBoyId = bundle.getString(BundleConstants.DELIVERY_BOY_ID);
            deliveryBoyName = bundle.getString(BundleConstants.DELIVERY_BOY_NAME);
        }
        deliveryBoyList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvDeliveryBoy.setLayoutManager(layoutManager);
        mAdapter = new DeliveryBoyOrdersListAdapter(getDashboardActivity(), deliveryBoyList);
        mBinding.rvDeliveryBoy.setAdapter(mAdapter);
        getPresenter().getDeliveryBoyOrderDetail(getDashboardActivity(), new DeliveryBoyOrderDetailRequest(deliveryBoyId));
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {
        mBinding.tvName.setText(deliveryBoyName);

    }

    @Override
    public String getFragmentName() {
        return DeliveryBoyOrdersDetailsFragment.class.getSimpleName();
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
            DeliveryBoyOrdersData data = (DeliveryBoyOrdersData) response;
            setData(data);
        }
    }

    private void setData(DeliveryBoyOrdersData data) {
        deliveryBoyList.clear();
        if (CommonUtility.isNotNull(data.getData())) {
            deliveryBoyList.addAll(data.getData());
        }
    }

    @Override
    public void headerChangedCalled() {

    }

}

package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderBinding;
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.network.request.dashboard.home.MyOrder;
import com.app.merchant.network.request.dashboard.home.MyOrderData;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyOrderAdapter;
import com.app.merchant.ui.dashboard.home.graphfragment.AssignDeliveryBoyFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderConfirmedFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderDeliveredFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderOutForDeliveryFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReceivedFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReturnedCancelFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReturnedFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by ashok on 13/11/17.
 */

public class MyOrderFragment extends DashboardFragment implements MyOrderAdapter.OrderListener {

    private FragmentOrderBinding mBinding;
    private MyOrderAdapter myOrderAdapter;
    private ArrayList<MyOrder> orderList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        CommonUtility.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager notificationManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOrder.setLayoutManager(notificationManager);
        orderList = new ArrayList<>();
        myOrderAdapter = new MyOrderAdapter(getDashboardActivity(),orderList, this);
        mBinding.rvOrder.setAdapter(myOrderAdapter);
        getPresenter().getAllOrder(getDashboardActivity());
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return MyOrderFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.ALL_ORDER) {
                MyOrderData data = (MyOrderData) response;
                if (CommonUtility.isNotNull(data)) {
                    orderList.clear();
                    orderList.addAll(data.getData());
                    myOrderAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Subscribe
    public void onMessageEvent(InventoryEvent event) {
        if (event.getOrderInventory() == AppConstants.MY_ORDER) {
            mBinding.layoutOrder.setVisibility(View.VISIBLE);
        } else {
            mBinding.layoutOrder.setVisibility(View.GONE);

        }
    }

    @Override
    public void onViewClick(int position) {
        if(CommonUtility.isNotNull(orderList)&&orderList.size()>position){
            if (orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_received))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderReceivedFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if (orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_confirmed))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderConfirmedFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if (orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_out_for_delivery))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderOutForDeliveryFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if (orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_delivered))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderDeliveredFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if (orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_canceled))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderReturnedCancelFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if(orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.order_returned))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderReturnedFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            } else if(orderList.get(position).getOrder_status().equalsIgnoreCase(getResources().getString(R.string.assign_delivery_boy))) {
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new AssignDeliveryBoyFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }

    @Override
    public void headerChangedCalled() {

    }
}

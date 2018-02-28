package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentNotificationBinding;
import com.app.merchant.databinding.FragmentOrderBinding;
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.network.request.dashboard.home.MyOrder;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyOrderAdapter;
import com.app.merchant.ui.dashboard.notification.NotificationAdapter;
import com.app.merchant.utility.AppConstants;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class MyOrderFragment extends DashboardFragment implements MyOrderAdapter.OrderListener {

    private FragmentOrderBinding mBinding;
    private MyOrderAdapter myOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.notification));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager notificationManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOrder.setLayoutManager(notificationManager);
        myOrderAdapter = new MyOrderAdapter(getDashboardActivity(),this);
        mBinding.rvOrder.setAdapter(myOrderAdapter);
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

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
    @Subscribe
    public void onMessageEvent(InventoryEvent event) {
        if (event.getOrderInventory() == AppConstants.MY_INVENTORY) {
            mBinding.layoutOrder.setVisibility(View.VISIBLE);
            myOrderAdapter.setOrderList(event.getMyOrderList());
        } else{
            mBinding.layoutOrder.setVisibility(View.GONE);

        }
    }

    @Override
    public void onViewClick(int position) {

    }
}

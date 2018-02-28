package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentInventoryBinding;
import com.app.merchant.databinding.FragmentNotificationBinding;
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.network.request.dashboard.home.MyInventory;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyInventoryAdapter;
import com.app.merchant.ui.dashboard.notification.NotificationAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class MyInventoryFragment extends DashboardFragment implements MyInventoryAdapter.InventoryListener {

    private FragmentInventoryBinding mBinding;
    private MyInventoryAdapter myInventoryAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_inventory, container, false);
        CommonUtility.register(this);
        getDashboardActivity().setHeaderTitle(getString(R.string.notification));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvInventory.setLayoutManager(layoutManager);
        myInventoryAdapter = new MyInventoryAdapter(getDashboardActivity(),this);
        mBinding.rvInventory.setAdapter(myInventoryAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return MyInventoryFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    @Subscribe
    public void onMessageEvent(InventoryEvent event) {
        if (event.getOrderInventory() == AppConstants.MY_INVENTORY) {
            mBinding.layoutInventory.setVisibility(View.VISIBLE);
            myInventoryAdapter.setList(event.getMyInventoryList());
        } else{
            mBinding.layoutInventory.setVisibility(View.GONE);

        }
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
}

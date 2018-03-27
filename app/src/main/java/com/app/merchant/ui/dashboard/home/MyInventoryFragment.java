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
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.network.request.dashboard.cart.InventoryStatusRequest;
import com.app.merchant.network.request.dashboard.cart.UpdateMyInventoryRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyInventoryAdapter;
import com.app.merchant.ui.scanner.ScannerActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;

import org.greenrobot.eventbus.Subscribe;


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
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvInventory.setLayoutManager(layoutManager);
        myInventoryAdapter = new MyInventoryAdapter(getDashboardActivity(), this);
        mBinding.rvInventory.setAdapter(myInventoryAdapter);
        getPresenter().myInventoryHistory(getDashboardActivity());
       /* getPresenter().getAllMerchantProduct(getDashboardActivity());
        getPresenter().getAllMerchantRunningProduct(getDashboardActivity());

        UpdateMyInventoryRequest request=new UpdateMyInventoryRequest();
        getPresenter().updateMyInventory(getDashboardActivity(),request);*/


    }

    @Override
    public void setListener() {
        mBinding.ivAddProduct.setOnClickListener(this);
        mBinding.ivScan.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return MyInventoryFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
       getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Subscribe
    public void onMessageEvent(InventoryEvent event) {
        if (event.getOrderInventory() == AppConstants.MY_INVENTORY) {
            mBinding.layoutInventory.setVisibility(View.VISIBLE);
            myInventoryAdapter.setList(event.getMyInventoryList());
        } else {
            mBinding.layoutInventory.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivAddProduct) {

        } else if (view == mBinding.ivScan) {
            ExplicitIntent.getsInstance().navigateForResult(getDashboardActivity(), ScannerActivity.class,AppConstants.SCANNER_RESULT);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void setInventoryStatus(int status, int position) {
        InventoryStatusRequest statusRequest=new InventoryStatusRequest();
        statusRequest.setStatus(String.valueOf(status));
        getPresenter().updateMyInventoryStatus(getDashboardActivity(),statusRequest);
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

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
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyOrderAdapter;
import com.app.merchant.ui.dialogfrag.ConfirmOrderDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by ashok on 13/11/17.
 */

public class MyOrderFragment extends DashboardFragment implements MyOrderAdapter.OrderListener,
        ConfirmOrderDialogFragment.OrderDialogListener{

    private FragmentOrderBinding mBinding;
    private MyOrderAdapter myOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        CommonUtility.register(this);

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
        if (event.getOrderInventory() == AppConstants.MY_ORDER) {
            mBinding.layoutOrder.setVisibility(View.VISIBLE);
            myOrderAdapter.setOrderList(event.getMyOrderList());
        } else{
            mBinding.layoutOrder.setVisibility(View.GONE);

        }
    }

    @Override
    public void onViewClick(int position) {
        Bundle bundle=new Bundle();
         CommonUtility.showConfirmOrderDialog(getDashboardActivity(),bundle,this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }

    @Override
    public void confirmed() {

    }

    @Override
    public void notConfirmed() {

    }
}

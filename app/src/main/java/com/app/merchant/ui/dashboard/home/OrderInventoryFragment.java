package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderInventoryBinding;
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.event.PerformanceInventoryEvent;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class OrderInventoryFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentOrderInventoryBinding mBinding;
    private InventoryEvent event;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_inventory, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.welcome));
        CommonUtility.register(this);
        addFragment();
        return mBinding.getRoot();
    }

    private void addFragment() {
        event = new InventoryEvent();
        event.setOrderInventory(AppConstants.MY_ORDER);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.MY_INVENYORY_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.MY_ORDER_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void initializeData() {

    }


    @Override
    public void setListener() {
        mBinding.layoutOrder.setOnClickListener(this);
        mBinding.layoutInventory.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return OrderInventoryFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutOrder) {
            CommonUtility.clicked(mBinding.layoutOrder);
            openOrder();
        } else if (view == mBinding.layoutInventory) {
            CommonUtility.clicked(mBinding.layoutInventory);
            openInventory();
        }
    }

    private void openInventory() {
        //mBinding.tvInventory.setText(getResources().getString(R.string.my_inventory));
        mBinding.tvInventory.setTextColor(CommonUtility.getColor(getDashboardActivity(),R.color.date_bg_color));
        mBinding.tvMyOrder.setTextColor(CommonUtility.getColor(getDashboardActivity(),R.color.black));
        event.setOrderInventory(AppConstants.MY_INVENTORY);
        EventBus.getDefault().post(event);
    }

    private void openOrder() {
        //mBinding.tvMyOrder.setText(getResources().getString(R.string.my_order));
        mBinding.tvMyOrder.setTextColor(CommonUtility.getColor(getDashboardActivity(),R.color.date_bg_color));
        mBinding.tvInventory.setTextColor(CommonUtility.getColor(getDashboardActivity(),R.color.black));
        event.setOrderInventory(AppConstants.MY_ORDER);
        EventBus.getDefault().post(event);
    }

    @Override
    public void headerChangedCalled() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.welcome));
    }
    @Subscribe
    public void onEvent(PerformanceInventoryEvent event) {
        if (event.getProductInventory() == AppConstants.PRODUCT_INVENTORY) {
            mBinding.layoutProductInventory.setVisibility(View.VISIBLE);
        } else {
            mBinding.layoutProductInventory.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
}

package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.FragmentWelcomeBinding;
import com.app.merchant.event.PerformanceInventoryEvent;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Amul on 13/11/17.
 */
public class WelcomeFragment extends DashboardFragment {
    private PerformanceInventoryEvent event;
    FragmentWelcomeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        addFragment();
        return mBinding.getRoot();
    }

    private void addFragment() {
        mBinding.graphButton.setTag(R.drawable.histo_graph);
        mBinding.graphButton.setVisibility(View.VISIBLE);
        event = new PerformanceInventoryEvent();
        event.setProductInventory(AppConstants.PRODUCT_INVENTORY);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.ALL_PERFORMANCE,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), AppConstants.FRAGMENTS.PRODUCT_INVENTORY_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
    }


    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.graphButton.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return WelcomeFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        if (view == mBinding.graphButton) {
            if ((int) mBinding.graphButton.getTag() == R.drawable.bar_graph) {
                mBinding.graphButton.setImageResource(R.drawable.histo_graph);
                mBinding.graphButton.setTag(R.drawable.histo_graph);
            } else {
                mBinding.graphButton.setImageResource(R.drawable.bar_graph);
                mBinding.graphButton.setTag(R.drawable.bar_graph);
            }
            CommonUtility.clicked(mBinding.graphButton);
            listMapConversion();
        }
    }


    private void listMapConversion() {
        if (event.getProductInventory() == AppConstants.PRODUCT_INVENTORY) {
            event.setProductInventory(AppConstants.ALL_PERFORMANCE);
        } else {
            event.setProductInventory(AppConstants.PRODUCT_INVENTORY);
        }
        EventBus.getDefault().post(event);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void headerChangedCalled() {

    }
}

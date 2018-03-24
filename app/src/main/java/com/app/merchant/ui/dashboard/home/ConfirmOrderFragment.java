package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentConfirmOrderBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.cart.ProductSubproductFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.CustomCountDownTimer;

import static com.app.merchant.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.WELCOME_HOME_FRAGMENT;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ConfirmOrderFragment extends DashboardFragment {

    private FragmentConfirmOrderBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_order, container, false);
        DeliveryTimer deliveryTimer = new DeliveryTimer(1330*60 * 1000, AppConstants.COUNT_INTERVAL);
        mBinding.tvRemaining.setText(CommonUtility.convertSecondsToHMmSs(30*60));
        deliveryTimer.start();
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {
        mBinding.tvRaiseAnIssue.setOnClickListener(this);
        mBinding.tvHome.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return ConfirmOrderFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvRaiseAnIssue) {
            CommonUtility.clicked(mBinding.tvRaiseAnIssue);
            getDashboardActivity().addFragmentInContainer(new HelpsAndSupportFragment(),null,true,true, NONE);
            //mFragmentNavigation.pushFragment(HelpsAndSupportFragment.newInstance(mInt + 1));
        } else if (view == mBinding.tvHome) {
            CommonUtility.clicked(mBinding.tvHome);
            getBaseActivity().clearAllBackStack();
            getDashboardActivity().changeIcon(WELCOME_HOME_FRAGMENT);
            getDashboardActivity().addFragmentInContainer(new ProductSubproductFragment(), null, true, false, NONE);
           // mFragmentNavigation.popFragment();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void headerChangedCalled() {

    }

    /**
     * Countdown timer for meditation show time left to finish meditation
     */
    private class DeliveryTimer extends CustomCountDownTimer {

        public DeliveryTimer(long millisInFuture, long countDownInterval) {
            super(getDashboardActivity(), millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String time=CommonUtility.convertSecondsToHMmSs(millisUntilFinished/1000);
            mBinding.tvRemaining.setText(time);
        }

        @Override
        public void onFinish() {

        }

    }
}

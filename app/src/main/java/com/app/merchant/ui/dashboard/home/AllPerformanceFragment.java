package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAllPerformanceBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashok on 13/11/17.
 */

public class AllPerformanceFragment extends DashboardFragment {
    private FragmentAllPerformanceBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_performance, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.all_performance));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        receivedChartData();

    }

    private void receivedChartData() {
        initializeDeliveredChart();
        initializeReceivedChart();
    }

    private void initializeDeliveredChart() {

    }

    private void initializeReceivedChart() {


    }



    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return AllPerformanceFragment.class.getSimpleName();
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

}

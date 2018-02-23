package com.app.merchant.ui.dashboard.drawer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentHomeBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.notification.NotificationAdapter;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class WelcomeHomeFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private NotificationAdapter mNotificationAdapter;
    private FragmentHomeBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.home));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return WelcomeHomeFragment.class.getSimpleName();
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

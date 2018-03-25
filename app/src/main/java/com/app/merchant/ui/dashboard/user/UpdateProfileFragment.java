package com.app.merchant.ui.dashboard.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentUpdateProfileBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashboardFragment;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class UpdateProfileFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentUpdateProfileBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.update_profile));
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
        return UpdateProfileFragment.class.getSimpleName();
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

    @Override
    public void headerChangedCalled() {

    }
}

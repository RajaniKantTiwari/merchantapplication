package com.app.merchant.ui.dashboard.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentUserProfileBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class UserProfileFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentUserProfileBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.profile));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.ivEdit.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return UserProfileFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivEdit) {
            Bundle bundle = new Bundle();
            getDashboardActivity().addFragmentInContainer(new UpdateProfileFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
        }

    }

    @Override
    public void headerChangedCalled() {

    }
}

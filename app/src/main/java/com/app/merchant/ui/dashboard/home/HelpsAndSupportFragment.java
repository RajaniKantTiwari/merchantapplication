package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentHelpsupportBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.SimpleDividerItemDecoration;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.HelpSupportAdapter;
import com.app.merchant.ui.dialogfrag.RatingDialogFragment;
import com.app.merchant.utility.CommonUtility;


/**
 * Created by rajnikant on 31/12/17.
 */

public class HelpsAndSupportFragment extends DashboardFragment implements HelpSupportAdapter.HelpSupportListener,RatingDialogFragment.RatingDialogListener {
    private FragmentHelpsupportBinding mBinding;
    private HelpSupportAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_helpsupport,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mAdapter=new HelpSupportAdapter(getBaseActivity(),this);
        mBinding.rvChoice.setLayoutManager(layoutManager);
        mBinding.rvChoice.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvChoice.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return HelpsAndSupportFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Override
    public void itemClicked(int adapterPosition, boolean isChecked) {
        if(isChecked){
            CommonUtility.showRatingDialog(getDashboardActivity(),null,this);
        }
    }

    @Override
    public void submit(int id, float rating, String submit) {

    }
}

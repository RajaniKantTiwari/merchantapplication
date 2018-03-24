package com.app.merchant.ui.dashboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityCustomerDetailBinding;
import com.app.merchant.event.UserEvent;
import com.app.merchant.network.request.CustomerRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.CustomerDetail;
import com.app.merchant.network.response.CustomerDetailData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.authentication.CommonActivity;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by rajnikant on 31/12/17.
 */

public class CustomerDetailActivity extends CommonActivity {
    private ActivityCustomerDetailBinding mBinding;
    private Intent intent;
    private String customerId;
    @Inject
    CommonPresenter presenter;
    private CustomerDetail commonDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_detail);
        initializeData();
        setListener();
    }

    public void initializeData() {
        intent = getIntent();
        if (CommonUtility.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtility.isNotNull(bundle)) {
                customerId = bundle.getString(BundleConstants.CUSTOMER_ID);
                presenter.getCustomerDetails(this, new CustomerRequest(customerId));
            }
        }

        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.customer_details));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
    }

    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvDone) {
            CommonUtility.clicked(mBinding.tvDone);
            UserEvent userDetailEvent = new UserEvent(commonDetail.getMobile());
            EventBus.getDefault().post(userDetailEvent);
            finish();
        } else if (view == mBinding.layoutHeader.ivBack) {
            onBackPressed();
        }

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
                CustomerDetailData data = (CustomerDetailData) response;
                ArrayList<CustomerDetail> detailsList = data.getData();
                if (CommonUtility.isNotNull(detailsList) && detailsList.size() > 0) {
                    commonDetail = detailsList.get(0);
                    setData(commonDetail);
                }
            }
        }
    }

    private void setData(CustomerDetail commonDetail) {
        mBinding.tvUserName.setText(commonDetail.getUsername());
        mBinding.tvEmailId.setText(commonDetail.getEmail());
        mBinding.tvMobileNumber.setText(commonDetail.getMobile());
        mBinding.tvAddress.setText(commonDetail.getAddress());
        mBinding.tvCity.setText(commonDetail.getCity());
        mBinding.tvPinCode.setText(commonDetail.getPincode());
        mBinding.tvGender.setText(commonDetail.getGender());
        mBinding.tvFacebookId.setText(commonDetail.getFacebook_id());
        mBinding.tvLinkedInId.setText(commonDetail.getLinkedin_id());
    }
}

package com.app.merchant.ui.dashboard.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAddNewCustomerBinding;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.authentication.LoginActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.CommonUtility;

import static com.app.merchant.utility.CommonUtility.isNull;


/**
 * Created by rajnikant on 31/12/17.
 */

public class AddNewCustomerFragment extends DashboardFragment {
    private FragmentAddNewCustomerBinding mBinding;
    private String email;
    private String mobileNumber;
    private String address;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_customer, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
        mBinding.edAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (isValid()) {
                            getDashboardActivity().onBackPressed();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public String getFragmentName() {
        return AddNewCustomerFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvDone) {
            CommonUtility.clicked(mBinding.tvDone);
            if(isValid()){
                  getDashboardActivity().onBackPressed();
            }
        }

    }
    private boolean isValid() {
        email = mBinding.edEmail.getText().toString();
        mobileNumber = mBinding.edMobileNumber.getText().toString();
        address = mBinding.edAddress.getText().toString();
        if (isNull(email) || email.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_email_address));
            mBinding.edEmail.requestFocus();
            return false;
        } else if (!CommonUtility.checkValidEmail(email)) {
            mBinding.edEmail.requestFocus();
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        } else if (isNull(mobileNumber) || mobileNumber.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_mobile_number));
            mBinding.edMobileNumber.requestFocus();
            return false;
        } else if (isNull(mobileNumber) || mobileNumber.trim().length() < 10) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_valid_mobilenumber));
            mBinding.edMobileNumber.requestFocus();
            return false;
        }else if (isNull(address) || address.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_address));
            mBinding.edAddress.requestFocus();
            return false;
        }
          return true;

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void headerChangedCalled() {

    }
}

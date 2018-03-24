package com.app.merchant.ui.dashboard.cart;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAddNewCustomerBinding;
import com.app.merchant.event.UserEvent;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.dashboard.home.NewCustomerRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.authentication.LoginActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.PreferenceUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import static com.app.merchant.utility.CommonUtility.isNull;


/**
 * Created by rajnikant on 31/12/17.
 */

public class AddNewCustomerFragment extends DashboardFragment {
    private FragmentAddNewCustomerBinding mBinding;
    private String email;
    private String mobileNumber;
    private String address;
    private String name;
    private double latitude;
    private double longitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDashboardActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_customer, container, false);
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.add_new_customer));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
        mBinding.tvAddress.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return AddNewCustomerFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            CommonUtility.clicked(mBinding.tvSubmit);
            if (isValid()) {
                NewCustomerRequest request = new NewCustomerRequest();
                setData(request);
                getPresenter().addNewCustomer(request, getDashboardActivity());
            }
        } else if (view == mBinding.tvAddress) {
            CommonUtility.clicked(mBinding.tvAddress);
            if (isNetworkConnected()) {
                address();
            }
        }

    }

    private void setData(NewCustomerRequest request) {
        request.setName(name);
        request.setEmail(email);
        request.setPhoneno(mobileNumber);
        request.setAddress(address);
        request.setLat(latitude);
        request.setLng(longitude);
    }

    private void address() {
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(getDashboardActivity());
            startActivityForResult(intent, AppConstants.SELECT_MANUALLY);
        } catch (GooglePlayServicesRepairableException e) {
        } catch (GooglePlayServicesNotAvailableException e) {
        }
    }

    private boolean isValid() {
        name = mBinding.edName.getText().toString();
        email = mBinding.edEmail.getText().toString();
        mobileNumber = mBinding.edMobileNumber.getText().toString();
        address = mBinding.tvAddress.getText().toString();
        if (isNull(name) || name.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_name));
            mBinding.edName.requestFocus();
            return false;
        } else if (isNull(email) || email.trim().length() == 0) {
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
        } else if (isNull(address) || address.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_address));
            return false;
        }
        return true;

    }

    @Override
    public void onPause() {
        super.onPause();
        getDashboardActivity().getWindow().
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if(requestCode==1){
                getDashboardActivity().showToast(response.getMsg());
                UserEvent userDetailEvent = new UserEvent(mobileNumber);
                EventBus.getDefault().post(userDetailEvent);
                getDashboardActivity().onBackPressed();
            }

        }
    }

    @Override
    public void headerChangedCalled() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.SELECT_MANUALLY) {
            try {
                if (CommonUtility.isNotNull(data)) {
                    // retrive the data by using getPlace() method.
                    Place place = PlaceAutocomplete.getPlace(getDashboardActivity(), data);
                    if (CommonUtility.isNotNull(place)) {
                        LatLng latLng = place.getLatLng();
                        PreferenceUtils.setAddress(place.getAddress().toString());
                        PreferenceUtils.setLatitude(latLng.latitude);
                        PreferenceUtils.setLongitude(latLng.longitude);
                        latitude = latLng.latitude;
                        longitude = latLng.longitude;
                        address = PreferenceUtils.getAddress(getDashboardActivity(), PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude());
                        PreferenceUtils.setAddress(address);
                        mBinding.tvAddress.setText(address);

                    }

                }
            } catch (Exception ex) {
                getDashboardActivity().showToast(getResources().getString(R.string.something_went_wrong));
            }
        }
    }
}

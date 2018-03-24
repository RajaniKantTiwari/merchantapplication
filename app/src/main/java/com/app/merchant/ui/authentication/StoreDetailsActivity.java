package com.app.merchant.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityStoreDetailsBinding;
import com.app.merchant.event.EncodedBitmap;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.RegisterResponseData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.dialogfrag.OkDialogFragment;
import com.app.merchant.ui.uploadfile.UploadImage;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StoreDetailsActivity extends CommonActivity implements MvpView, View.OnClickListener,DatePickerDialog.OnDateSetListener {
    private static final String TAG = StoreDetailsActivity.class.getSimpleName();
    ActivityStoreDetailsBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String legalName;
    private String panNumber;
    private String gstNumber;
    private String bankName;
    private String branchName;
    private String accountNumber;
    private String ifscCode;
    private String deliveryCharge;
    private String serviceArea;
    private RegisterRequest register;
    private int numberOfStoreAreas;
    private List<View> viewList = new ArrayList<>();
    private String adharNumber;
    private String nationality;
    private String dob;
    private String opentime;
    private String closetime;
    private String minorder;
    private String avgtime;
    private String coi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_store_details);
        CommonUtility.register(this);
        initilizeData();
        setListener();
    }

    private void initilizeData() {
        Intent intent = getIntent();
        if (CommonUtility.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtility.isNotNull(bundle)) {
                register = bundle.getParcelable(BundleConstants.REGISTER_USER);
                try {
                    storeImage(AppConstants.STORE_IMAGE, register.getStoreList().get(0).getImageUrl());
                    storeImage(AppConstants.FACULTY_IMAGE, register.getStoreList().get(1).getImageUrl());
                    storeImage(AppConstants.OWNER_IMAGE, register.getStoreList().get(2).getImageUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
        mBinding.layoutPayment.layoutCash.setOnClickListener(this);
        mBinding.layoutPayment.layoutCard.setOnClickListener(this);
        mBinding.layoutPayment.layoutPaytm.setOnClickListener(this);
        mBinding.layoutPayment.layoutOther.setOnClickListener(this);
        mBinding.tvAddMoreAreas.setOnClickListener(this);
        mBinding.tvDateOfBirth.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (isNotNull(response)) {
                if (response.getStatus().equals(AppConstants.SUCCESS)) {
                    RegisterResponseData data=(RegisterResponseData)response;
                    Bundle bundle = new Bundle();
                    bundle.putString(BundleConstants.MOBILE_NUMBER,register.getMobile());
                    bundle.putString(BundleConstants.EMAIL,register.getEmail());
                    PreferenceUtils.setUserName(register.getMerchantname());
                    PreferenceUtils.setMerchantId(data.getId());
                    ExplicitIntent.getsInstance().navigateTo(this, VerifyAccountActivity.class, bundle);
                }
        }
    }



    @Override
    public void onClick(View view) {
        if (view == mBinding.tvDone) {
            CommonUtility.clicked(mBinding.tvDone);
            if (isValid()) {
                setRegisterData();
                if (isNetworkConnected()) {
                    presenter.registerMerchant(this, register);
                }
            }
        } else if (view == mBinding.layoutPayment.layoutCash) {
            selectCash();
        } else if (view == mBinding.layoutPayment.layoutCard) {
            selectCard();
        } else if (view == mBinding.layoutPayment.layoutPaytm) {
            selectPaytm();
        } else if (view == mBinding.layoutPayment.layoutOther) {
            selectOther();
        } else if (view == mBinding.tvAddMoreAreas) {
            addChildView(numberOfStoreAreas);
        }else if (mBinding.tvDateOfBirth == view) {
            CommonUtility.openDatePicker(this);
        }
    }

    /**
     * Here User Can Add Payment if needed
     *
     * @param number
     */
    private void addChildView(int number) {
        View child = getLayoutInflater().inflate(R.layout.add_more_service_row, null);
        viewList.add(child);
        numberOfStoreAreas=numberOfStoreAreas+1;
        child.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        child.setTag(number);
        child.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.layoutMoreService.removeView(child);
                viewList.remove(numberOfStoreAreas);
                numberOfStoreAreas = numberOfStoreAreas - 1;
            }
        });
        mBinding.layoutMoreService.addView(child);
    }

    private void setRegisterData() {
        if (CommonUtility.isNotNull(register)) {
            register.setCompany_legal_name(legalName);
            register.setCoi(coi);
            register.setPancard(panNumber);
            register.setGst(gstNumber);
            register.setBankname(bankName);
            register.setBranch(branchName);
            register.setAccountno(accountNumber);
            register.setIfsc(ifscCode);
            register.setAdhar(adharNumber);
            register.setNationality(nationality);
            register.setDob(dob);
            register.setOpentime(opentime);
            register.setClosetime(closetime);
            register.setMinorder(minorder);
            register.setAvgtime(avgtime);
            setPaymentOption();
            register.setDelivery(deliveryCharge);
            register.setServicingarea(serviceArea);
            if (CommonUtility.isNotNull(viewList) && viewList.size() > 0) {
                ArrayList<String> otherServiceArea = new ArrayList<>();
                for (int i = 0; i < viewList.size(); i++) {
                    EditText edServiceArea = viewList.get(i).findViewById(R.id.edServiceArea);
                    otherServiceArea.add(edServiceArea.getText().toString());
                }
                register.setOtherServiceArea(otherServiceArea);
            }
        }
    }

    private void setPaymentOption() {
        if (mBinding.layoutPayment.radioCash.isChecked()) {
            register.setPaymentmode(getResources().getString(R.string.optioncash));
        } else if (mBinding.layoutPayment.radioCard.isChecked()) {
            register.setPaymentmode(getResources().getString(R.string.optioncard));
        } else if (mBinding.layoutPayment.radioPaytm.isChecked()) {
            register.setPaymentmode(getResources().getString(R.string.optionpaytm));
        } else if (mBinding.layoutPayment.radioOther.isChecked()) {
            register.setPaymentmode(getResources().getString(R.string.optionother));
        }
    }

    private void selectOther() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(true);
    }

    private void selectPaytm() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(true);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private void selectCard() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(true);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private void selectCash() {
        mBinding.layoutPayment.radioCash.setChecked(true);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private boolean isValid() {
        coi=mBinding.edCoi.getText().toString();
        legalName = mBinding.edLegalName.getText().toString();
        adharNumber=mBinding.edAdharCard.getText().toString();
        panNumber = mBinding.edPanNumber.getText().toString();
        gstNumber = mBinding.edGstNumber.getText().toString();
        dob=mBinding.tvDateOfBirth.getText().toString();
        nationality=mBinding.edNationality.getText().toString();
        opentime=mBinding.edNationality.getText().toString();
        closetime=mBinding.edCloseTime.getText().toString();
        minorder=mBinding.edMinOrder.getText().toString();
        avgtime=mBinding.edAverageTime.getText().toString();
        bankName = mBinding.edBankName.getText().toString();
        branchName = mBinding.edBranch.getText().toString();
        accountNumber = mBinding.edAccountNumber.getText().toString();
        ifscCode = mBinding.edIFSCCode.getText().toString();
        deliveryCharge = mBinding.edDelivery.getText().toString();
        serviceArea = mBinding.edServiceArea.getText().toString();
         if (isNull(legalName) || legalName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_legalname_of_company));
            return false;
        }else if (isNull(coi) || coi.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_coi));
            return false;
        } else if (isNull(panNumber) || panNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_pan_number));
            return false;
        } else if (panNumber.trim().length() != 10) {
            showToast(getResources().getString(R.string.please_enter_valid_pan_number));
            return false;
        } else if (isNull(adharNumber) || adharNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_adhar_number));
            return false;
        } else if (adharNumber.trim().length() != 12) {
            showToast(getResources().getString(R.string.please_enter_valid_adhar_number));
            return false;
        } else if (isNull(gstNumber) || gstNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_gst_number));
            return false;
        } else if (gstNumber.trim().length() != 15) {
            showToast(getResources().getString(R.string.please_enter_valid_gst_number));
            return false;
        } else if (isNull(dob) || dob.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_date_of_birth));
            return false;
        } else if (isNull(nationality) || nationality.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_nationality));
            return false;
        }else if (isNull(opentime) || opentime.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_open_time));
            return false;
        }else if (isNull(closetime) || closetime.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_close_time));
            return false;
        }else if (isNull(minorder) || minorder.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_min_order));
            return false;
        }else if (isNull(avgtime) || avgtime.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_average_time));
            return false;
        }
        else if (isNull(bankName) || bankName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_bank_name));
            return false;
        } else if (isNull(branchName) || branchName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_branch_name));
            return false;
        } else if (isNull(accountNumber) || accountNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_account_number));
            return false;
        } else if (isNull(ifscCode) || ifscCode.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_ifsc_code));
            return false;
        } else if (isNull(deliveryCharge) || deliveryCharge.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_delivery_charge));
            return false;
        } else if (isNull(serviceArea) || serviceArea.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_service_area));
            return false;
        }
        return true;

    }

    private void storeImage(int type, String imageUrl) {
        try {
            UploadImage postImage = new UploadImage(this, CommonUtility.getBitmap(imageUrl), type);
            postImage.execute();
        } catch (Exception e) {
            LogUtils.LOGE("StoreImage", e.toString());
        }
    }

    @Subscribe
    public void onImageEncoded(EncodedBitmap event) {
        int type = event.getType();
        if (type == AppConstants.STORE_IMAGE) {
            register.setStoreimage(event.getEncodeImage());
        } else if (type == AppConstants.FACULTY_IMAGE) {
            register.setFacultyimage(event.getEncodeImage());
        } else if (type == AppConstants.OWNER_IMAGE) {
            register.setOwnerimage(event.getEncodeImage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = CommonUtility.getMonth(monthOfYear) + "/" + CommonUtility.getMonth(dayOfMonth) + "/" + year;
        mBinding.tvDateOfBirth.setText(date);
    }
}

package com.app.merchant.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityUpdateProfileBinding;
import com.app.merchant.network.request.PaymentOption;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.adapter.PaymentAdapter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;
import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by arvind on 06/11/17.
 */

public class EditProfileActivity extends CommonActivity implements MvpView, View.OnClickListener {

    ActivityUpdateProfileBinding mBinding;
    private static String TAG = EditProfileActivity.class.getSimpleName();
    private List<PaymentOption> paymentList = new ArrayList<>();
    private PaymentAdapter paymentAdapter;
    private String profilePicFilePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        initializeData();
        setListener();
    }

    public void setListener() {
        mBinding.ivProfile.setOnClickListener(this);
        mBinding.tvDone.setOnClickListener(this);
    }

    public void initializeData() {
        mBinding.edName.setText(PreferenceUtils.getUserName());
        mBinding.edEmail.setText(PreferenceUtils.getEmail());
        CommonUtility.showCursorEnd(mBinding.edName);
        CommonUtility.showCursorEnd(mBinding.edEmail);
        CommonUtility.showCursorEnd(mBinding.edCreditDetails);
        mBinding.edName.setCursorVisible(true);
        GlideUtils.loadImageProfilePic(this, PreferenceUtils.getImage(), mBinding.ivProfile, null, R.drawable.avatar);
        setPaymentOption();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvPayment.setLayoutManager(layoutManager);
        paymentAdapter = new PaymentAdapter(this, paymentList);
        mBinding.rvPayment.setAdapter(paymentAdapter);
        CommonUtility.setRecyclerViewHeight(mBinding.rvPayment, paymentList, AppConstants.PAYMENT_HEIGHT);
    }

    private void setPaymentOption() {
        PaymentOption option1 = new PaymentOption();
        option1.setPaymentString(getResources().getString(R.string.cash_on_delivery));
        PaymentOption option2 = new PaymentOption();
        option2.setPaymentString(getResources().getString(R.string.credit_debit_card));
        PaymentOption option3 = new PaymentOption();
        option3.setPaymentString(getResources().getString(R.string.paytm));
        paymentList.add(option1);
        paymentList.add(option2);
        paymentList.add(option3);

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
            if(mBinding.ivProfile==view){
                showImageChooserDialog();
            }else if(mBinding.tvDone==view){
                updateProfile();
            }
    }


    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.profile_dialog_layout);
            View layoutCamera = dialog.findViewById(R.id.layoutCamera);
            View layoutGallery = dialog.findViewById(R.id.layoutGallery);
            View layoutCancel=dialog.findViewById(R.id.layoutCancel);
            layoutCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    openImageCamera();
                }
            });
            layoutGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    openImageGallery();
                }
            });
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                clearImage();
            }
        });
            dialog.show();

    }

    private void clearImage() {
        profilePicFilePath = "";
    }
    /**
     * Open camera to capture image
     */
    private void openImageCamera() {
        new ImagePicker.Builder(this)
                .mode(ImagePicker.Mode.CAMERA)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }

    /**
     * Open camera to capture image
     */
    private void openImageGallery() {
        new ImagePicker.Builder(this)
                .mode(ImagePicker.Mode.GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                List<String> mPaths = (List<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);
                profilePicFilePath = mPaths.get(0);
                profilePicFilePath="file:///"+profilePicFilePath;
                gotoCropper(Uri.parse(profilePicFilePath));
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                LogUtils.LOGD(TAG, "CROP");
                //handleCropResult(data);

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri imageUri = result.getUri();
                String path = imageUri.getPath();
                setImageFromLocal(path);

            }
        }
    }
    private void setImageFromLocal(String filePath) {
        profilePicFilePath=filePath;
        File f = new File(filePath);
        if (f.exists()) {
            GlideUtils.loadImageProfilePic(this, filePath, mBinding.ivProfile, null, R.drawable.avatar);
        }
    }
    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(this);
    }

    private void updateProfile() {
        try {
            //int age = Integer.parseInt(mBinding.edtAge.getText().toString().trim());
            //String name = mBinding.edtName.getText().toString().trim();
            //String phoneNumber = mBinding.edtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(profilePicFilePath)) {
               // presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
            } else {
                MultipartBody.Part body = CommonUtility.createMultipart(profilePicFilePath, AppConstants.PROFILE_UPDATE_PARAMETER);
                if (body != null) {
                   // presenter.updateProfile(this, name, phoneNumber, age, currentGender, body);
                } else {
                    //presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
                }

            }

        } catch (Exception e) {
            LogUtils.LOGE("ProfileUpdate", e.toString());
        }


    }



}

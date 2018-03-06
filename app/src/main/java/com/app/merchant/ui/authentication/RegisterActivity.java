package com.app.merchant.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityResisterBinding;
import com.app.merchant.network.request.dashboard.StoreImage;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.adapter.StoreImageAdapter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.google.android.gms.iid.InstanceID;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterActivity extends CommonActivity implements MvpView, View.OnClickListener, StoreImageAdapter.ImageListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    ActivityResisterBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String location;
    private String address;
    private StoreImageAdapter productAdapter;
    private List<StoreImage> productList = new ArrayList<>();
    private int adapterPosition;
    private String profilePicFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_resister);
        initializeAdapter();
        setListener();
    }

    private void initializeAdapter() {
        setList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        productAdapter = new StoreImageAdapter(this, productList, this);
        mBinding.rvDocument.setAdapter(productAdapter);
    }

    private void setList() {
        StoreImage product1 = new StoreImage();
        product1.setProductName("Store Image");
        productList.add(product1);
        StoreImage product2 = new StoreImage();
        product2.setProductName("Faculty Image");
        productList.add(product2);

        StoreImage product3 = new StoreImage();
        product3.setProductName("Owner Image");
        productList.add(product3);

    }

    public void setListener() {
        mBinding.tvNext.setOnClickListener(this);
        mBinding.layoutYes.setOnClickListener(this);
        mBinding.layoutNo.setOnClickListener(this);

    }

    public void initializeData() {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);

            /*String token = instanceID.getAuthToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);*/

            //Log.i(TAG, "GCM Registration Token: " + token);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) response;
                if (isNotNull(loginResponse)) {
                    String type = loginResponse.getType();
                    if (type.equals(AppConstants.SUCCESS)) {
                        PreferenceUtils.setUserName(address);
                        PreferenceUtils.setUserMono(location);
                        Bundle bundle = new Bundle();
                        bundle.putString(BundleConstants.USER_NAME, address);
                        bundle.putString(BundleConstants.MOBILE_NUMBER, location);
                        ExplicitIntent.getsInstance().navigateTo(this, VerifyAccountActivity.class, bundle);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvNext) {
            CommonUtility.clicked(mBinding.tvNext);
            ExplicitIntent.getsInstance().navigateTo(this, VerifyAccountActivity.class);
           /*if(isValid()){
               if(isNetworkConnected()){
                   presenter.getLoginDetail(this,new LoginRequest(address, location,
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
               }
           }*/
        } else if (view == mBinding.layoutYes) {
            mBinding.radioYes.setChecked(true);
            mBinding.radioNo.setChecked(false);
        } else if (view == mBinding.layoutNo) {
            mBinding.radioYes.setChecked(false);
            mBinding.radioNo.setChecked(true);
        }
    }

    private boolean isValid() {
        location = mBinding.edLocation.getText().toString();
        address = mBinding.edAddress.getText().toString();
        if ((isNotNull(location) && location.trim().length() > 0) && (isNotNull(address) && address.trim().length() > 0)) {
            return true;
        } else if (isNull(location) || location.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_location));
            return false;
        } else if (isNull(address) || address.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_address));
            return false;
        } else {
            return false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                List<String> mPaths = (List<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);
                profilePicFilePath = mPaths.get(0);
                profilePicFilePath = "file:///" + profilePicFilePath;
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
        profilePicFilePath = filePath;
        if (productList.size() > adapterPosition) {
            StoreImage storeImage = productList.get(adapterPosition);
            storeImage.setImageUrl(profilePicFilePath);
            productList.set(adapterPosition, storeImage);
            productAdapter.notifyDataSetChanged();
        }
        //loadImageToServer();
    }

    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    public void onItemClick(int position) {
        adapterPosition = position;

        showImageChooserDialog();
    }

    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.profile_dialog_layout);
        View layoutCamera = dialog.findViewById(R.id.layoutCamera);
        View layoutGallery = dialog.findViewById(R.id.layoutGallery);
        View layoutCancel = dialog.findViewById(R.id.layoutCancel);
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

    private void clearImage() {
        profilePicFilePath = "";
    }
}

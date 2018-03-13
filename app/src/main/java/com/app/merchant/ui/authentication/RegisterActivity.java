package com.app.merchant.ui.authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityResisterBinding;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.dashboard.StoreImage;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.adapter.StoreImageAdapter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.LogUtils;
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
    private ArrayList<StoreImage> storeImageList = new ArrayList<>();
    private int adapterPosition;
    private String profilePicFilePath;
    private String email;
    private String storeName;
    private String password;
    private String mobileNumber;

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
        productAdapter = new StoreImageAdapter(this, storeImageList, this);
        mBinding.rvDocument.setAdapter(productAdapter);
    }

    private void setList() {
        StoreImage storeImage = new StoreImage();
        storeImage.setStoreName("Store Image");
        storeImageList.add(storeImage);
        StoreImage facultyImage = new StoreImage();
        facultyImage.setStoreName("Faculty Image");
        storeImageList.add(facultyImage);
        StoreImage ownerImage = new StoreImage();
        ownerImage.setStoreName("Owner Image");
        storeImageList.add(ownerImage);
    }

    public void setListener() {
        mBinding.tvNext.setOnClickListener(this);
        mBinding.layoutProduct.setOnClickListener(this);
        mBinding.layoutService.setOnClickListener(this);

    }

    public void initializeData() {

    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvNext) {
            CommonUtility.clicked(mBinding.tvNext);
            if (isValid()) {
                Intent  intent = new Intent(this,StoreDetailsActivity.class);
                setIntentData(intent);
                    ExplicitIntent.getsInstance().navigateTo(this, StoreDetailsActivity.class,intent);
            }
        } else if (view == mBinding.layoutProduct) {
            mBinding.radioProduct.setChecked(true);
            mBinding.radioService.setChecked(false);
        } else if (view == mBinding.layoutService) {
            mBinding.radioProduct.setChecked(false);
            mBinding.radioService.setChecked(true);
        }
    }

    private void setIntentData(Intent intent) {
        RegisterRequest register = new RegisterRequest();
        register.setEmail(email);
        register.setPassword(password);
        register.setMobile(mobileNumber);
        register.setLocation(location);
        register.setAddress(address);
        register.setMerchantname(storeName);
        if (mBinding.radioProduct.isChecked()) {
            register.setStoretype(getResources().getString(R.string.product));
        } else if (mBinding.radioProduct.isChecked()) {
            register.setStoretype(getResources().getString(R.string.service));
        }
        register.setStoreList(storeImageList);
        intent.putExtra(BundleConstants.REGISTER_USER, register);
    }

    private boolean isValid() {
        email = mBinding.edEmail.getText().toString();
        password=mBinding.edPassword.getText().toString();
        mobileNumber=mBinding.edMobileNumber.getText().toString();
        location = mBinding.edLocation.getText().toString();
        address = mBinding.edAddress.getText().toString();
        storeName = mBinding.edStore.getText().toString();
        if (isNull(email) || email.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_email_address));
            mBinding.edEmail.requestFocus();
            return false;
        } else if (!CommonUtility.checkValidEmail(email)) {
            mBinding.edEmail.requestFocus();
            showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        }else if (isNull(password) || password.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_password));
            mBinding.edPassword.requestFocus();
            return false;
        }else if (isNull(password) || password.trim().length() <8) {
            showToast(getResources().getString(R.string.password_length_should_be_atleast_eight_character));
            mBinding.edPassword.requestFocus();
            return false;
        }else if (isNull(mobileNumber) || mobileNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            mBinding.edMobileNumber.requestFocus();
            return false;
        }else if (isNull(mobileNumber) || mobileNumber.trim().length() ==10) {
            showToast(getResources().getString(R.string.please_enter_valid_mobilenumber));
            mBinding.edMobileNumber.requestFocus();
            return false;
        } else if (isNull(location) || location.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_location));
            mBinding.edLocation.requestFocus();
            return false;
        } else if (isNull(address) || address.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_address));
            mBinding.edAddress.requestFocus();
            return false;
        } else if (isNull(storeName) || storeName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_store_name));
            mBinding.edStore.requestFocus();
            return false;
        } else if (!CommonUtility.checkValidName(storeName)) {
            mBinding.edStore.requestFocus();
            showToast(getResources().getString(R.string.please_enter_valid_store_name));
            return false;
        }else if (isNull(storeImageList.get(0).getImageUrl()) || storeImageList.get(0).getImageUrl().trim().length() == 0) {
            showToast(getResources().getString(R.string.please_select_store_image));
            return false;
        } else if (isNull(storeImageList.get(1).getImageUrl()) || storeImageList.get(1).getImageUrl().trim().length() == 0) {
            showToast(getResources().getString(R.string.please_select_faculty_image));
            return false;
        } else if (isNull(storeImageList.get(2).getImageUrl()) || storeImageList.get(2).getImageUrl().trim().length() == 0) {
            showToast(getResources().getString(R.string.please_select_owner_image));
            return false;
        }
        return true;
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
        if (storeImageList.size() > adapterPosition) {
            StoreImage storeImage = storeImageList.get(adapterPosition);
            storeImage.setImageUrl(profilePicFilePath);
            storeImageList.set(adapterPosition, storeImage);
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

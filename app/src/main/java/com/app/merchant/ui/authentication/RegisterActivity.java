package com.app.merchant.ui.authentication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityResisterBinding;
import com.app.merchant.event.UpdateAddress;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.dashboard.StoreImage;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.adapter.StoreImageAdapter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.location.GPSTracker;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.app.merchant.utility.AppConstants.PERMISSIONS_REQUEST_LOCATION;

public class RegisterActivity extends CommonActivity implements MvpView, View.OnClickListener, StoreImageAdapter.ImageListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    ActivityResisterBinding mBinding;
    private GPSTracker gpsTracker;

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
    private double latitude;
    private double longitude;
    private String merchantName;

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
        mBinding.tvAddress.setOnClickListener(this);
        mBinding.layoutProduct.setOnClickListener(this);
        mBinding.layoutService.setOnClickListener(this);
        mBinding.tvLocateMe.setOnClickListener(this);
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
                Intent intent = new Intent(this, StoreDetailsActivity.class);
                setIntentData(intent);
                ExplicitIntent.getsInstance().navigateTo(this, StoreDetailsActivity.class, intent);
            }
        } else if (view == mBinding.layoutProduct) {
            mBinding.radioProduct.setChecked(true);
            mBinding.radioService.setChecked(false);
        } else if (view == mBinding.layoutService) {
            mBinding.radioProduct.setChecked(false);
            mBinding.radioService.setChecked(true);
        } else if (view == mBinding.tvAddress) {
            CommonUtility.clicked(mBinding.tvAddress);
            if (isNetworkConnected()) {
                address();
            }
        } else if (view == mBinding.tvLocateMe) {
            CommonUtility.clicked(mBinding.tvLocateMe);
            if (isNetworkConnected()) {
                getCurrentLocation();
            }
        }
    }
    private void getCurrentLocation() {
        // create class object
        gpsTracker = new GPSTracker(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            gpsTracker.requestPermission(this);
        } else if (gpsTracker.canGetLocation()) {
            getLatLong();
        }
    }

    private void getLatLong() {
        PreferenceUtils.setLatitude(gpsTracker.getLatitude());
        PreferenceUtils.setLongitude(gpsTracker.getLongitude());
        latitude=gpsTracker.getLatitude();
        longitude=gpsTracker.getLongitude();
        address=PreferenceUtils.getAddress(this, PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude());
        mBinding.tvAddress.setText(address);
        PreferenceUtils.setAddress(address);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gpsTracker.getLocation();
                    getLatLong();
                } else {
                    showToast(getResources().getString(R.string.gps_permittion_denied));
                }
                break;
        }
    }
    private void address() {
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, AppConstants.SELECT_MANUALLY);
        } catch (GooglePlayServicesRepairableException e) {
        } catch (GooglePlayServicesNotAvailableException e) {
        }
    }

    private void setIntentData(Intent intent) {
        RegisterRequest register = new RegisterRequest();
        register.setMerchantname(merchantName);
        register.setEmail(email);
        register.setPassword(password);
        register.setMobile(mobileNumber);
        register.setLocation(location);
        register.setAddress(address);
        register.setLegalname(storeName);
        register.setLat(latitude);
        register.setLng(longitude);

        if (mBinding.radioProduct.isChecked()) {
            register.setStoretype(getResources().getString(R.string.product));
        } else if (mBinding.radioProduct.isChecked()) {
            register.setStoretype(getResources().getString(R.string.service));
        }
        register.setStoreList(storeImageList);
        intent.putExtra(BundleConstants.REGISTER_USER, register);
    }

    private boolean isValid() {
        merchantName=mBinding.edMerchantName.getText().toString();
        email = mBinding.edEmail.getText().toString();
        password = mBinding.edPassword.getText().toString();
        mobileNumber = mBinding.edMobileNumber.getText().toString();
        location = mBinding.edLocation.getText().toString();
        address = mBinding.tvAddress.getText().toString();
        storeName = mBinding.edStore.getText().toString();
        if (isNull(merchantName) || merchantName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_merchat_name));
            mBinding.edEmail.requestFocus();
            return false;
        }else if (isNull(email) || email.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_email_address));
            mBinding.edEmail.requestFocus();
            return false;
        } else if (!CommonUtility.checkValidEmail(email)) {
            mBinding.edEmail.requestFocus();
            showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        } else if (isNull(password) || password.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_password));
            mBinding.edPassword.requestFocus();
            return false;
        } else if (isNull(password) || password.trim().length() < 8) {
            showToast(getResources().getString(R.string.password_length_should_be_atleast_eight_character));
            mBinding.edPassword.requestFocus();
            return false;
        } else if (isNull(mobileNumber) || mobileNumber.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            mBinding.edMobileNumber.requestFocus();
            return false;
        } else if (isNull(mobileNumber) || mobileNumber.trim().length() < 10) {
            showToast(getResources().getString(R.string.please_enter_valid_mobilenumber));
            mBinding.edMobileNumber.requestFocus();
            return false;
        } else if (isNull(location) || location.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_location));
            mBinding.edLocation.requestFocus();
            return false;
        } else if (isNull(address) || address.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_address));
            return false;
        } else if (isNull(storeName) || storeName.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_store_name));
            mBinding.edStore.requestFocus();
            return false;
        }/* else if (!CommonUtility.checkValidName(storeName)) {
            mBinding.edStore.requestFocus();
            showToast(getResources().getString(R.string.please_enter_valid_store_name));
            return false;
        }*/ else if (isNull(storeImageList.get(0).getImageUrl()) || storeImageList.get(0).getImageUrl().trim().length() == 0) {
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

            } else if (requestCode == AppConstants.SELECT_MANUALLY) {
                try {
                    if (CommonUtility.isNotNull(data)) {
                        // retrive the data by using getPlace() method.
                        Place place = PlaceAutocomplete.getPlace(this, data);
                        if (CommonUtility.isNotNull(place)) {
                            LatLng latLng = place.getLatLng();
                            PreferenceUtils.setAddress(place.getAddress().toString());
                            PreferenceUtils.setLatitude(latLng.latitude);
                            PreferenceUtils.setLongitude(latLng.longitude);
                            latitude=latLng.latitude;
                            longitude=latLng.longitude;
                            address=PreferenceUtils.getAddress(this, PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude());
                            PreferenceUtils.setAddress(address);
                            mBinding.tvAddress.setText(address);

                        }

                    }
                } catch (Exception ex) {
                    showToast(getResources().getString(R.string.something_went_wrong));
                    finish();
                }
            }
        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            // TODO: Handle the error.
            Log.e("Tag", status.getStatusMessage());

        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
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

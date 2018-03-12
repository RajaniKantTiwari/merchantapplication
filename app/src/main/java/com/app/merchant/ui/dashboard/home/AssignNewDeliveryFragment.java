package com.app.merchant.ui.dashboard.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentDeliveryBinding;
import com.app.merchant.network.request.dashboard.StoreImage;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.ui.adapter.StoreImageAdapter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.LogUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.app.merchant.utility.CommonUtility.isNotNull;
import static com.app.merchant.utility.CommonUtility.isNull;

public class AssignNewDeliveryFragment extends DashboardFragment implements MvpView, View.OnClickListener,StoreImageAdapter.ImageListener {
    private static final String TAG = AssignNewDeliveryFragment.class.getSimpleName();
    private String name;
    private String dateOfBirth;
    private StoreImageAdapter productAdapter;
    private List<StoreImage> productList = new ArrayList<>();
    private int adapterPosition;
    private String profilePicFilePath;
    private FragmentDeliveryBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery,container,false);
        getDashboardActivity().setHeaderTitle(getString(R.string.assign_new_delivery_boy));
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        setList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvDocument.setLayoutManager(layoutManager);
        productAdapter = new StoreImageAdapter(getDashboardActivity(), productList, this);
        mBinding.rvDocument.setAdapter(productAdapter);
    }

    private void setList() {
        StoreImage product1 = new StoreImage();
        product1.setStoreName("Delivery Boy Photo");
        productList.add(product1);
        StoreImage product2 = new StoreImage();
        product2.setStoreName("Delivery Boy ID");
        productList.add(product2);
    }

    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return null;
    }

    public void initializeData() {

    }

    @Override
    public void attachView() {

        getPresenter().attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(isNotNull(response)){
            if(response instanceof LoginResponse){
                LoginResponse loginResponse=(LoginResponse)response;
                if(isNotNull(loginResponse)){
                    if(response.getStatus().equals(AppConstants.SUCCESS)){
                        Bundle bundle=new Bundle();

                        /*ExplicitIntent.getsInstance().navigateTo(this,VerifyAccountActivity.class,bundle);*/
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvDone){
            CommonUtility.clicked(mBinding.tvDone);
            getBaseActivity().onBackPressed();
           /*if(isValid()){
               if(isNetworkConnected()){
                   presenter.registerMerchant(this,new RegisterRequest(dateOfBirth, name,
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
               }
           }*/
        }
    }

    private boolean isValid() {
        name =mBinding.edName.getText().toString();
        dateOfBirth =mBinding.edDateOfBirth.getText().toString();
        if((isNotNull(name)&& name.trim().length()>0)&&(isNotNull(dateOfBirth)&& dateOfBirth.trim().length()>0)){
            return true;
        }else if(isNull(name)|| name.trim().length()==0){
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_name));
            return false;
        }else if(isNull(dateOfBirth)|| dateOfBirth.trim().length()==0){
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_date_of_birth));
            return false;
        }else {
            return false;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                .start(getDashboardActivity());
    }
    @Override
    public void onItemClick(int position) {
        adapterPosition = position;

        showImageChooserDialog();
    }
    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(getDashboardActivity());
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
        new ImagePicker.Builder(getDashboardActivity())
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
        new ImagePicker.Builder(getDashboardActivity())
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

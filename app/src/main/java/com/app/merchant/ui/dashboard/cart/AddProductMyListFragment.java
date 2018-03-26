package com.app.merchant.ui.dashboard.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAddProductMyListBinding;
import com.app.merchant.network.request.dashboard.cart.MerchantProductListRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.cart.Category;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.network.response.dashboard.cart.SubCategory;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.GlideUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ashok on 13/11/17.
 */

public class AddProductMyListFragment extends DashboardFragment implements DatePickerDialog.OnDateSetListener {
    private FragmentAddProductMyListBinding mBinding;
    private int position;
    private Category category;
    private SubCategory subCategory;
    private Product product;
    private String quantity;
    private String mrp;
    private String sellingPrice;
    private String taxType;
    private String taxPercent;
    private String manufacturingDate;
    private String expiryDate;
    private String manufactorur;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product_my_list, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.add_product_in_list));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            category = bundle.getParcelable(BundleConstants.CATEGORY);
            subCategory = bundle.getParcelable(BundleConstants.SUBCATEGORY);
            product = bundle.getParcelable(BundleConstants.PRODUCT);
        }
        if (CommonUtility.isNotNull(category)) {
            mBinding.tvCatName.setText(category.getName());
            GlideUtils.loadImage(getContext(), category.getIcons(), mBinding.ivCategory, null, R.drawable.icon_placeholder);
        }
        if (CommonUtility.isNotNull(subCategory)) {
            mBinding.tvSubCatName.setText(subCategory.getName());
            //GlideUtils.loadImage(getContext(), subCategory.get, mBinding.ivSubCategory, null, R.drawable.icon_placeholder);
        }
        if (CommonUtility.isNotNull(product)) {
            mBinding.tvProductName.setText(product.getProductname());
            GlideUtils.loadImage(getContext(), product.getIcon(), mBinding.ivProductImage, null, R.drawable.icon_placeholder);
        }

    }

    @Override
    public void setListener() {
        List<String> taxList = Arrays.asList(getResources().getStringArray(R.array.tax_type));
        AsignAdapter adapter = new AsignAdapter(getContext(), taxList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.taxSpiner.setAdapter(adapter);
        mBinding.taxSpiner.setSelection(adapter.getCount());
        mBinding.taxSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != taxList.size() - 1) {
                    if (CommonUtility.isNotNull(view)) {
                        view.setBackgroundResource(0);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBinding.tvManufactoringDate.setOnClickListener(this);
        mBinding.tvExpiryDate.setOnClickListener(this);
        mBinding.tvAddProduct.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return AddProductMyListFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
       if(CommonUtility.isNotNull(response)&&response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)){
           getDashboardActivity().showToast(response.getMsg());
           getDashboardActivity().onBackPressed();
       }
    }

    @Override
    public void onClick(View view) {
        if (mBinding.tvManufactoringDate == view) {
            position = 1;
            CommonUtility.openPicker(getDashboardActivity(), this);
        } else if (mBinding.tvExpiryDate == view) {
            position = 2;
            CommonUtility.openPicker(getDashboardActivity(), this);
        } else if (mBinding.tvAddProduct == view) {
            CommonUtility.clicked(mBinding.tvAddProduct);
            if (isValid()) {
                MerchantProductListRequest request = new MerchantProductListRequest();
                setAddProductData(request);
                if (isNetworkConnected()) {
                    getPresenter().addProductToMerchantList(getDashboardActivity(), request);
                }
            }
        }
    }

    private boolean isValid() {
        quantity = mBinding.edQty.getText().toString();
        mrp = mBinding.edMrp.getText().toString();
        sellingPrice = mBinding.edSelling.getText().toString();
        taxType = mBinding.taxSpiner.getSelectedItem().toString();
        taxPercent = mBinding.edTaxPercent.getText().toString();
        manufacturingDate = mBinding.tvManufactoringDate.getText().toString();
        expiryDate = mBinding.tvExpiryDate.getText().toString();
        manufactorur = mBinding.edManufacturer.getText().toString();
        if (CommonUtility.isNull(quantity) || quantity.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_quantity));
            return false;
        } else if (CommonUtility.isNull(mrp) || mrp.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_mrp));
            return false;
        } else if (CommonUtility.isNull(sellingPrice) || sellingPrice.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_selling_price));
            return false;
        } else if (CommonUtility.isNull(taxType) || taxType.trim().equalsIgnoreCase(getResources().getString(R.string.tax).trim())) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_select_tax_type));
            return false;
        } else if (CommonUtility.isNull(taxPercent) || taxPercent.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_tax_percent));
            return false;
        } else if (Integer.parseInt(taxPercent.trim()) > 35) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_tax_percent));
            return false;
        } else if (CommonUtility.isNull(manufacturingDate) || manufacturingDate.trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_manufactor_date));
            return false;
        }
        return true;
    }

    private void setAddProductData(MerchantProductListRequest request) {
        request.setMerchant_id(PreferenceUtils.getMerchantId());
        request.setQty(quantity);
        request.setMrp(mrp);
        request.setSelling_price(sellingPrice);
        request.setTax_type(taxType);
        request.setTax_percent(taxPercent);
        request.setManufacturer_date(manufacturingDate);
        if (CommonUtility.isNotNull(manufactorur) && manufactorur.trim().length() > 0) {
            request.setManufacturer(manufactorur);
        }
        if (CommonUtility.isNotNull(expiryDate) && expiryDate.trim().length() > 0) {
            request.setExpirey_date(expiryDate);
        }
        if (CommonUtility.isNotNull(category)) {
            request.setCategory(category.getId());
        }
        if (CommonUtility.isNotNull(subCategory)) {
            request.setCategory(subCategory.getId());
        }
        if (CommonUtility.isNotNull(product)) {
            request.setMaster_product_id(String.valueOf(product.getMasterproductid()));
        }
    }

    @Override
    public void headerChangedCalled() {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = CommonUtility.getMonth(monthOfYear) + "/" + CommonUtility.getMonth(dayOfMonth) + "/" + year;
        if (position == 1) {
            mBinding.tvManufactoringDate.setText(date);
        } else if (position == 2) {
            mBinding.tvExpiryDate.setText(date);
        }
    }
}

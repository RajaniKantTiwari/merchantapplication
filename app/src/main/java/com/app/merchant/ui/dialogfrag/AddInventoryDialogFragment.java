package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogInventoryBinding;
import com.app.merchant.databinding.DialogRatingBinding;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;


public class AddInventoryDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogInventoryBinding mBinding;
    private InventoryDialogListener listener;
    private String productName;


    public interface InventoryDialogListener {
        void addInventoryClick(String feedback);
    }
    public void addListener(InventoryDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_inventory, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog,getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            productName=bundle.getString(BundleConstants.PRODUCT_NAME);
            mBinding.tvName.setText(productName);
        }
    }

    public void setListener() {
        mBinding.tvAddInventory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvAddInventory) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                listener.addInventoryClick(mBinding.edNumberOfProduct.getText().toString());
            }
        }
    }
}
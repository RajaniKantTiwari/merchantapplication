package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.merchant.R;
import com.app.merchant.databinding.OkDialogBinding;
import com.app.merchant.utility.CommonUtility;


public class OkDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private OkDialogBinding mBinding;
    private OkDialogListener listener;


    public interface OkDialogListener {
        void next(String str);
    }

    public void dialogListener(OkDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.ok_dialog, container, false);
        dialog = getDialog();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        setListener();
        return mBinding.getRoot();
    }

    public void setListener() {
        mBinding.tvOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvOk) {
            if(CommonUtility.isNotNull(listener))
                listener.next(null);
        }
    }
}
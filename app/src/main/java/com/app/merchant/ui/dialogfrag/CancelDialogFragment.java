package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogCancelBinding;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;


public class CancelDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogCancelBinding mBinding;
    private CancelDialogListener listener;
    private int position;

    public interface CancelDialogListener {
        void submit(int position, String reason);
    }

    public void addListener(CancelDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_cancel, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog, getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            position = bundle.getInt(BundleConstants.POSITION);
        }
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            CommonUtility.clicked(mBinding.tvSubmit);
            String submit = mBinding.edCancelReason.getText().toString();
            if (CommonUtility.isNotNull(submit) && submit.trim().length() > 0) {
                dialog.cancel();
                if (CommonUtility.isNotNull(listener)) {
                    listener.submit(position, mBinding.edCancelReason.getText().toString());
                }
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.enter_reason_of_cancel), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
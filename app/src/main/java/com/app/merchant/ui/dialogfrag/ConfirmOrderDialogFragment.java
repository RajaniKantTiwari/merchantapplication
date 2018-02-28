package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogConfirmOrderBinding;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConfirmOrderDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogConfirmOrderBinding mBinding;
    private OrderDialogListener listener;

    public interface OrderDialogListener {
        void submit(String submit);
    }
    public void addListener(OrderDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_confirm_order, container, false);
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

        }
        List<String> daysArrayList = new ArrayList<>();
        daysArrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.selected_type)));
        AsignAdapter adapter = new AsignAdapter(getContext(), daysArrayList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
    }

    public void setListener() {
        mBinding.tvConfirmOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvConfirmOrder) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                //listener.submit(mBinding.edFeedBack.getText().toString());
            }
        }
    }
}
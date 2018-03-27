package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogRatingBinding;
import com.app.merchant.utility.CommonUtility;


public class RatingDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogRatingBinding mBinding;
    private RatingDialogListener listener;
    private int id;
    private String storeName;

    public interface RatingDialogListener {
        void submit(int id, float rating, String feedback);
    }
    public void addListener(RatingDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_rating, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        CommonUtility.setRating(mBinding.ratingBar);
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
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                listener.submit(id,mBinding.ratingBar.getRating(),mBinding.edFeedBack.getText().toString());
            }
        }
    }
}
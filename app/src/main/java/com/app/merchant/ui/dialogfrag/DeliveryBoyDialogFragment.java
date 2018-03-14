package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogDeliveryBoyBinding;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;


public class DeliveryBoyDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogDeliveryBoyBinding mBinding;
    private DeliveryBoyDialogListener listener;
    private FragmentActivity mActivity;
    private ArrayList<DeliveryBoy> deliveryBoyList;

    public interface DeliveryBoyDialogListener {
        void newDeliveryBoy();
    }
    public void addListener(DeliveryBoyDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delivery_boy, container, false);
        dialog = getDialog();
        mActivity=getActivity();
        CommonUtility.setDialog(dialog);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog,mActivity);
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            deliveryBoyList=bundle.getParcelableArrayList(BundleConstants.DELIVERY_BOY_LIST);
        }
        ArrayList<String> deliveryBoyNameList=new ArrayList<>();
        if(CommonUtility.isNotNull(deliveryBoyList)){
            for(int i=0;i<deliveryBoyList.size();i++){
                deliveryBoyNameList.add(deliveryBoyList.get(i).getName());
            }
        }
        AsignAdapter adapter =new AsignAdapter(getContext(), deliveryBoyNameList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.selectedSpiner.setAdapter(adapter);
        mBinding.selectedSpiner.setSelection(adapter.getCount());
        mBinding.selectedSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != deliveryBoyNameList.size() - 1) {
                    if (CommonUtility.isNotNull(view)) {
                        view.setBackgroundResource(0);
                        Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setListener() {
        mBinding.tvNewDelivery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvNewDelivery) {
            dialog.cancel();
            if(CommonUtility.isNotNull(listener)){
                listener.newDeliveryBoy();
            }
        }
    }
}
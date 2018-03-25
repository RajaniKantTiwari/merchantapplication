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
import com.app.merchant.databinding.DialogConfirmOrderBinding;
import com.app.merchant.network.response.dashboard.Order;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConfirmOrderDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogConfirmOrderBinding mBinding;
    private OrderDialogListener listener;
    private FragmentActivity mActivity;
    private ArrayList<DeliveryBoy> deliveryBoyList;
    private Order order;

    public interface OrderDialogListener {
        void confirmed();
        void notConfirmed();
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
        mActivity = getActivity();
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog, mActivity);
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            order = bundle.getParcelable(BundleConstants.ORDER);
            deliveryBoyList = bundle.getParcelableArrayList(BundleConstants.DELIVERY_BOY_LIST);
        }
        ArrayList<String> deliveryBoyNameList = new ArrayList<>();
        for (int i = 0; i < deliveryBoyList.size(); i++) {
            deliveryBoyNameList.add(deliveryBoyList.get(i).getName());
        }

        AsignAdapter adapter = new AsignAdapter(getContext(), deliveryBoyNameList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.selectedSpiner.setAdapter(adapter);
        mBinding.selectedSpiner.setSelection(adapter.getCount());
        mBinding.selectedSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != deliveryBoyNameList.size() - 1) {
                    if (CommonUtility.isNotNull(view)) {
                        view.setBackgroundResource(0);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBinding.tvOrderNumber.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.order_number),order.getOrder_id()));
        mBinding.tvCustomerName.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.customer_name),order.getOrder_id()));
        mBinding.tvCustomerAddress.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.customer_address),order.getOrder_id()));
        mBinding.tvNumberOfItem.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.number_of_item),order.getOrder_id()));
        mBinding.tvOrdervalue.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.order_value),order.getOrder_id()));

    }

    public void setListener() {
        mBinding.tvConfirmOrder.setOnClickListener(this);
        mBinding.tvNotConfirmed.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvConfirmOrder) {
            dialog.cancel();
            if (CommonUtility.isNotNull(listener)) {
                listener.confirmed();
            }
        } else if (view == mBinding.tvNotConfirmed) {
            listener.notConfirmed();
        }
    }
}
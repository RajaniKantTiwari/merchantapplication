package com.app.merchant.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.app.merchant.R;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;

import java.util.ArrayList;

/**
 * Created by rajnikant on 24/01/18.
 */

public class AsignAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflator;
    private final Context context;

    public AsignAdapter(Context context, ArrayList<String> daysList) {
        super(context, 0, daysList);
        this.context=context;
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.spinner_row, parent, false);
        }
        try {
            TextView tvDays = convertView.findViewById(R.id.tvDays);
            if(position==getCount()){
               tvDays.setBackgroundResource(0);
            }
            String days = getItem(position);
            tvDays.setText(days);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
    @Override
    public int getCount() {
        return super.getCount()-1; // you dont display last item. It is used as hint.
    }
}

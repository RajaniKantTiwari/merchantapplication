package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAllPerformanceBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.chart.MyAxisValueFormatter;
import com.app.merchant.ui.dashboard.chart.MyValueFormatter;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashok on 13/11/17.
 */

public class AllPerformanceFragment extends DashboardFragment {
    private FragmentAllPerformanceBinding mBinding;
    private int xValue=10;
    private  int yValue=12;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_performance, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.all_performance));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        receivedChartData();

    }

    private void receivedChartData() {

        initializeReceivedChart();
        setReceivedChartData();
        initializeDeliveredChart();
        setDeliveredChartData();
    }



    private void initializeReceivedChart() {
        /*XAxis xAxis = mBinding.orderReceived.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);*/
        YAxis y = mBinding.orderReceived.getAxisLeft();
        y.setLabelCount(5);
        y.setAxisMaximum(100);
        y.setAxisMinimum(20);
        mBinding.orderReceived.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                BarEntry entry = (BarEntry) e;

                if (entry.getYVals() != null)
                    Log.i("VAL SELECTED", "Value: " + entry.getYVals()[h.getStackIndex()]);
                else
                    Log.i("VAL SELECTED", "Value: " + entry.getY());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        mBinding.orderReceived.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBinding.orderReceived.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mBinding.orderReceived.setPinchZoom(false);

        mBinding.orderReceived.setDrawGridBackground(false);
        mBinding.orderReceived.setDrawBarShadow(false);

        mBinding.orderReceived.setDrawValueAboveBar(false);
        mBinding.orderReceived.setHighlightFullBarEnabled(false);
        // change the position of the y-labels
        YAxis leftAxis = mBinding.orderReceived.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mBinding.orderReceived.getAxisRight().setEnabled(false);
        mBinding.orderReceived.getLegend().setEnabled(false);

        /*XAxis xLabels = mBinding.orderReceived.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBinding.orderReceived.getXAxis().setEnabled(false);*/
        mBinding.orderReceived.getXAxis().setTextColor(Color.TRANSPARENT);
        Legend l = mBinding.orderReceived.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(18f);
        l.setFormToTextSpace(14f);
        l.setXEntrySpace(8f);


    }
    private void setReceivedChartData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < xValue + 1; i++) {
            float mult = (yValue + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            float val3 = (float) (Math.random() * mult) + mult / 3;

            yVals1.add(new BarEntry(
                    i,
                    new float[]{val1, val2, val3},
                    getResources().getDrawable(R.drawable.star)));
        }

        BarDataSet set1;

        if (mBinding.orderReceived.getData() != null &&
                mBinding.orderReceived.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBinding.orderReceived.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBinding.orderReceived.getData().notifyDataChanged();
            mBinding.orderReceived.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);
            set1.setColors(getReceivedColors());
            set1.setStackLabels(new String[]{"Births", "Divorces", "Marriages"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new MyValueFormatter());
            data.setValueTextColor(Color.TRANSPARENT);

            mBinding.orderReceived.setData(data);
        }

        mBinding.orderReceived.setFitBars(true);
        mBinding.orderReceived.invalidate();
    }

    private void initializeDeliveredChart() {
        mBinding.orderDelivered.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                BarEntry entry = (BarEntry) e;

                if (entry.getYVals() != null)
                    Log.i("VAL SELECTED", "Value: " + entry.getYVals()[h.getStackIndex()]);
                else
                    Log.i("VAL SELECTED", "Value: " + entry.getY());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        mBinding.orderDelivered.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBinding.orderDelivered.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mBinding.orderDelivered.setPinchZoom(false);

        mBinding.orderDelivered.setDrawGridBackground(false);
        mBinding.orderDelivered.setDrawBarShadow(false);

        mBinding.orderDelivered.setDrawValueAboveBar(false);
        mBinding.orderDelivered.setHighlightFullBarEnabled(false);
        // change the position of the y-labels
        YAxis leftAxis = mBinding.orderDelivered.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mBinding.orderDelivered.getAxisRight().setEnabled(false);
        mBinding.orderDelivered.getLegend().setEnabled(false);

        XAxis xLabels = mBinding.orderDelivered.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        Legend l = mBinding.orderDelivered.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
    }
    private void setDeliveredChartData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < xValue + 1; i++) {
            float mult = (yValue + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            float val3 = (float) (Math.random() * mult) + mult / 3;

            yVals1.add(new BarEntry(
                    i,
                    new float[]{val1, val2, val3},
                    getResources().getDrawable(R.drawable.star)));
        }

        BarDataSet set1;

        if (mBinding.orderDelivered.getData() != null &&
                mBinding.orderDelivered.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBinding.orderDelivered.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBinding.orderDelivered.getData().notifyDataChanged();
            mBinding.orderDelivered.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);
            set1.setColors(getDeliveredColors());
            //set1.setStackLabels(new String[]{"Births", "Divorces", "Marriages"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new MyValueFormatter());
            data.setValueTextColor(Color.WHITE);

            mBinding.orderDelivered.setData(data);
        }

        mBinding.orderDelivered.setFitBars(true);
        mBinding.orderDelivered.invalidate();
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return AllPerformanceFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
    private int[] getReceivedColors() {
        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.ORDER_RECEIVED_COLORS[i];
        }

        return colors;
    }
    private int[] getDeliveredColors() {
        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.ORDER_DELIVERED_COLORS[i];
        }

        return colors;
    }

    @Override
    public void headerChangedCalled() {

    }
}

package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderConfirmedBinding;
import com.app.merchant.databinding.FragmentOrderOutForDeliveryBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderConfirmedAdapter;
import com.app.merchant.ui.dialogfrag.DeliveryBoyDialogFragment;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;


/**
 * Created by ashok on 13/11/17.
 */

public class OrderOutForDeliveryFragment extends DashboardFragment implements
        OrderConfirmedAdapter.OrderConfirmedListener,DeliveryBoyDialogFragment.DeliveryBoyDialogListener {
    private FragmentOrderOutForDeliveryBinding mBinding;
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = false;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_out_for_delivery, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_out_for_delivery));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
      initializeChartData();
      initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        OrderConfirmedAdapter mAdapter=new OrderConfirmedAdapter(getDashboardActivity(),this);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    private void initializeChartData() {
        mBinding.lineChart.setOnValueTouchListener(new ValueTouchListener());
        // Generate some random values.
        generateValues();

        generateData();

        // Disable viewport recalculations, see toggleCubic() method for more info.
        mBinding.lineChart.setViewportCalculationEnabled(false);

        resetViewport();

    }
    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(mBinding.lineChart.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfPoints - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[2]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor){
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        mBinding.lineChart.setLineChartData(data);

    }


    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return OrderOutForDeliveryFragment.class.getSimpleName();
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



    @Override
    public void onOrderConfirmClick(int position) {
        Bundle bundle=new Bundle();
        CommonUtility.showNewDeliveryDialog(getDashboardActivity(),bundle,this);
    }

    @Override
    public void newDeliveryBoy() {
        Bundle bundle=new Bundle();
        getDashboardActivity().addFragmentInContainer(new DeliveryFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}

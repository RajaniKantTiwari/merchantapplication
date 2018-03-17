package com.app.merchant.ui.dashboard.home.graphfragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentAssignDeliveryBoyBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy.AssignDeliveryBoy;
import com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy.AssignDeliveryBoyChart;
import com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy.AssignDeliveryBoyChartData;
import com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy.AssignDeliveryBoyData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryChart;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.AssignNewDeliveryFragment;
import com.app.merchant.ui.dashboard.home.adapter.AssignDeliveryBoyAdapter;
import com.app.merchant.ui.dialogfrag.DeliveryBoyDialogFragment;
import com.app.merchant.ui.dialogfrag.RatingDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;


/**
 * Created by ashok on 13/11/17.
 */

public class AssignDeliveryBoyFragment extends DashboardFragment implements
        AssignDeliveryBoyAdapter.AssignDeliveryListener,
        DeliveryBoyDialogFragment.DeliveryBoyDialogListener, RatingDialogFragment.RatingDialogListener {
    private FragmentAssignDeliveryBoyBinding mBinding;
    private ArrayList<AssignDeliveryBoy> deliveryBoyList = new ArrayList<>();
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfOrderForDelivery;
    float[][] orderOutForDeliveryTab = new float[maxNumberOfLines][numberOfOrderForDelivery];
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    /*private boolean pointsHaveDifferentColor;*/
    private boolean hasGradientToTransparent = false;
    private AssignDeliveryBoyAdapter mAdapter;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_assign_delivery_boy, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.assign_delivery_boy));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getPresenter().assignDeliveryBoyChart(getDashboardActivity());
        getPresenter().assignDeliveryBoy(getDashboardActivity());
        initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mAdapter = new AssignDeliveryBoyAdapter(getDashboardActivity(), deliveryBoyList, this);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    private void initializeChartData(ArrayList<AssignDeliveryBoyChart> data) {
        mBinding.lineChart.setOnValueTouchListener(new ValueTouchListener());
        // Generate some random values.
        generateValues(data);
        generateData(data);
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
        v.right = numberOfOrderForDelivery - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }

    private void generateValues(ArrayList<AssignDeliveryBoyChart> data) {
        if (CommonUtility.isNotNull(data)) {
            numberOfOrderForDelivery = data.size();
            orderOutForDeliveryTab = new float[maxNumberOfLines][numberOfOrderForDelivery];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfOrderForDelivery; ++j) {
                    orderOutForDeliveryTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData(ArrayList<AssignDeliveryBoyChart> data) {

        List<Line> lines = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfOrderForDelivery; ++j) {
                values.add(new PointValue(j, orderOutForDeliveryTab[i][j]));
                if (CommonUtility.isNotNull(data) && data.size() > j) {
                    axisValues.add(new AxisValue(j).setLabel(CommonUtility.formatDate(data.get(j).getInvoiceDate())));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[6]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
            /*if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }*/
            lines.add(line);
        }
        this.data = new LineChartData(lines);
        this.data.setAxisXBottom(new Axis(axisValues).setHasLines(true).setMaxLabelChars(3));
        this.data.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));
        this.data.setBaseValue(Float.NEGATIVE_INFINITY);
        mBinding.lineChart.setLineChartData(this.data);

    }


    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return AssignDeliveryBoyFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
       getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.CHART_DATA) {
                AssignDeliveryBoyChartData chartData=(AssignDeliveryBoyChartData)response;
                if(CommonUtility.isNotNull(chartData)){
                    initializeChartData(chartData.getData());
                }
            } else if (requestCode == AppConstants.ORDER_DATA) {
                AssignDeliveryBoyData deliveryData=(AssignDeliveryBoyData)response;
                if(CommonUtility.isNotNull(deliveryData)){
                    if(CommonUtility.isNotNull(deliveryData.getData())){
                        deliveryBoyList.addAll(deliveryData.getData());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void newDeliveryBoy() {
        Bundle bundle = new Bundle();
        getDashboardActivity().addFragmentInContainer(new AssignNewDeliveryFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void onRatingClick(int position) {
        Bundle bundle = new Bundle();
        CommonUtility.showRatingDialog(getDashboardActivity(), bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedback) {
        getDashboardActivity().showToast("Rating Submitted");
    }

    @Override
    public void headerChangedCalled() {

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

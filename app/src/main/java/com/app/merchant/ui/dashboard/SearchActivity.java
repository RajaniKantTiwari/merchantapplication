package com.app.merchant.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivitySearchBinding;
import com.app.merchant.event.UserEvent;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.request.CustomerPhoneRequest;
import com.app.merchant.network.response.UserSearchResponse;
import com.app.merchant.network.response.UserSearchResponseData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.SimpleDividerItemDecoration;
import com.app.merchant.ui.authentication.CommonActivity;
import com.app.merchant.ui.dashboard.adapter.SearchAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SearchActivity extends CommonActivity implements
        SearchAdapter.SearchListener {
    @Inject
    CommonPresenter presenter;
    private String search;
    private ActivitySearchBinding mBinding;
    private SearchAdapter mSearchAdapter;
    private ArrayList<UserSearchResponse> userResponseList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        CommonUtility.register(this);
        initializeAdapter();
        initializeData();
        setListener();
    }

    private void initializeAdapter() {
        // set search list
        userResponseList = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(this, userResponseList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvSearch.setLayoutManager(layoutManager);
        mBinding.rvSearch.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvSearch.setAdapter(mSearchAdapter);
        //end
    }

    public void initializeData() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.layoutHeader.edSearch.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence searchText, int start, int before, int count) {
                search = searchText.toString();
                if (searchText.toString().length() == 0) {
                    showDefault();

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.progressBar.setVisibility(View.VISIBLE);
                            presenter.searchCustomerByPhone(SearchActivity.this, new CustomerPhoneRequest(search));
                        }
                    }, AppConstants.API_SERVICE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showDefault() {
        mBinding.layoutMain.setVisibility(View.VISIBLE);
        mBinding.rvSearch.setVisibility(View.GONE);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        }
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.rvSearch.setVisibility(View.VISIBLE);
        mBinding.tvNoResult.setVisibility(View.GONE);
        mBinding.layoutMain.setVisibility(View.VISIBLE);

        if (CommonUtility.isNotNull(response) && response instanceof UserSearchResponseData) {
            UserSearchResponseData responseData = (UserSearchResponseData) response;
            if (CommonUtility.isNotNull(responseData)) {
                if (CommonUtility.isNotNull(responseData.getData()) && responseData.getData().size() > 0) {
                    userResponseList.clear();
                    userResponseList.addAll(responseData.getData());
                    mSearchAdapter.notifyDataSetChanged();
                } else {
                    mBinding.layoutMain.setVisibility(View.GONE);
                    mBinding.tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void onMobileClicked(int position) {
        if (CommonUtility.isNotNull(userResponseList) && userResponseList.size() > position) {
            UserSearchResponse userResponse = userResponseList.get(position);
            if (CommonUtility.isNotNull(userResponse)) {
                UserEvent userDetailEvent = new UserEvent(userResponse.getMobile());
                EventBus.getDefault().post(userDetailEvent);
                finish();
            }
        }
    }

    @Override
    public void goToDetails(int position) {
        if (CommonUtility.isNotNull(userResponseList) && userResponseList.size() > position) {
            UserSearchResponse userResponse = userResponseList.get(position);
            if (CommonUtility.isNotNull(userResponse)) {
                Bundle bundle = new Bundle();
                bundle.putString(BundleConstants.CUSTOMER_ID, String.valueOf(userResponse.getId()));
                ExplicitIntent.getsInstance().navigateTo(this, CustomerDetailActivity.class, bundle);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
    @Subscribe
    public void onUserMobileNumber(UserEvent event) {
        finish();
    }
}

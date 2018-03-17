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
import com.app.merchant.network.request.dashboard.UserSearchRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.UserResponse;
import com.app.merchant.network.response.UserResponseData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.SimpleDividerItemDecoration;
import com.app.merchant.ui.authentication.CommonActivity;
import com.app.merchant.ui.dashboard.adapter.SearchAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.EventBus;

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
    private ArrayList<UserResponse> merchantList;
    private UserResponse userResponse;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initializeAdapter();
        initializeData();
        setListener();
    }

    private void initializeAdapter() {
        // set search list
        merchantList = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(this, merchantList, this);
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
                            presenter.getUserListBySearch(SearchActivity.this, new UserSearchRequest(search));
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

        if (CommonUtility.isNotNull(response) && response instanceof UserResponseData) {
            UserResponseData responseData = (UserResponseData) response;
            if (CommonUtility.isNotNull(responseData)) {
                if (CommonUtility.isNotNull(responseData.getData()) && responseData.getData().size() > 0) {
                    merchantList.clear();
                    merchantList.addAll(responseData.getData());
                    mSearchAdapter.notifyDataSetChanged();
                } else {
                    mBinding.layoutMain.setVisibility(View.GONE);
                    mBinding.tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void onSearchItemClicked(int position) {
        if (CommonUtility.isNotNull(merchantList) && merchantList.size() > position) {
            userResponse = merchantList.get(position);
            if (CommonUtility.isNotNull(userResponse)) {
                    gotoProductDetails();
            }
        }
    }

    private void gotoProductDetails() {
        UserEvent userDetailEvent = new UserEvent(userResponse.getMobileNumber());
        EventBus.getDefault().post(userDetailEvent);
        finish();
    }
}

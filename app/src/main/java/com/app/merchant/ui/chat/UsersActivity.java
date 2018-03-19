package com.app.merchant.ui.chat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.merchant.R;
import com.app.merchant.databinding.ActivityUsersBinding;
import com.app.merchant.network.request.UsersData;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UsersActivity extends BaseActivity implements UserAdapter.UsersListener {

    private ActivityUsersBinding mBinding;
    ArrayList<UsersData> userList = new ArrayList<>();
    int totalUsers = 0;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        initializeUserView();
        setListener();
        callApi();
    }

    private void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    private void initializeUserView() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.user));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvUsersList.setLayoutManager(layoutManager);
        mAdapter = new UserAdapter(this, userList, this);
        mBinding.rvUsersList.setAdapter(mAdapter);
    }

    private void callApi() {
        showProgress();
        String url = AppConstants.FIREBASE_BASE_URL + AppConstants.FIREBASE_USER;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String userJson) {
                doOnSuccess(userJson);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(UsersActivity.this);
        rQueue.add(request);
    }

    public void doOnSuccess(String userJson) {
        try {
            userList.clear();
            JSONObject obj = new JSONObject(userJson);
            Iterator i = obj.keys();
            String key = "";
            String userName = "";
            while (i.hasNext()) {
                key = i.next().toString();
                JSONObject object = obj.getJSONObject(key);
                if (CommonUtility.isNotNull(object)) {
                    userName = object.getString("password");
                }
                if (!key.equals(PreferenceUtils.getUserMono())) {
                    UsersData usersData = new UsersData();
                    usersData.setMobileNumber(key);
                    usersData.setUserName(userName);
                    userList.add(usersData);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers <= 1) {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.tvNoData.setText(getResources().getString(R.string.no_user_found));
            mBinding.rvUsersList.setVisibility(View.GONE);
        } else {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.GONE);
            mBinding.rvUsersList.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        hideProgress();
    }

    @Override
    public void onUsersClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.CHAT_WITH, userList.get(position).getMobileNumber());
        bundle.putString(AppConstants.CHAT_USER_NAME, userList.get(position).getUserName());
        ExplicitIntent.getsInstance().navigateTo(this, ChatActivity.class, bundle);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
       if(view==mBinding.layoutHeader.ivBack){
           finish();
       }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
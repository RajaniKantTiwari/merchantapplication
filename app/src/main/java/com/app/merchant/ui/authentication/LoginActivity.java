package com.app.merchant.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.merchant.R;
import com.app.merchant.databinding.ActivityLoginBinding;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class LoginActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        Firebase.setAndroidContext(this);
        mBinding.edEmail.setText(PreferenceUtils.getEmail());
        mBinding.edEmail.setSelection(mBinding.edEmail.getText().length());
        setListener();
    }

    public void setListener() {
        mBinding.forgotPassword.setOnClickListener(this);
        mBinding.tvLogin.setOnClickListener(this);
        mBinding.tvSignupForAccount.setOnClickListener(this);
        mBinding.edPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (isValid()) {
                        if (isNetworkConnected()) {
                            presenter.loginMerchant(LoginActivity.this, new LoginRequest(email, password));
                        }
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponseData) {
                LoginResponseData loginResponse = (LoginResponseData) response;
                if (isNotNull(loginResponse)) {
                    if (loginResponse.getStatus().equals(AppConstants.SUCCESS)) {
                        if(CommonUtility.isNotNull(loginResponse.getAuthkey())){
                            PreferenceUtils.setMerchantId(loginResponse.getAuthkey().getMerchantid());
                            PreferenceUtils.setAuthToken(loginResponse.getAuthkey().getAuth_key());
                            PreferenceUtils.setEmail(email);
                            PreferenceUtils.setLogin(true);
                            ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, DashBoardActivity.class);
                            finish();
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvLogin) {
            CommonUtility.clicked(mBinding.tvLogin);
            if (isValid()) {
                if (isNetworkConnected()) {
                    presenter.loginMerchant(this, new LoginRequest(email, password));
                    loginOnFireBase();
                }
            }
        } else if (view == mBinding.tvSignupForAccount) {
            CommonUtility.clicked(mBinding.tvSignupForAccount);
            ExplicitIntent.getsInstance().navigateTo(this, RegisterActivity.class);
        } else if (view == mBinding.forgotPassword) {
            CommonUtility.clicked(mBinding.forgotPassword);
            ExplicitIntent.getsInstance().navigateTo(this, ForgotPasswordActivity.class);
        }
    }

    private boolean isValid() {
        password = mBinding.edPassword.getText().toString();
        email = mBinding.edEmail.getText().toString();
        if (isNull(email) || email.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_email_address));
            return false;
        } else if (!CommonUtility.checkValidEmail(email)) {
            showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        } else if (isNull(password) || password.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_password));
            return false;
        }else if (isNull(password) || password.trim().length() <8) {
            showToast(getResources().getString(R.string.password_length_should_be_atleast_eight_character));
            mBinding.edPassword.requestFocus();
            return false;
        }
        return true;
    }
    private void loginOnFireBase() {

        String url = AppConstants.FIREBASE_BASE_URL+"/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase(AppConstants.FIREBASE_BASE_URL+"/users");

                if(s.equals("null")) {
                    reference.child(PreferenceUtils.getUserMono()).child("password").setValue(PreferenceUtils.getUserName());
                    //Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();

                }
                else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(PreferenceUtils.getUserMono())) {
                            reference.child(PreferenceUtils.getUserMono()).child("password").setValue(PreferenceUtils.getUserName());
                            //Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(LoginActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError );
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);
    }

}

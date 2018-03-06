package com.app.merchant.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;


import com.app.merchant.R;
import com.app.merchant.MerchantApplication;
import com.app.merchant.databinding.ActivityDashboardBinding;
import com.app.merchant.event.ProfileEvent;
import com.app.merchant.injector.component.DaggerDashboardComponent;
import com.app.merchant.injector.component.DashboardComponent;
import com.app.merchant.injector.module.DashboardModule;
import com.app.merchant.network.request.DeviceToken;
import com.app.merchant.network.request.DeviceTokenRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.authentication.EditProfileActivity;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.adapter.DrawerAdapterLeft;
import com.app.merchant.ui.dashboard.drawer.HelpActivity;
import com.app.merchant.ui.dashboard.home.AllPerformanceFragment;
import com.app.merchant.ui.dashboard.home.OrderReceivedFragment;
import com.app.merchant.ui.dashboard.home.WelcomeHomeFragment;
import com.app.merchant.ui.dashboard.drawer.InsuranceActivity;
import com.app.merchant.ui.dashboard.drawer.SeniorCitizenActivity;
import com.app.merchant.ui.dashboard.drawer.TermConditionActivity;
import com.app.merchant.ui.dashboard.drawer.WarantyActivity;
import com.app.merchant.ui.dashboard.notification.NotificationFragment;
import com.app.merchant.ui.dashboard.offer.OfferFragment;
import com.app.merchant.ui.dashboard.user.UserProfileFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import static com.app.merchant.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.OFFER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.USER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.WELCOME_HOME_FRAGMENT;


public class DashBoardActivity extends BaseActivity implements DrawerAdapterLeft.DrawerLeftListener {
    private static String TAG = DashBoardActivity.class.getSimpleName();
    //Better convention to properly name the indices what they are in your app

    private ActivityDashboardBinding mBinding;
    public DashboardComponent mDashboardComponent;
    @Inject
    public DashboardPresenter mPresenter;

    private ActionBarDrawerToggle mDrawerToggleLeft;
    private DrawerAdapterLeft mDrawerAdapterLeft;

    @Override
    public void onLeftDrawerItemClicked(int position) {
        closeDrawerLeft();
        switch (position) {
            case AppConstants.HOME:
                openFragment(new WelcomeHomeFragment(), null, false, false, NONE);
                break;
            case AppConstants.MYWARANTY:
                ExplicitIntent.getsInstance().navigateTo(this, WarantyActivity.class);
                break;
            case AppConstants.INSURANCE:
                ExplicitIntent.getsInstance().navigateTo(this, InsuranceActivity.class);
                break;
            case AppConstants.EDIT_PROFILE:
                ExplicitIntent.getsInstance().navigateTo(this, EditProfileActivity.class);
                break;
            case AppConstants.HELP:
                ExplicitIntent.getsInstance().navigateTo(this, HelpActivity.class);

                changeIcon(NOTIFICATION_FRAGMENT);
                openFragment(new NotificationFragment(), null, false, false, NONE);
                break;
            case AppConstants.SENIOR_CITIZEN:
                ExplicitIntent.getsInstance().navigateTo(this, SeniorCitizenActivity.class);

                break;
            case AppConstants.TERM_CONDITION:
                ExplicitIntent.getsInstance().navigateTo(this, TermConditionActivity.class);
                break;

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        CommonUtility.register(this);
        clearAllBackStack();
        pushFragment(new WelcomeHomeFragment(), null, R.id.container, true, false, NONE);
        hideSoftKeyboard(mBinding.getRoot());
        initDashboardComponent();
        attachView();
        setupDrawerToggleLeft();
        initializeData();
        setListener();
    }

    private void initDashboardComponent() {
        mDashboardComponent = DaggerDashboardComponent.builder().
                dashboardModule(new DashboardModule(this)).
                applicationComponent(((MerchantApplication) getApplication()).
                        getApplicationComponent()).build();

    }

    public DashboardComponent getActivityComponent() {
        return mDashboardComponent;
    }

    public void onTabSelected(int position) {
        changeIcon(position);
        switch (position) {
            case WELCOME_HOME_FRAGMENT:
                openFragment(new WelcomeHomeFragment(), null, false, false, NONE);
                break;
            case OFFER_FRAGMENT:
               // openFragment(new OfferFragment(), null, false, false, NONE);
                openFragment(new AllPerformanceFragment(), null, false, false, NONE);
                break;
            case NOTIFICATION_FRAGMENT:
                openFragment(new NotificationFragment(), null, false, false, NONE);
                break;
            case USER_FRAGMENT:
                openFragment(new UserProfileFragment(), null, false, false, NONE);
                break;
        }
    }

    private void openFragment(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        pushFragment(fragment, bundle, R.id.container, addToBackStack, shouldAdd, animationType);
        clearAllBackStack();
    }

    public void addFragmentInContainer(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        pushFragment(fragment, bundle, R.id.container, addToBackStack, shouldAdd, animationType);
    }

    private void changeIcon(int position) {
        for (int i = 0; i < AppConstants.NO_OF_TAB; i++) {
            switch (i) {
                case 0:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar1.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar1.setImageResource(R.drawable.ic_home);
                    } else {
                        mBinding.bottomLayout.viewBar1.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar1.setImageResource(R.drawable.ic_home_light);
                    }

                    break;
                case 1:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar2.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar2.setImageResource(R.drawable.ic_gift);
                    } else {
                        mBinding.bottomLayout.viewBar2.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar2.setImageResource(R.drawable.ic_gift_light);
                    }
                    break;
                case 2:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar3.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar3.setImageResource(R.drawable.ic_notification);
                    } else {
                        mBinding.bottomLayout.viewBar3.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar3.setImageResource(R.drawable.ic_notification_light);
                    }
                    break;
                case 3:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar4.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar4.setImageResource(R.drawable.ic_user);
                    } else {
                        mBinding.bottomLayout.viewBar4.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar4.setImageResource(R.drawable.ic_user_light);
                    }
                    break;
            }
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == AppConstants.DEVICE_TOKEN_RESPONSE) {
            LogUtils.LOGE(TAG, response.getMsg());
        } else if (requestCode == AppConstants.LOGOUT) {
            if (CommonUtility.isNotNull(response)) {
                showToast(response.getMsg());
                CommonUtility.logout(this);
            }
        }
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
    }

    public void initializeData() {
        mBinding.layoutDrawerLeft.tvName.setText(PreferenceUtils.getUserName());
        mBinding.layoutDrawerLeft.tvMobile.setText(PreferenceUtils.getUserMono());
        DeviceTokenRequest request = new DeviceTokenRequest();
        request.setUserid(PreferenceUtils.getUserId());
        DeviceToken token = new DeviceToken();
        token.setDeveiceUniqId(CommonUtility.getDeviceUniqueId(this));
        token.setDeviceTokenId(PreferenceUtils.getDeviceToken());
        token.setDeviceType(AppConstants.DEVICETYPE);
        request.setInfo(token);
        //mPresenter.setDeviceToken(this, request);
    }

    public void setListener() {
        mBinding.toolBar.ivSearch.setOnClickListener(this);
        mBinding.toolBar.ivDrawer.setOnClickListener(this);
        mBinding.layoutDrawerLeft.layoutLogout.setOnClickListener(this);
        mBinding.layoutDrawerLeft.ivProfile.setOnClickListener(this);
        mBinding.layoutDrawerLeft.ivUpdate.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar1.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar2.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar3.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar4.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (mBinding.toolBar.ivDrawer == view) {
            openDrawerLeft();
        } else if (mBinding.layoutDrawerLeft.layoutLogout == view) {
            //mPresenter.logout(this);
        } else if (mBinding.layoutDrawerLeft.ivProfile == view) {
            closeDrawerLeft();
            changeIcon(USER_FRAGMENT);
            onTabSelected(USER_FRAGMENT);
        } else if (mBinding.layoutDrawerLeft.ivUpdate == view) {
            closeDrawerLeft();
            ExplicitIntent.getsInstance().navigateTo(this, EditProfileActivity.class);
        }else if (view == mBinding.bottomLayout.linearLayoutBar1) {
            changeIcon(WELCOME_HOME_FRAGMENT);
            clearAllBackStack();
            pushFragment(new WelcomeHomeFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar2) {
            changeIcon(OFFER_FRAGMENT);
            clearAllBackStack();

            pushFragment(new AllPerformanceFragment(), null, R.id.container, true, false, NONE);

            //pushFragment(new OfferFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar3) {
            changeIcon(NOTIFICATION_FRAGMENT);
            clearAllBackStack();
            /*pushFragment(new NotificationFragment(), null, R.id.container, true, false, NONE);*/
            pushFragment(new OrderReceivedFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar4) {
            changeIcon(USER_FRAGMENT);
            clearAllBackStack();
            pushFragment(new UserProfileFragment(), null, R.id.container, true, false, NONE);
        }
    }
    void setupDrawerToggleLeft() {

        mDrawerToggleLeft = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggleLeft.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggleLeft);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawerLeft.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        mDrawerAdapterLeft = new DrawerAdapterLeft(this, this);
        mBinding.layoutDrawerLeft.rvDrawer.setLayoutManager(layoutManager);
        mBinding.layoutDrawerLeft.rvDrawer.setAdapter(mDrawerAdapterLeft);
    }

    private void openDrawerLeft() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.openDrawer(Gravity.LEFT);
        }
    }


    private void closeDrawerLeft() {
        if (mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.closeDrawers();
        }
    }



    public void setHeaderTitle(String title) {
        mBinding.toolBar.tvHeading.setText(title);
    }

    public void setHeader(String address, String imageUrl, String bgColor) {
        if (CommonUtility.isNotNull(bgColor)) {
            mBinding.toolBar.toolbar.setBackgroundColor(Color.parseColor(bgColor));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
    @Subscribe
    public void onUpdateProfileEvent(ProfileEvent event) {
       /* GlideUtils.loadImageProfilePic(this, PreferenceUtils.getImage(), mBinding.layoutDrawerLeft.ivProfile, null, R.drawable.avatar);
        mBinding.layoutDrawerLeft.tvName.setText(PreferenceUtils.getUserName());
        mBinding.layoutDrawerLeft.tvMobile.setText(PreferenceUtils.getUserMono());*/
    }
}

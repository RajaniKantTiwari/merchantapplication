package com.app.merchant.ui.dashboard.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.app.merchant.R;
import com.app.merchant.databinding.CartRowItemBinding;
import com.app.merchant.databinding.FragmentCartBinding;
import com.app.merchant.event.UpdateCartEvent;
import com.app.merchant.network.request.dashboard.cart.Cart;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CartRequest;
import com.app.merchant.network.request.dashboard.cart.DeleteCartRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.ui.SimpleDividerItemDecoration;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.cart.adapter.CartRowAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.app.merchant.ui.base.BaseActivity.AnimationType.NONE;


/**
 * Created by rajnikant on 31/12/17.
 */

public class CartFragment extends DashboardFragment implements CartRowAdapter.OnAddToCart {
    private FragmentCartBinding mBinding;
    private CartRowAdapter mAdapter;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private ArrayList<Product> mCartList;
    private CartRequest cartRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mCartList = PreferenceUtils.getCartData();
        mAdapter = new CartRowAdapter(getBaseActivity(), mCartList, this);
        mBinding.rvCartList.setLayoutManager(layoutManager);
        mBinding.rvCartList.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvCartList.setAdapter(mAdapter);
        setTotalAmount();
    }

    @Override
    public void initializeData() {
        cartRequest = new CartRequest();
        getPresenter().addToCart(getDashboardActivity(), cartRequest);

    }

    @Override
    public void setListener() {
        mBinding.tvCheckoutNow.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return CartFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCheckoutNow) {
            CommonUtility.clicked(mBinding.tvCheckoutNow);
            if(CommonUtility.isNotNull(PreferenceUtils.getCartData())&&PreferenceUtils.getCartData().size()>0){
                addToCartList();
            }else {
                getDashboardActivity().showToast(getResources().getString(R.string.please_add_data_in_cart_first));
            }
        }
    }
    private void addToCartList() {
        if (CommonUtility.isNotNull(PreferenceUtils.getCartData()) &&
                CommonUtility.isNotNull(PreferenceUtils.getCartData().size())
                && PreferenceUtils.getCartData().size() > 0) {
            //request for cart
            CartListRequest request = new CartListRequest();
            //list of product added in cart
            ArrayList<Cart> cartList = new ArrayList<>();
            ArrayList<Product> productList = PreferenceUtils.getCartData();
            request.setMerchant_id(productList.get(0).getMerchantId());
            //id of merchant
            for (Product product : productList) {
                if (CommonUtility.isNotNull(product)) {
                    Cart cart = new Cart();
                    cart.setMerchantlist_id(product.getMerchantlistid());
                    cart.setMasterproductid(product.getMasterproductid());
                    cart.setQty(product.getQty());
                    cartList.add(cart);
                }
            }
            request.setCart(cartList);
            getPresenter().addForCartList(getDashboardActivity(), request,this);
        }
    }
    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(requestCode== AppConstants.CARTADDED){
            getDashboardActivity().addFragmentInContainer(new CheckoutFragment(), null, true, true, NONE);
        } else if (CommonUtility.isNotNull(response) && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
            if (CommonUtility.isNotNull(mCartList) && mCartList.size() > requestCode) {
                mCartList.remove(requestCode);
                setCartData();
            }
        }
    }


    @Override
    public void addToCartClick(int pos, View view) {
        CartRowItemBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.ivAdd:
                addToCart(viewBinding.tvQuantity, pos);
                break;
            case R.id.ivSub:
                removeFromCart(viewBinding.tvQuantity, pos);
                break;
            case R.id.ivDeleteCart:
                if (CommonUtility.isNotNull(mCartList) && mCartList.size() > pos) {
                    getPresenter().deleteFromCart(getDashboardActivity(), new DeleteCartRequest(mCartList.get(0).getMerchantId(), mCartList.get(pos).getMasterproductid()), pos);
                }
               /* if (CommonUtility.isNotNull(mCartList) && mCartList.size() > pos) {
                    mCartList.remove(pos);
                    setCartData();
                }*/
                break;
        }
    }

    private void addToCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));
            mCartList.get(pos).setQty(count);
            setCartData();
            setTotalAmount();
        } else {
            Toast.makeText(getDashboardActivity(), getResources().getString(R.string.you_can_not_add), Toast.LENGTH_SHORT).show();
        }

    }

    private void setCartData() {
        PreferenceUtils.setCartData(mCartList);
        EventBus.getDefault().post(new UpdateCartEvent());
        mAdapter.notifyDataSetChanged();
    }

    private void removeFromCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > MIN_LIMIT) {
            count--;
            textView.setText(String.valueOf(count));
            mCartList.get(pos).setQty(count);
            setTotalAmount();
            if (count == 0) {
                mCartList.remove(pos);
            }
            setCartData();
        }
    }


    private void setTotalAmount() {
        float total = 0.0f;
        EventBus.getDefault().post(new UpdateCartEvent());
        for (Product data : mCartList) {
            total += data.getQty() * data.getProduct_mrp();
        }
        mBinding.tvTotal.setText(String.valueOf(total));
    }

    @Override
    public void headerChangedCalled() {

    }
}

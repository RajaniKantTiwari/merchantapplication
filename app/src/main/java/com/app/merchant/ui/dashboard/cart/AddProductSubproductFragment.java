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
import com.app.merchant.databinding.FragmentAddProductSubproductBinding;
import com.app.merchant.databinding.ItemCartBinding;
import com.app.merchant.event.ProductUpdateEvent;
import com.app.merchant.event.UserEvent;
import com.app.merchant.network.request.dashboard.cart.Cart;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CategorySubCatRequest;
import com.app.merchant.network.request.dashboard.cart.MerchantProductListRequest;
import com.app.merchant.network.request.dashboard.cart.SubCatProductRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.cart.Category;
import com.app.merchant.network.response.dashboard.cart.CategoryData;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.network.response.dashboard.cart.SubCategory;
import com.app.merchant.network.response.dashboard.cart.SubCategoryData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.SearchActivity;
import com.app.merchant.ui.dashboard.cart.adapter.CartAdapter;
import com.app.merchant.ui.dashboard.cart.adapter.CategoryAdapter;
import com.app.merchant.ui.dashboard.cart.adapter.SubCatAdapter;
import com.app.merchant.ui.dashboard.home.FullInformationFragment;
import com.app.merchant.ui.dashboard.home.OrderInventoryFragment;
import com.app.merchant.ui.dialogfrag.AddInventoryDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.app.merchant.ui.base.BaseActivity.AnimationType.NONE;


public class AddProductSubproductFragment extends DashboardFragment implements
        CartAdapter.OnAddToCart, CategoryAdapter.OnCatItemClick,
        SubCatAdapter.OnSubCatItemClick, AddInventoryDialogFragment.InventoryDialogListener {
    private FragmentAddProductSubproductBinding mBinding;
    private CategoryAdapter mCategoryAdapter;
    private SubCatAdapter mSubCategoryAdapter;
    private LinearLayoutManager mLayoutManager, mLayoutMangerSubcat, mLayoutManagerCart;
    private CartAdapter mCartAdapter;
    private int oldCatPos, oldSubCatPos;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private ArrayList<Category> mCatList = new ArrayList<>();
    private ArrayList<SubCategory> mSubCatList = new ArrayList<>();
    private ArrayList<Product> mProductList = new ArrayList<>();
    //private ArrayList<Product> addCartList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product_subproduct, container, false);
        CommonUtility.register(this);
      /*  if (CommonUtility.isNotNull(PreferenceUtils.getCartData()) && PreferenceUtils.getCartData().size() > 0) {
            addCartList = PreferenceUtils.getCartData();
        } else {
            addCartList = new ArrayList<>();
        }*/
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.add_product_in_inventory));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            getDashboardActivity().setHeader(bundle.getString(AppConstants.MERCHANT_ADDRESS),
                    bundle.getString(AppConstants.MERCHANT_IMAGE), bundle.getString(AppConstants.MERCHANT_BACKGROUND_COLOR));
        }
        setViews();
        callApi();
    }

    private void setViews() {
        mLayoutManager = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutMangerSubcat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerCart = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.VERTICAL, false);
        mBinding.rvCat.setLayoutManager(mLayoutManager);
        mBinding.rvSubCat.setLayoutManager(mLayoutMangerSubcat);
        mBinding.rvDetail.setLayoutManager(mLayoutManagerCart);
        mBinding.tvMyInventory.setOnClickListener(this);
        mCategoryAdapter = new CategoryAdapter(mCatList, this);
        mSubCategoryAdapter = new SubCatAdapter(mSubCatList, this);
        mCartAdapter = new CartAdapter(mProductList, this);
        mBinding.rvCat.setAdapter(mCategoryAdapter);
        mBinding.rvSubCat.setAdapter(mSubCategoryAdapter);
        mBinding.rvDetail.setAdapter(mCartAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAddMerchantList:
                MerchantProductListRequest request = new MerchantProductListRequest();
                setRequest(request);
                getPresenter().addProductToMerchantList(getDashboardActivity(), request);
            /*case R.id.tvCheckout:*/
               /* CommonUtility.clicked(mBinding.tvCheckout);
                if (mBinding.tvMobile.getText().toString() == null || mBinding.tvMobile.getText().toString().trim().length() == 0) {
                    getDashboardActivity().showToast(getResources().getString(R.string.please_enter_customer_mobile_number));
                    return;
                }
                if (CommonUtility.isNotNull(PreferenceUtils.getCartData()) && PreferenceUtils.getCartData().size() > 0) {
                    addToCartList();
                } else {
                    getDashboardActivity().showToast(getResources().getString(R.string.please_add_data_in_cart_first));
                }*/

                break;
            case R.id.tvMyInventory:
                CommonUtility.clicked(mBinding.tvMyInventory);
                Bundle bundle = new Bundle();
                getDashboardActivity().addFragmentInContainer(new OrderInventoryFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
                break;
            case R.id.tvCustomer:
                CommonUtility.clicked(mBinding.tvMyInventory);
                getDashboardActivity().addFragmentInContainer(new AddNewCustomerFragment(), null, true, true, BaseActivity.AnimationType.NONE);
                break;
            case R.id.layoutCustomer:
                ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), SearchActivity.class);
                break;
        }

    }

    private void setRequest(MerchantProductListRequest request) {
        request.setMerchant_id(PreferenceUtils.getMerchantId());
        request.setCategory(mCatList.get(oldCatPos).getId());
        request.setSubcategory(mSubCatList.get(oldSubCatPos).getId());


    }

   /* private boolean isValid() {
        if (mBinding.edMobile.getText().toString() == null || mBinding.edMobile.getText().toString().trim().length() == 0) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_mobile_number));
            return false;
        } else if (mBinding.edMobile.getText().toString().trim().length() < 10) {
            getDashboardActivity().showToast(getResources().getString(R.string.please_enter_valid_mobilenumber));
            return false;
        }
        return true;
    }*/

   /* private void addToCartList() {
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
            getPresenter().addForCartList(getDashboardActivity(), request, this);
        }
    }*/

    private void addToCart(TextView textView, int pos) {
        boolean isNotAdded = true;
        int count = Integer.parseInt(textView.getText().toString());
        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));
        } else {
            Toast.makeText(getDashboardActivity(), getResources().getString(R.string.you_can_not_add), Toast.LENGTH_SHORT).show();
        }
        mProductList.get(pos).setQty(count);
        /*for (int i = 0; i < addCartList.size(); i++) {
            if (mProductList.get(pos).getMerchantlistid() == addCartList.get(i).getMerchantlistid()) {
                isNotAdded = false;
                addCartList.set(i, mProductList.get(pos));
            }
        }
        if (isNotAdded) {
            addCartList.add(mProductList.get(pos));
        }*/
        //setCartData();
        setTotalAmount();
    }

    private void removeFromCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > MIN_LIMIT) {
            count--;
            textView.setText(String.valueOf(count));
        } else {
            count = 0;
            Toast.makeText(getDashboardActivity(), "Your cart is already empty.", Toast.LENGTH_SHORT).show();
        }
        mProductList.get(pos).setQty(count);
       /* for (int i = 0; i < addCartList.size(); i++) {
            if (mProductList.get(pos).getMerchantlistid() == addCartList.get(i).getMerchantlistid()) {
                if (mProductList.get(pos).getQty() == 0) {
                    addCartList.remove(i);
                } else {
                    addCartList.set(i, mProductList.get(pos));
                }
            }
        }*/
        //setCartData();
        setTotalAmount();

    }

   /* private void setCartData() {
        if (CommonUtility.isNotNull(addCartList)&&addCartList.size() > 0) {
            for (int i = 0; i < addCartList.size(); i++) {
                Product product = addCartList.get(i);
                product.setMerchantId(PreferenceUtils.getMerchantId());
                addCartList.set(i, product);
            }
        }
        *//*PreferenceUtils.setCartData(addCartList);*//*
    }*/

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.CARTADDED) {
                getDashboardActivity().addFragmentInContainer(new CheckoutFragment(), null, true, true, NONE);
            } else if (requestCode == 1) {
                categoryResponse(response);
            } else if (requestCode == 2) {
                subCategoryResponse(response);
            } else if (requestCode == 3) {
                productResponse(response);
            } else if (requestCode == 4) {

            }
        }
    }

    private void productResponse(BaseResponse response) {
        if (response instanceof ProductData) {
            ProductData categoryResponse = (ProductData) response;
            ArrayList<Product> productList = categoryResponse.getInfo();
            if (CommonUtility.isNotNull(productList) && productList.size() > 0) {
                mProductList.clear();
                mProductList.addAll(productList);
                mCartAdapter.notifyDataSetChanged();
            }
        }
    }

    private void subCategoryResponse(BaseResponse response) {
        if (response instanceof SubCategoryData) {
            SubCategoryData categoryResponse = (SubCategoryData) response;
            ArrayList<SubCategory> subCatList = categoryResponse.getInfo();
            if (CommonUtility.isNotNull(subCatList) && subCatList.size() > 0) {
                mSubCatList.clear();
                mSubCatList.addAll(subCatList);
                mSubCatList.get(0).setSelected(true);
                mSubCategoryAdapter.notifyDataSetChanged();
            }
        }
    }

    private void categoryResponse(BaseResponse response) {
        if (response instanceof CategoryData) {
            CategoryData categoryResponse = (CategoryData) response;
            CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
            ArrayList<Category> categoryList = categoryResponse.getInfo();
            if (CommonUtility.isNotNull(categoryList) && categoryList.size() > 0) {
                mCatList.clear();
                mCatList.addAll(categoryList);
                mCatList.get(0).setSelected(true);
                mCategoryAdapter.notifyDataSetChanged();
            } else {
                CommonUtility.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
            }
        }
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {
        /*mBinding.layoutCustomer.setOnClickListener(this);
        mBinding.tvCheckout.setOnClickListener(this);*/
        mBinding.tvAddMerchantList.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return AddProductSubproductFragment.class.getName();
    }


    @Override
    public void addToCartClick(int pos, View view) {
        ItemCartBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.ivPlus:
                addToCart(viewBinding.tvQty, pos);
                break;
            case R.id.ivMinus:
                removeFromCart(viewBinding.tvQty, pos);
                break;
            case R.id.layoutProduct:
            case R.id.layoutInfo:
                if (CommonUtility.isNotNull(mProductList) && mProductList.size() > pos) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(AppConstants.PRODUCT_DATA, mProductList.get(pos));
                    bundle.putInt(AppConstants.MERCHANT_ID, PreferenceUtils.getMerchantId());
                    bundle.putInt(AppConstants.POSITION, pos);
                    getDashboardActivity().addFragmentInContainer(new FullInformationFragment(), bundle, true, true, NONE);
                }
                break;
            case R.id.tvAddInventory:
                Bundle bundle = new Bundle();
                bundle.putString(BundleConstants.PRODUCT_NAME, mProductList.get(pos).getProductname());
                CommonUtility.showInventoryOrderDialog(getDashboardActivity(), bundle, this);
                break;
        }
    }

    private void setTotalAmount() {
        float total = 0.0f;
        /*EventBus.getDefault().post(new UpdateCartEvent());*/
       /* for (Product data : addCartList) {
            total += data.getQty() * data.getProduct_mrp();
        }*/
        //mBinding.tvTotal.setText(String.valueOf(total));
    }

    @Override
    public void onCatClick(int pos, View view) {
//        ItemCartBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();

        switch (itemView.getId()) {
            case R.id.tvName:
                mCatList.get(oldCatPos).setSelected(false);
                mCatList.get(pos).setSelected(true);
                mSubCatList.clear();
                mCategoryAdapter.notifyDataSetChanged();
                oldCatPos = pos;
                oldSubCatPos = 0;
                CategorySubCatRequest request = new CategorySubCatRequest();
                request.setCategory_id(mCatList.get(pos).getId());
                request.setMerchant_id(PreferenceUtils.getMerchantId());
                getPresenter().getProductSubCategory(getDashboardActivity(), request);
                break;
        }
    }

    @Override
    public void onSubCatClick(int pos, View view) {
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.tvName:
                mSubCatList.get(oldSubCatPos).setSelected(false);
                mSubCatList.get(pos).setSelected(true);
                mProductList.clear();
                mSubCategoryAdapter.notifyDataSetChanged();
                oldSubCatPos = pos;
                SubCatProductRequest request = new SubCatProductRequest();
                request.setCategory_id(mCatList.get(oldCatPos).getId());
                request.setMerchant_id(PreferenceUtils.getMerchantId());
                request.setSub_category_id(mSubCatList.get(pos).getId());
                getPresenter().getSubCategoryProduct(getDashboardActivity(), request);
                break;
        }
    }

    @Subscribe
    public void onUpdateProductData(ProductUpdateEvent event) {
        //addCartList = PreferenceUtils.getCartData();
        mProductList.set(event.getPosition(), event.getProductData());
        mCartAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onUserMobileNumber(UserEvent event) {
        //mBinding.tvUserName.setText(event.getName());
        //mBinding.tvMobile.setText(event.getMobileNumber());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }

    private void callApi() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setMerchant_id(PreferenceUtils.getMerchantId());
        getPresenter().getProductCategory(getDashboardActivity(), categoryRequest);
    }

    @Override
    public void headerChangedCalled() {
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.add_product_in_inventory));
    }

    @Override
    public void addInventoryClick(String noOfProduct) {

    }
}

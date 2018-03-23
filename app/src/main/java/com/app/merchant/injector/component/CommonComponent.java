package com.app.merchant.injector.component;



import com.app.merchant.injector.module.CommonModule;
import com.app.merchant.injector.scope.PerActivity;
import com.app.merchant.ui.authentication.EditProfileActivity;
import com.app.merchant.ui.authentication.ForgotPasswordActivity;
import com.app.merchant.ui.authentication.LoginActivity;
import com.app.merchant.ui.authentication.RegisterActivity;
import com.app.merchant.ui.authentication.StoreDetailsActivity;
import com.app.merchant.ui.authentication.VerifyAccountActivity;
import com.app.merchant.ui.dashboard.CustomerDetailActivity;
import com.app.merchant.ui.dashboard.SearchActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CommonModule.class)
public interface CommonComponent {
    void inject(LoginActivity activity);
    void inject(ForgotPasswordActivity activity);
    void inject(EditProfileActivity activity);
    void inject(VerifyAccountActivity activity);
    void inject(RegisterActivity activity);
    void inject(StoreDetailsActivity activity);
    void inject(SearchActivity activity);
    void inject(CustomerDetailActivity activity);
}

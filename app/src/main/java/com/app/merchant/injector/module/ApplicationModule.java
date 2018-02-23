package com.app.merchant.injector.module;

import com.app.merchant.MerchantApplication;
import com.app.merchant.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private MerchantApplication application;

    public ApplicationModule(MerchantApplication application) {
        this.application = application;
    }
    @Provides
    @PerApplication
    public MerchantApplication provideApplication() {
        return application;
    }
}

package com.app.merchant;

import android.support.multidex.MultiDexApplication;

import com.app.merchant.injector.component.ApplicationComponent;
import com.app.merchant.injector.component.DaggerApplicationComponent;
import com.app.merchant.injector.module.ApplicationModule;
import com.orhanobut.hawk.Hawk;


public class MerchantApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    public static boolean isDebug=true;
    @Override
    public void onCreate() {
        super.onCreate();
        setOrientation();
        Hawk.init(this).build();
        setUpInjector();
    }

    private void setUpInjector() {
        applicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();
    }

    private void setOrientation() {
        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

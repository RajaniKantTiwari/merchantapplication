package com.app.merchant.injector.component;


import com.app.merchant.injector.module.ApplicationModule;
import com.app.merchant.injector.module.NetworkModule;
import com.app.merchant.injector.scope.PerApplication;
import com.app.merchant.network.Repository;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Repository repository();
}

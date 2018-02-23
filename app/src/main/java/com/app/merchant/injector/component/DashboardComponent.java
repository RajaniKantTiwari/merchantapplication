package com.app.merchant.injector.component;




import com.app.merchant.injector.module.DashboardModule;
import com.app.merchant.injector.scope.PerActivity;
import com.app.merchant.ui.dashboard.DashBoardActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DashboardModule.class)
public interface DashboardComponent {

    void inject(DashBoardActivity dashBoardActivity);

}

package com.tae.connect2mongo201912.component;

import android.app.Application;

import com.tae.connect2mongo201912.App;
import com.tae.connect2mongo201912.module.MainActivityModule;
import com.tae.connect2mongo201912.module.SharedPrefModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, SharedPrefModule.class, MainActivityModule.class})
public interface MyComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MyComponent build();
    }
    void inject(App app);
}
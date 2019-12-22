package com.tae.connect2mongo201912;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tae.connect2mongo201912.component.DaggerMyComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    Context context;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        context=getApplicationContext();
        System.out.println("-------------------------------APP------------- #1. $$$$ APP contains this sharePreferences: "+sharedPreferences);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger(){
        DaggerMyComponent.builder().application(this).build().inject(this);
    }
}
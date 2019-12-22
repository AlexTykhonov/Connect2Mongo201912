package com.tae.connect2mongo201912.module;


import com.tae.connect2mongo201912.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();

}

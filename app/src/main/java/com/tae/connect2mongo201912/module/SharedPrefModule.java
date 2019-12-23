package com.tae.connect2mongo201912.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tae.connect2mongo201912.HeaderInterceptor;
import com.tae.connect2mongo201912.RetrofitClient;

import java.io.IOException;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Module
public class SharedPrefModule {
    private Context context;

    @Singleton
    @Provides
    public Context provideContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public HeaderInterceptor provideHeaderInterceptor (SharedPreferences sharedPreferences) {
        return new HeaderInterceptor(sharedPreferences);
    }

    @Singleton
    @Provides
    public RetrofitClient provideRetrofitClient (HeaderInterceptor headerInterceptor) {
        return new RetrofitClient(headerInterceptor);
    }

}

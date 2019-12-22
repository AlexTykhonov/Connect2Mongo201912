package com.tae.connect2mongo201912.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    public Interceptor provideHeaderIntereptor (SharedPreferences sharedPreferences) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder;
                if(original.url().equals("https://fluxjwt.herokuapp.com/authorize/login")){
                    requestBuilder = original.newBuilder();
                }
                else {
                    if (sharedPreferences!=null) {
                        requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer "+ sharedPreferences.getString("token", "no token"));
                    }
                    else requestBuilder = original.newBuilder();
                }


                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

//    @Singleton
//    @Provides
//    public Controller provideController (Interceptor interceptor) {
//        return new Controller(interceptor);
//    }

}


package com.tae.connect2mongo201912;

import android.content.SharedPreferences;
import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    SharedPreferences sharedPreferences;

    public HeaderInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        System.out.println(" -------------> SharedPreferences.getString(token): "+sharedPreferences.getString("token","no token"));

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Call from INTERCEPTOR method"+chain.request().tag());
        Request original = chain.request();
        Request.Builder requestBuilder;
        if(original.url().equals("https://fluxjwt.herokuapp.com/authorize/login")){
            requestBuilder = original.newBuilder();
          System.out.println(" !!!!!!!!!!!!!!!!!!!!!-------------> SharedPreferences.getString(token): "+sharedPreferences.getString("token","no token"));
        }
        else {
            requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer "+ sharedPreferences.getString("token", "no token"));
        }
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

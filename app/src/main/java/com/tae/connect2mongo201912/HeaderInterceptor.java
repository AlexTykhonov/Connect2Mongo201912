package com.tae.connect2mongo201912;

import android.content.SharedPreferences;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    SharedPreferences sharedPreferences;

    public HeaderInterceptor() { }

    public HeaderInterceptor(SharedPreferences sharedPreferences) {
        //    this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder requestBuilder;
        if(original.url().equals("https://fluxjwt.herokuapp.com/authorize/login")){
            requestBuilder = original.newBuilder();
        }
        else {
            requestBuilder = original.newBuilder();
//                    .header("Authorization", "Bearer "+ sharedPreferences.getString("token", "no token"));
        }
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

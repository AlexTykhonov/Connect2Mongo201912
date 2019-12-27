package com.tae.connect2mongo201912;

import com.tae.connect2mongo201912.Api.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;
    public static final String BASE_URL = "https://fluxjwt.herokuapp.com/";

    HeaderInterceptor headerInterceptor;

    public RetrofitClient(HeaderInterceptor headerInterceptor) {
        this.headerInterceptor = headerInterceptor;
        System.out.println("--------> HEADER INTERCEPTOR "+ headerInterceptor);
    }

    public  Retrofit callRetrofit() {
        if (retrofit == null) {

            OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();
            sHttpClient.addInterceptor(this.headerInterceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(sHttpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } return retrofit;
    }

}
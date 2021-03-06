package com.tae.connect2mongo201912.Api;

import com.tae.connect2mongo201912.model.Login;
import com.tae.connect2mongo201912.model.Moto;
import com.tae.connect2mongo201912.model.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("authorize/login")
    Observable<Token> login(@Body Login login);

    @GET("moto")
    Observable<ArrayList<Moto>> getMoto();

}

// вывести в консоль список мото
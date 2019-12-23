package com.tae.connect2mongo201912;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tae.connect2mongo201912.model.Login;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    String loginuser, password;

    @Inject
    RetrofitClient retrofitClient;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitClient.callApi().login(new Login("Andrew","password"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(token-> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token.getToken());
                    editor.commit();
                },
                        e-> System.out.println("------------------ ERROR is: "+e));
    }
}

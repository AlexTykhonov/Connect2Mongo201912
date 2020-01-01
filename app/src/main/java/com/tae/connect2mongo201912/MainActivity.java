package com.tae.connect2mongo201912;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tae.connect2mongo201912.Api.Api;
import com.tae.connect2mongo201912.model.Login;
import com.tae.connect2mongo201912.model.Moto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    String loginuser, password;

    List<Moto> listMoto;

    @Inject
    RetrofitClient retrofitClient;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitClient.callRetrofit().create(Api.class).login(new Login("Andrew","password"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(token-> {
                    System.out.println("=============  =========== FROM M.A. token "+token.getToken());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token.getToken());
                    editor.commit();
                }, e-> System.out.println("------------------ ERROR is: "+e));

        callMotos();

// button click for another activity
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               goMotoActivity();
            }
        });
    }

// call for motos - receive the list of motos and transfer it to motoActivity
    public void callMotos() {
        retrofitClient.callRetrofit().create(Api.class).getMoto()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(motos -> {
                    listMoto = motos;
//                    for (int i=0;i<listMoto.size();i++) {
//                        System.out.println(" %%%%%%%%%%%%%%%%%%%%%%%%%% Moto #"+i+"  is named: "+listMoto.get(i).getModel());
//                    }
                }, e -> System.out.println("------------------ ERROR is: " + e));
    }

    // intent for another activity
    public void goMotoActivity() {
        Intent intent = new Intent(this, MotoActivity.class);
        Bundle bundle = new Bundle();
        String[] strings = new String[listMoto.size()];
        for (int i = 0; i < listMoto.size(); i++) {
            strings[i] = listMoto.get(i).getModel().toString();
        }
        bundle.putStringArray("MOTOS", strings);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

// создать второе активити после поучения токена в переход во второе активити где выводим список мотоциклов
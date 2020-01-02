package com.tae.connect2mongo201912;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;


public class MotoActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moto_activity);
        RecyclerView recyclerView = findViewById(R.id.listMoto);

        String[] newStrings;
        String newString;
        List<String> motocycles;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newStrings = null;
            } else {
                newStrings = extras.getStringArray("MOTOS");
                System.out.println("THIS IS newStringS: "+newStrings);
                motocycles = convertStringsToList(newStrings);
                System.out.println("++++++++++++++++++++++++++++++++MOTORCYCLES SIZE FROM MOTO ACTIVITY "+ motocycles.size());
                System.out.println("++++++++++++++++++++++++++++++++MOTORCYCLES FROM MOTO ACTIVITY "+ motocycles);

                RecViewAdapter adapter = new RecViewAdapter(this, motocycles);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("MOTOS");
            System.out.println("THIS IS newString: "+newString);
        }
    }

    //STRINGS converter
    public List<String> convertStringsToList (String[] text) {
        List<String> chars = new ArrayList<>();
        for (int i=0;i<text.length;i++)
        {
          chars.add(i,text[i]);
        }
        return chars;
    }


}

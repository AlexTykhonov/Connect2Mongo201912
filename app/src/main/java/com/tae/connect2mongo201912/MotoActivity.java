package com.tae.connect2mongo201912;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moto_activity);

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

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listMoto);
                RecViewAdapter adapter = new RecViewAdapter(this, motocycles);
                recyclerView.setAdapter(adapter);
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

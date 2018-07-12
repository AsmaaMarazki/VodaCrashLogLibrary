package com.example.asmaamarazki.vodacrashloglibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new PlusOneFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2_container,new BlankFragment()).commit();

    }

}

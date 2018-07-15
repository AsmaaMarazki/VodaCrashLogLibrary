package com.example.asmaamarazki.vodacrashloglibrary;

import com.androidnetworking.AndroidNetworking;
import com.example.asmaamarazki.vodacrashloglibrary.lib.Vodalytics;
import com.example.asmaamarazki.vodacrashloglibrary.lib.network.NetworkManager;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        Vodalytics.with(this);
        new NetworkManager().sendCrashLogToServer();

    }





}

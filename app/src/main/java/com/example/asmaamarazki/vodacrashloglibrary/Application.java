package com.example.asmaamarazki.vodacrashloglibrary;

import com.androidnetworking.AndroidNetworking;
import com.example.asmaamarazki.vodacrashloglibrary.lib.Vodalytics;
import com.example.asmaamarazki.vodacrashloglibrary.lib.network.NetworkManager;

public class Application extends android.app.Application {

    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AndroidNetworking.initialize(getApplicationContext());
        Vodalytics.with(this);
        Vodalytics.log("20");
    }



    public static Application getApp() {
        return app;
    }



}

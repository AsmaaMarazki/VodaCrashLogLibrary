package com.example.asmaamarazki.vodacrashloglibrary.lib;

import android.app.Application;

public class Vodalytics {
    private static volatile Vodalytics vodalytics;
    private static Application application;

    private Vodalytics(){
        if(vodalytics !=null)
            throw new RuntimeException("Use getInstance() method to get the single instance of the class.");
    }

    /**
     * @param application
     * @since Application class
     */
    public static Vodalytics with(Application application){
        if (vodalytics==null) {
            synchronized (Vodalytics.class){
                if (vodalytics==null) {
                    vodalytics = new Vodalytics();
                    Vodalytics.application = application;
                }
            }
        }
        return vodalytics;
    }

    public static void log(Throwable throwable){

    }
    public static void log(String msg){

    }
    public static void log(String key,String msg){

    }


}


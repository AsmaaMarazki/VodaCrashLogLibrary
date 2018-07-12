package com.example.asmaamarazki.vodacrashloglibrary.lib;

public class Vodalytics {
    private static volatile Vodalytics vodalytics;

    private Vodalytics(){
        if(vodalytics !=null)
            throw new RuntimeException("Use getInstance() method to get the single instance of the class.");
    }

    public static Vodalytics getInstance(){
        if (vodalytics==null) {
            synchronized (Vodalytics.class){
                if (vodalytics==null)
                vodalytics = new Vodalytics();
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


package com.example.asmaamarazki.vodacrashloglibrary.lib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Vodalytics {
    private static volatile Vodalytics vodalytics;
    private static Application application;
    private ArrayList<String> screensOpend = new ArrayList<>();

    private Vodalytics(){
        if(vodalytics !=null)
            throw new RuntimeException("Use getInstance() method to get the single instance of the class.");


        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                addScreensOpend(activity.getClass().getSimpleName());
                //activity.getCallingActivity()

            }

            @Override
            public void onActivityResumed(Activity activity) {


                if(!((AppCompatActivity)activity).getSupportFragmentManager().getFragments().isEmpty()){
                    for(Fragment fragment : ((AppCompatActivity)activity).getSupportFragmentManager().getFragments()){
                        if(fragment != null)
                            addScreensOpend(fragment.getClass().getSimpleName());

                    }
                }

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    /**
     * @param application
     * @since Application class
     */
    public static Vodalytics with(Application application){
        if (vodalytics==null) {
            synchronized (Vodalytics.class){
                if (vodalytics==null) {
                    Vodalytics.application = application;
                    vodalytics = new Vodalytics();
                }
            }
        }

        return vodalytics;
    }


    public ArrayList<String> getScreensOpend() {
        return screensOpend;
    }

    public void addScreensOpend(String screenOpend) {
        this.screensOpend.add(screenOpend);
        Log.e("TAG", "addScreensOpend: " + screensOpend.toString() );
    }

    public static void log(Throwable throwable){

    }
    public static void log(String msg){

    }
    public static void log(String key,String msg){

    }


}


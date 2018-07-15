package com.example.asmaamarazki.vodacrashloglibrary.lib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;
import com.example.asmaamarazki.vodacrashloglibrary.lib.network.NetworkManager;

import java.util.ArrayList;

public class Vodalytics {
    private static volatile Vodalytics vodalytics;
    private static Application application;
    private ArrayList<String> screensOpened = new ArrayList<>();

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
            public void onActivityResumed(final Activity activity) {

                ((AppCompatActivity)activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                        super.onFragmentResumed(fm, f);
                        addScreensOpend(f.getClass().getSimpleName());
                        /*
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // get Data
                                DataSource dataSource = DataSource.getAppDataSource(activity);
                                Log.d("SHOW_DB", "onActivityResumed:" + dataSource.getErrorInfo().getAllErrors().toString());

                            }
                        });
                        */
                    }

                }, true);

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


    public ArrayList<String> getScreensOpened() {
        return screensOpened;
    }

    public void addScreensOpend(final String screenOpend) {
        this.screensOpened.add(screenOpend);

        Log.e("TAG", "addScreensOpend: " + screensOpened.toString() );
    }

    public static void log(Throwable throwable){

    }
    public static void log(String msg){
        ErrorInfo error = new ErrorInfo(msg);
        //SharedPreferences sharedPreferences = application.getSharedPreferences("TEMP", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("errCODE",error.getUuid()).apply();
        NetworkManager networkManager = new NetworkManager();
        networkManager.sendCrashLogToServer(error);

    }
    public static void log(String key,String msg){

    }


}


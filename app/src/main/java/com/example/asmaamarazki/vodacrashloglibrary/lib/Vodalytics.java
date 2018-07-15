package com.example.asmaamarazki.vodacrashloglibrary.lib;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asmaamarazki.vodacrashloglibrary.lib.database.DataSource;
import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.ElasticsData;

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


    public ArrayList<String> getScreensOpend() {
        return screensOpend;
    }

    public void addScreensOpend(final String screenOpend) {
        this.screensOpend.add(screenOpend);


        /*

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Insert Data
                DataSource.getAppDataSource(application).getErrorInfo().insertError(new ErrorInfo(screenOpend));

                   }
        });*/

        //DataSource dataSource = DataSource.getAppDataSource(application);
        //dataSource.getErrorInfo().insertError(new ErrorInfo(screenOpend));

        Log.e("TAG", "addScreensOpend: " + screensOpend.toString() );
    }

    public static void log(Throwable throwable){

    }
    public static void log(String msg){

    }
    public static void log(String key,String msg){

    }
    public static void log(ElasticsData elasticsData){}


}


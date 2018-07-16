package com.example.asmaamarazki.vodacrashloglibrary.lib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asmaamarazki.vodacrashloglibrary.lib.Utilities.Constants;
import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.DeviceData;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.ElasticsError;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.ElasticsMap;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.ErrorCodes;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.Journey;
import com.example.asmaamarazki.vodacrashloglibrary.lib.models.UserData;
import com.example.asmaamarazki.vodacrashloglibrary.lib.network.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Vodalytics {
    private static volatile Vodalytics vodalytics;
    private static Application application;
    private ArrayList<String> screensOpened = new ArrayList<>();
    private UserData userData;
    private DeviceData deviceData;
    private static NetworkManager networkManager;

    private Vodalytics() {
        if (vodalytics != null)
            throw new RuntimeException("Use getInstance() method to get the single instance of the class.");

        //initialize the Network manager
        networkManager = new NetworkManager();
        //set the listeners
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                addScreensOpened(activity.getClass().getSimpleName());
                //activity.getCallingActivity()

            }

            @Override
            public void onActivityResumed(final Activity activity) {
                ((AppCompatActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentTracker(), true);

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
    public static Vodalytics with(Application application) {
        if (vodalytics == null) {
            synchronized (Vodalytics.class) {
                if (vodalytics == null) {
                    Vodalytics.application = application;
                    vodalytics = new Vodalytics();
                }
            }
        }

        return vodalytics;
    }


    private ArrayList<String> getScreensOpened() {
        return screensOpened;
    }

    private void addScreensOpened(final String screenOpend) {
        this.screensOpened.add(screenOpend);

        Log.e("TAG", "addScreensOpend: " + screensOpened.toString());
    }

    public static void log(String msg) {
        ErrorInfo error = new ErrorInfo(msg);
        //SharedPreferences sharedPreferences = application.getSharedPreferences("TEMP", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("errCODE",error.getUuid()).apply();
        networkManager.sendCrashLogToServer(error);

    }

    public static void log(String key, HashMap<String, String> errorMap) {
        ElasticsMap elasticsMapError = with(application).initElasticsMap(errorMap);
        //networkManager.sendCrashLogToServer();


    }

    public static void log(Throwable throwable) {
        ElasticsError elasticsError = with(application).initElasticsError(throwable);
        //check connectivity then call network client or room

    }

    private void initElastics() {
        // set device data
       /*  deviceData = new DeviceData(NetworkUtils.isWifiConnected(application.getApplicationContext()),
                NetworkUtils.isMobileConnected(application.getApplicationContext(),
                        PhoneUtils.getDeviceName(),
                        PhoneUtils.getAndroidVersion()));

        // set user data
        ArrayList<String> otherAccounts=new ArrayList<>();
        for (int i=0;i<Configurations.getAllUsers().size();i++){
            otherAccounts.add(Configurations.getAllUsers().get(i).getUserAccountInfoModel().getEncryptMsisdn());
        }

        userData = new UserData(LoggedUser.getInstance().isSeamless(),LoggedUser.getInstance().getAccount().getEncryptMsisdn(), otherAccounts,
                LoggedUser.getInstance().getAccount().getRatePlanCode(), LoggedUser.getInstance().getAccount().getTariffModelName()
                ,LoggedUser.getInstance().getAccount().getAccountInfoRoles());*/

    }

    private ElasticsError initElasticsError(Throwable throwable) {
        //set elastics data
        initElastics();
        //default data for network error
        boolean isNetworkError = true;
        String rawResponse = "";
        int errorCode = -999;
        String errorMsg = "";

        //Business error
     /*   if (throwable instanceof MCareException) {
            isNetworkError=false;
            errorCode=(((MCareException) throwable).getErrorCode());
            errorMsg=ErrorCodeUtility.getErrorMessage(errorCode);
            rawResponse=((MCareBusinessException)throwable).getRawServerResponse();
        }
*/
        //set error codes
        ErrorCodes errorCodes = new ErrorCodes(Constants.API_URL, rawResponse, isNetworkError, String.valueOf(errorCode), errorMsg);
        return new ElasticsError(deviceData, userData, new Journey(screensOpened.toString()), errorCodes);


    }

    private ElasticsMap initElasticsMap(HashMap<String, String> errorMap) {
        initElastics();
        return new ElasticsMap(deviceData, new Journey(screensOpened.toString()), userData, errorMap);
    }

    class FragmentTracker extends FragmentManager.FragmentLifecycleCallbacks {
        @Override
        public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentResumed(fm, f);
            addScreensOpened(f.getClass().getSimpleName());
        }
    }
}


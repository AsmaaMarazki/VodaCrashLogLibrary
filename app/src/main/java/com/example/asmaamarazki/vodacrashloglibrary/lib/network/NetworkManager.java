package com.example.asmaamarazki.vodacrashloglibrary.lib.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.asmaamarazki.vodacrashloglibrary.Application;
import com.example.asmaamarazki.vodacrashloglibrary.lib.Vodalytics;
import com.example.asmaamarazki.vodacrashloglibrary.lib.database.DataSource;
import com.example.asmaamarazki.vodacrashloglibrary.lib.database.entities.ErrorInfo;

import static com.example.asmaamarazki.vodacrashloglibrary.lib.network.ElasticEndPoints.ELASTIC_REQUEST_TAG;

public class NetworkManager {

    //Call it to save a crash and it handles the logic
    public void sendCrashLogToServer(ErrorInfo error) {
        checkTheCurrentConnectionQuality(error);
    }

    private void sendReportToServer(final ErrorInfo error) {
        AndroidNetworking.post(ElasticEndPoints.ENDPOINT_ELASTIC_SAVE_CRASH)
                //.addQueryParameter("test","2")
                .addBodyParameter("userId", "1") // will send the Crash log object here
                .setTag(ELASTIC_REQUEST_TAG)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse response) {
                        //Handle the return of success of saving to server
                        //clear the item from db if there
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Insert Data
                                ErrorInfo errorInfo = DataSource.getAppDataSource(Application.getApp()).getErrorInfo().getErrorUsingId(error.getUuid());
                                if (errorInfo != null) {
                                    DataSource.getAppDataSource(Application.getApp()).getErrorInfo().deleteError(errorInfo);
                                }

                                Log.i("Response", "onResponse: Success");

                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {
                        //an error occurred and couldn't save the log to the server
                        //if is not available in the db add it.
                        //SharedPreferences sharedPreferences = Application.getApp().getSharedPreferences("TEMP", Context.MODE_PRIVATE);
                        //final String errCODE = sharedPreferences.getString("errCODE","");
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Insert Data
                                insertLogToDbIfUnique(error);

                                Log.i("Response", "onResponse: error");

                            }
                        });
                    }
                });
    }

    public void checkTheCurrentConnectionQuality(final ErrorInfo error) {
        //If there is network check quality else save to db directly.

        //Case No Network add add to the db
        /*if(NetworkUtils.isNetworkConnected(Application.getApp())){
            insertLogToDbIfUnique(error);
        }else{
        //Case network check quality.
        if (NetworkUtils.isConnectedFast(Application.getApp())) { // fast then send logs
            sendReportToServer(error);
        } else { //slow then don't send to servers and check to save to the db if not already there
            insertLogToDbIfUnique(error);
        }

        }*/

        // Getting current ConnectionQuality

        ConnectionQuality connectionQuality = AndroidNetworking.getCurrentConnectionQuality();
        if (connectionQuality == ConnectionQuality.EXCELLENT) {
            // save to server
            sendReportToServer(error);
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: " + ConnectionQuality.EXCELLENT);
        } else if (connectionQuality == ConnectionQuality.MODERATE) {
            // save to server
            sendReportToServer(error);
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: " + ConnectionQuality.MODERATE);
        } else if (connectionQuality == ConnectionQuality.POOR) {
            // do something
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: " + ConnectionQuality.POOR);
            sendReportToServer(error);

        } else if (connectionQuality == ConnectionQuality.UNKNOWN) {
            // do something
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: " + ConnectionQuality.UNKNOWN);
            sendReportToServer(error);

        }

    }

    private void insertLogToDbIfUnique(final ErrorInfo error) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Insert Data
                ErrorInfo errorInfo = DataSource.getAppDataSource(Application.getApp()).getErrorInfo().getErrorUsingId(error.getUuid());
                if (errorInfo != null) {
                    DataSource.getAppDataSource(Application.getApp()).getErrorInfo().insertError(errorInfo);
                }
            }
        });
    }
}

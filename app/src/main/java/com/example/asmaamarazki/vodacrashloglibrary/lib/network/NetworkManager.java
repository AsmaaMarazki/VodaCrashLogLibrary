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

import java.util.List;

import static com.example.asmaamarazki.vodacrashloglibrary.lib.network.ElasticEndPoints.ELASTIC_REQUEST_TAG;

public class NetworkManager {

    public void sendCrashLogToServer() {
        checkTheCurrentConnectionQuality();
    }

    private void sendReportToServer() {
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
                        SharedPreferences sharedPreferences = Application.getApp().getSharedPreferences("TEMP", Context.MODE_PRIVATE);
                        final String error = sharedPreferences.getString("errCODE","");
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Insert Data
                                ErrorInfo errorInfo = DataSource.getAppDataSource(Application.getApp()).getErrorInfo().getErrorUsingId(error);
                                if(errorInfo != null){
                                    DataSource.getAppDataSource(Application.getApp()).getErrorInfo().deleteError(errorInfo);
                                }

                                Log.i("Response", "onResponse: Success");

                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {
                        //an error occurred and couldn't save the log to the server
                        SharedPreferences sharedPreferences = Application.getApp().getSharedPreferences("TEMP", Context.MODE_PRIVATE);
                        final String error = sharedPreferences.getString("errCODE","");
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                // Insert Data
                                ErrorInfo errorInfo = DataSource.getAppDataSource(Application.getApp()).getErrorInfo().getErrorUsingId(error);
                                if(errorInfo == null){
                                    DataSource.getAppDataSource(Application.getApp()).getErrorInfo().insertError(Vodalytics.latestError);
                                }

                                Log.i("Response", "onResponse: error");

                            }
                        });
                    }
                });
    }

    public void checkTheCurrentConnectionQuality() {
        // Getting current ConnectionQuality
        ConnectionQuality connectionQuality = AndroidNetworking.getCurrentConnectionQuality();
        if (connectionQuality == ConnectionQuality.EXCELLENT) {
            // save to server
            sendReportToServer();
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: "+ConnectionQuality.EXCELLENT);
        } else if (connectionQuality == ConnectionQuality.MODERATE) {
            // save to server
            sendReportToServer();
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: "+ConnectionQuality.MODERATE);
        } else if (connectionQuality == ConnectionQuality.POOR) {
            // do something
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: "+ConnectionQuality.POOR);
            sendReportToServer();

        } else if (connectionQuality == ConnectionQuality.UNKNOWN) {
            // do something
            Log.i("NetworkManger", "checkTheCurrentConnectionQuality: "+ConnectionQuality.UNKNOWN);
            sendReportToServer();

        }
    }
}

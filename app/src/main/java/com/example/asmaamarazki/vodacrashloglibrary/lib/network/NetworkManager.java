package com.example.asmaamarazki.vodacrashloglibrary.lib.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import static com.example.asmaamarazki.vodacrashloglibrary.lib.network.ElasticEndPoints.ELASTIC_REQUEST_TAG;

public class NetworkManager {

    public void sendCrashLogToServer() {
        checkTheCurrentConnectionQuality();
    }

    private void sendReportToServer() {
        AndroidNetworking.post(ElasticEndPoints.ENDPOINT_ELASTIC_SAVE_CRASH)
                .addBodyParameter("userId", "1") // will send the Crash log object here
                .setTag(ELASTIC_REQUEST_TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse response) {
                        //Handle the return of success of saving to server
                        //clear the item from db if there
                    }

                    @Override
                    public void onError(ANError anError) {
                        //an error occurred and couldn't save the log to the server
                    }
                });
    }

    public void checkTheCurrentConnectionQuality() {
        // Getting current ConnectionQuality
        ConnectionQuality connectionQuality = AndroidNetworking.getCurrentConnectionQuality();
        if (connectionQuality == ConnectionQuality.EXCELLENT) {
            // save to server
            sendReportToServer();
        } else if (connectionQuality == ConnectionQuality.MODERATE) {
            // save to server
            sendReportToServer();
        } else if (connectionQuality == ConnectionQuality.POOR) {
            // do something
        } else if (connectionQuality == ConnectionQuality.UNKNOWN) {
            // do something
        }
    }
}

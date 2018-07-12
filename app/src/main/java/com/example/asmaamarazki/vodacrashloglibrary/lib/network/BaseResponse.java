package com.example.asmaamarazki.vodacrashloglibrary.lib.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Morabea on 10/27/15.
 */
public class BaseResponse {

    @SerializedName("eCode")
    private int errorCode;

    @SerializedName("eDesc")
    private String errorMsg;


    public BaseResponse() {
    }

    public BaseResponse(int errorCode) {
        this(errorCode, "");
    }

    public BaseResponse(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

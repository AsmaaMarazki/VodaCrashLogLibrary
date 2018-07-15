package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

public class ErrorCodes {
    public ErrorCodes(String apiUrl, String rawResponse, boolean isNetworkError, String errorCode, String errorMessage) {
        this.apiUrl = apiUrl;
        this.rawResponse = rawResponse;
        this.isNetworkError = isNetworkError;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private String apiUrl;
   private String rawResponse;
   private boolean isNetworkError;
   private String errorCode;
   private String errorMessage;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public boolean isNetworkError() {
        return isNetworkError;
    }

    public void setNetworkError(boolean networkError) {
        isNetworkError = networkError;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

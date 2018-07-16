package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

public class ElasticsError extends  BaseElastics{
    ErrorCodes errorCodes;;

    public ElasticsError() {
    }

    public ElasticsError(DeviceData deviceData, Journey journey, ErrorCodes errorCodes) {
        super(deviceData, journey);
        this.errorCodes = errorCodes;
    }

    public ElasticsError(DeviceData deviceData, UserData userData, Journey journey, ErrorCodes errorCodes) {
        super(deviceData,journey,userData);
        this.deviceData = deviceData;
        this.errorCodes = errorCodes;
        this.journey = journey;
        this.userData = userData;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public void setErrorCodes(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public DeviceData getDeviceData() {

        return deviceData;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public Journey getJourney() {
        return journey;
    }

    public UserData getUserData() {
        return userData;
    }
}

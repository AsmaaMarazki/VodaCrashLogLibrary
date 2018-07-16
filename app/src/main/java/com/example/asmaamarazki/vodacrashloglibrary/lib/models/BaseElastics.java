package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

public class BaseElastics {
    DeviceData deviceData;
    Journey journey;
    UserData userData;

    public BaseElastics() {
    }

    public BaseElastics(DeviceData deviceData, Journey journey) {
        this.deviceData = deviceData;
        this.journey = journey;
    }

    public BaseElastics(DeviceData deviceData, Journey journey, UserData userData) {
        this.deviceData = deviceData;
        this.journey = journey;
        this.userData = userData;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public Journey getJourney() {
        return journey;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}

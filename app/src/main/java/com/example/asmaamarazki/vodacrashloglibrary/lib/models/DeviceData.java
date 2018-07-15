package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

public class DeviceData {
    private boolean isWifiConnected;
    private boolean isMobileDataConnected;
    private String deviceType;
    private String deviceOsVersion;

    public boolean isWifiConnected() {
        return isWifiConnected;
    }

    public void setWifiConnected(boolean wifiConnected) {
        isWifiConnected = wifiConnected;
    }

    public boolean isMobileDataConnected() {
        return isMobileDataConnected;
    }

    public void setMobileDataConnected(boolean mobileDataConnected) {
        isMobileDataConnected = mobileDataConnected;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceOsVersion() {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion(String deviceOsVersion) {
        this.deviceOsVersion = deviceOsVersion;
    }
}

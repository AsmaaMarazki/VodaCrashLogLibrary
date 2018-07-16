package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

import java.util.HashMap;

public class ElasticsMap  extends BaseElastics{
    HashMap<String,String> errorMap;

    public ElasticsMap() {
    }

    public ElasticsMap(DeviceData deviceData, Journey journey, HashMap<String, String> errorMap) {
        super(deviceData, journey);
        this.errorMap = errorMap;
    }

    public ElasticsMap(DeviceData deviceData, Journey journey, UserData userData, HashMap<String, String> errorMap) {
        super(deviceData, journey, userData);
        this.errorMap = errorMap;
    }
}

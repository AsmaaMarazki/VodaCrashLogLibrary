package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

import java.util.ArrayList;

public class UserData {

    private boolean isSeamless;
    private String msisdn;
    private ArrayList<String> msisdnAllAccounts;
    private String ratePlanCode;
    private String tireId;
    private String[] userRoles;

    public UserData(boolean isSeamless, String msisdn, ArrayList<String> msisdnAllAccounts, String ratePlanCode, String tireId, String[] userRoles) {
        this.isSeamless = isSeamless;
        this.msisdn = msisdn;
        this.msisdnAllAccounts = msisdnAllAccounts;
        this.ratePlanCode = ratePlanCode;
        this.tireId = tireId;
        this.userRoles = userRoles;
    }

    public boolean isSeamless() {
        return isSeamless;
    }

    public void setSeamless(boolean seamless) {
        isSeamless = seamless;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public ArrayList<String> getMsisdnAllAccounts() {
        return msisdnAllAccounts;
    }

    public void setMsisdnAllAccounts(ArrayList<String> msisdnAllAccounts) {
        this.msisdnAllAccounts = msisdnAllAccounts;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getTireId() {
        return tireId;
    }

    public void setTireId(String tireId) {
        this.tireId = tireId;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }
}

package com.example.asmaamarazki.vodacrashloglibrary.lib.models;

public class UserData {

    private boolean isSeamless;
    private String msisdn;
    private String[] msisdnAllAccounts;
    private String ratePlanCode;
    private String tireId;
    private String[] userRoles;

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

    public String[] getMsisdnAllAccounts() {
        return msisdnAllAccounts;
    }

    public void setMsisdnAllAccounts(String[] msisdnAllAccounts) {
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

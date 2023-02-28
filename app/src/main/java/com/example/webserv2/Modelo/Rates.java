package com.example.webserv2.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {
    @SerializedName("EUR")
    @Expose
    private String EUR;

    @SerializedName("AUD")
    @Expose
    private String AUD;

    @SerializedName("CNY")
    @Expose
    private String CNY;

    @SerializedName("GBP")
    @Expose
    private String GBP;

    @SerializedName("ISK")
    @Expose
    private String ISK;

    @SerializedName("MXN")
    @Expose
    private String MXN;

    @SerializedName("PLN")
    @Expose
    private String PLN;

    public String getEUR() {
        return EUR;
    }

    public void setEUR(String EUR) {
        this.EUR = EUR;
    }

    public String getAUD() {
        return AUD;
    }

    public void setAUD(String AUD) {
        this.AUD = AUD;
    }

    public String getCNY() {
        return CNY;
    }

    public void setCNY(String CNY) {
        this.CNY = CNY;
    }

    public String getGBP() {
        return GBP;
    }

    public void setGBP(String GBP) {
        this.GBP = GBP;
    }

    public String getISK() {
        return ISK;
    }

    public void setISK(String ISK) {
        this.ISK = ISK;
    }

    public String getMXN() {
        return MXN;
    }

    public void setMXN(String MXN) {
        this.MXN = MXN;
    }

    public String getPLN() {
        return PLN;
    }

    public void setPLN(String PLN) {
        this.PLN = PLN;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "EUR='" + EUR + '\'' +
                ", AUD='" + AUD + '\'' +
                ", CNY='" + CNY + '\'' +
                ", GBP='" + GBP + '\'' +
                ", ISK='" + ISK + '\'' +
                ", MXN='" + MXN + '\'' +
                ", PLN='" + PLN + '\'' +
                '}';
    }
}

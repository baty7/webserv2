package com.example.webserv2.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CambioDivisas {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CambioDivisas{" +
                "timestamp='" + timestamp + '\'' +
                ", base='" + base + '\'' +
                ", rate=" + rates +
                '}';
    }
}

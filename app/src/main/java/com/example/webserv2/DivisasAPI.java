package com.example.webserv2;

import com.example.webserv2.Modelo.CambioDivisas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DivisasAPI {
    String BASE_URL = "https://openexchangerates.org/api/";
    @Headers("Content-type: application/json")
    @GET("latest.json")
    Call<CambioDivisas> getStuff(@Query("app_id") String key);
}
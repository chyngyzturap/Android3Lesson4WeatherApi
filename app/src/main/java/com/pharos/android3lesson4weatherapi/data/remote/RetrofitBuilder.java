package com.pharos.android3lesson4weatherapi.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder(){}
    private static AndroidApi instance;

    public static AndroidApi getInstance() {
        if (instance == null) {
            instance = createApi();
        }
        return instance;
    }

    private static AndroidApi createApi() {
        return new Retrofit
                .Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AndroidApi.class);
    }
}
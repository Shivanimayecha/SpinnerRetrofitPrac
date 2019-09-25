package com.hd.nature.spinnerretrofitprac.retrofit;

import com.hd.nature.spinnerretrofitprac.ToStringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static String URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";

    public static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(new ToStringConverterFactory())
                .build();

    }


    public static RetroInterface getRetroInterface() {
        return getRetrofitInstance().create(RetroInterface.class);
    }


}

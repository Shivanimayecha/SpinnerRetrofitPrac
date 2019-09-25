package com.hd.nature.spinnerretrofitprac.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {

    @GET("json_parsing.php")
    Call<String> getData();
}

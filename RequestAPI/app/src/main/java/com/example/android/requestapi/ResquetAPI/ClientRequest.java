package com.example.android.requestapi.ResquetAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRequest {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://reqres.in/";

    public static Retrofit getClientRequest() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
package com.asterixsolution.ruia.vacanza;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Base_URL
{
    public static final String BASE_URL = "http://192.168.31.89:8080/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

package com.asterixsolution.ruia.vacanza;

import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RetrofitUtils
{
    public static final String BASE_URL = "http://192.168.31.89:8080/Vacanza/";
    private static RetrofitService service = null;

    public static RetrofitService getClient() {
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService.class);
        }
        return service;
    }

    public static interface RetrofitService
    {
        @POST("LoginGetData.jsp")
        @FormUrlEncoded
        Call<UserModel> LoginGetData(@Field("Email") String Email,
                                     @Field("Pwd") String Pwd);

        @POST("RegisterInsertData.jsp")
        @FormUrlEncoded
        Call<String>RegisterInsertData(@Field("Email") String Email,
                                       @Field("FName") String FName,
                                       @Field("LName") String LName,
                                       @Field("Pwd") String Pwd,
                                       @Field("CPwd") String CPwd);

        @GET("packageData.jsp")
        Call<List<PackageModel>>getData();

    }
}

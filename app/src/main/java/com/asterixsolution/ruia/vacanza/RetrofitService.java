package com.asterixsolution.ruia.vacanza; /**
 * Created by Wagle on 12-01-2018.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService
{
    @POST("Vacanza/LoginGetData.jsp")
    @FormUrlEncoded
    Call<String>LoginGetData(@Field("Email") String Email,
                             @Field("Pwd") String Pwd);

    @POST("Vacanza/RegisterInsertData.jsp")
    @FormUrlEncoded
    Call<String>RegisterInsertData(@Field("Email") String Email,
                                   @Field("FName") String FName,
                                   @Field("LName") String LName,
                                   @Field("Pwd") String Pwd,
                                   @Field("CPwd") String CPwd);

    @GET("Vacanza/cardData.jsp")
    Call<List<AlbumModel>>getData();
}

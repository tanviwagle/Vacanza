package com.asterixsolution.ruia.vacanza.retrofit;

import com.asterixsolution.ruia.vacanza.models.HotelModel;
import com.asterixsolution.ruia.vacanza.models.PackageModel;
import com.asterixsolution.ruia.vacanza.models.PlaceModel;
import com.asterixsolution.ruia.vacanza.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

        @GET("packDetails.jsp")
        Call<List<PackageModel>>getPackDet(@Query("id") String placeid);

        @POST("ProfilePassword.jsp")
        @FormUrlEncoded
        Call<String> PasswordData (@Field("Pwd") String Pwd);

        @POST("UpdateProfile.jsp")
        @FormUrlEncoded
        Call<String> ProfileDetails(@Field("id") String id,
                                    @Field("Email") String Email,
                                    @Field("FName") String FName,
                                    @Field("LName") String LName,
                                    @Field("Pwd") String Pwd);

        @POST("Feedback.jsp")
        @FormUrlEncoded
        Call<String> sendFeedback(@Field("Email") String Email,
                                 @Field("FText") String FText);

        @POST("place.jsp")
        @FormUrlEncoded
        Call<List<PlaceModel>> details(@Field("name") String name);

        @GET("PlaceNames.jsp")
        Call<List<String>> names();

        @GET("hotelPlaceId.jsp")
        Call<List<HotelModel>>getHotelDet(@Query("name") String name);

        @GET("HotelDetails.jsp")
        Call<List<HotelModel>> Details(@Query("placeid") String placeid);

        @POST("addRequest.jsp")
        @FormUrlEncoded
        Call<String> addRequest(@Field("packageId") String pid,
                                @Field("hotelId")String hid,
                                @Field("userId")String uid);

    }
}

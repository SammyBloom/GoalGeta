package com.android.goalgeta.api;

import com.android.goalgeta.models.ResponseObb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST ("register")
    Call<ResponseObb>register(
            @Field("name") String username,
            @Field("email") String email,
            @Field("phone_number") String phoneNo,
            @Field("password") String password,
            @Field("password_confirmation") String cnfPassword

    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseObb>login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("profile")
    Call<ResponseObb>profile(@Header("Authorization") String authToken);


}

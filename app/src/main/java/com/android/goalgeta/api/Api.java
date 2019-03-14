package com.android.goalgeta.api;

import com.android.goalgeta.models.LoginResponse;
import com.android.goalgeta.models.ProfileResponse;
import com.android.goalgeta.models.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST ("register")
    Call<Response>register(
            @Field("name") String username,
            @Field("email") String email,
            @Field("phone_number") String phoneNo,
            @Field("password") String password,
            @Field("password_confirmation") String cnfPassword

    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse>login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("profile")
    Call<ProfileResponse>profile(@Header("Authorization") String authToken);


}

package com.android.goalgeta.api;

import com.android.goalgeta.models.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST ("register")
    Call<ResponseBody>register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phoneNo") String phoneNo,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse>login(
            @Field("username") String username,
            @Field("password") String password
    );


}

package com.example.userprotection.network;

import com.example.userprotection.models.LoginRequest;
import com.example.userprotection.models.LoginResponse;
import com.example.userprotection.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/register")
    Call<Void> register(@Body RegisterRequest request);

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}

package com.example.userprotection.network;

import com.example.userprotection.utils.Constants;
import com.example.userprotection.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client;

            if (BuildConfig.DEBUG && BuildConfig.USE_UNSAFE_SSL) {
                // Debug build → dùng unsafe client để bypass SSL (chỉ khi chưa import cert)
                client = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            } else {
                // Release build → client an toàn mặc định
                client = new OkHttpClient.Builder().build();
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

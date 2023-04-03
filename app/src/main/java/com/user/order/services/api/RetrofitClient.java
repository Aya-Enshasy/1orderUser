package com.user.order.services.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getSimpleName();

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://1order.sa/api/v2/";

    public static Retrofit getClient() {
         HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor(message -> Log.d(TAG, "getClient: " + message));
        httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(50000, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Accept", "*/*")
                                .addHeader("Content-Type", "multipart/form-data")
                                .addHeader("X-Requested-With", "XMLHttpRequest")
                                .addHeader("Accept-Language", "en")

                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(httpLogging)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
}

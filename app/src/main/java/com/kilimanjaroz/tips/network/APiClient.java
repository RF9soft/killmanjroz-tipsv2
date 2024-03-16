package com.kilimanjaroz.tips.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APiClient {

    private static APiClient instance;
    private Retrofit retrofit;

    private APiClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Add an interceptor to include headers for every request
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("X-RapidAPI-Key", Constantine.API_KEY)
                    .header("X-RapidAPI-Host", Constantine.API_HOST)
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantine.BASE_URLLiveScore)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) // Set the custom OkHttpClient with the added headers
                .build();
    }

    public static synchronized APiClient getInstance() {
        if (instance == null) {
            instance = new APiClient();
        }
        return instance;
    }

    public APIService getApi() {
        return retrofit.create(APIService.class);
    }
}

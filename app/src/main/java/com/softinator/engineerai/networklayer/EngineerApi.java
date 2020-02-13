package com.softinator.engineerai.networklayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EngineerApi {

    private static Retrofit retrofit = null;
    private static final String BASE = "http://sd2-hiring.herokuapp.com/api/";

    public static Retrofit getClient(){

        Gson gson = new GsonBuilder().serializeNulls().create();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder().
                baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(client)
                .build();

        return retrofit;

    }


}

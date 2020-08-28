package com.example.doanandroid.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofitclient {
    public  static final String BASE_URL="https://mylifemrrobot.000webhostapp.com/";

    public static Retrofit retrofit= null;
    public  static Retrofit getClient()
    {
       if(retrofit==null) {
           Gson gson = new GsonBuilder().setLenient().create();
           OkHttpClient builder = new OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS)//thời gian cho app đọc data
                   .writeTimeout(3000,TimeUnit.MILLISECONDS)//thời gian cho app ghi dữ liệu
                   .connectTimeout(3000,TimeUnit.MILLISECONDS)//thời gian cho app kết nối lại
                   .retryOnConnectionFailure(true)//kết nối lại
                   .build();
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(ScalarsConverterFactory.create())
                   .addConverterFactory(GsonConverterFactory.create(gson))
                   .client(builder)
                   .build();
       }
        return  retrofit;
    }
}

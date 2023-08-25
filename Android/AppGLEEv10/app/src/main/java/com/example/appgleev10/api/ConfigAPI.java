package com.example.appgleev10.api;

import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// 192.168.0.9
public class ConfigAPI {

    public static final String BASE_URL = "http://192.168.1.237:8081";
    private static Retrofit retrofit;
    private static String token = "";

    private static AdminAPI adminAPI;
    private static VisitantAPI visitantAPI;
    private static DocStroredAPI docStroredAPI;
    private static CategoryAPI categoryAPI;
    private static EventAPI eventAPI;
    private static RequestAPI requestAPI;

    static  {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();

    }

    public static OkHttpClient getClient() {
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(loggin)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(stetho);
        return builder.build();
    }

    public static void settoken(String value) {
        token = value;
        initClient();
    }

    public static AdminAPI getAdminAPI() {
        if (adminAPI == null) {
            adminAPI = retrofit.create(AdminAPI.class);
        }
        return adminAPI;
    }

    public static VisitantAPI getVisitantAPI() {
        if(visitantAPI == null) {
            visitantAPI = retrofit.create(VisitantAPI.class);
        }
        return visitantAPI;
    }

    public static DocStroredAPI getDocStroredAPI() {
        if (docStroredAPI == null) {
            docStroredAPI = retrofit.create(DocStroredAPI.class);
        }
        return docStroredAPI;
    }

    public static CategoryAPI getCategoryAPI() {
        if (categoryAPI == null) {
            categoryAPI = retrofit.create(CategoryAPI.class);
        }
        return categoryAPI;
    }

    public static RequestAPI getResquestAPI(){
        if(requestAPI == null){
            requestAPI = retrofit.create(RequestAPI.class);
        }
        return requestAPI;
    }

    public static EventAPI getEventAPI() {
        if (eventAPI == null) {
            eventAPI = retrofit.create(EventAPI.class);
        }
        return eventAPI;
    }
}

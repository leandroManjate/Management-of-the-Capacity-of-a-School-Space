package com.example.appgleev10.api;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Admin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AdminAPI {
    String base = "admin";

    @FormUrlEncoded
    @POST(base + "/login")
    Call<GenericResponse<Admin>> login(@Field("email") String email, @Field("pass") String password);

    @POST(base)
    Call<GenericResponse<Admin>> save (@Body Admin admin);
}

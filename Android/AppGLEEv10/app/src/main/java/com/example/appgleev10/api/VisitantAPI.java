package com.example.appgleev10.api;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Visitant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VisitantAPI {
    String base = "/visitant";

    @POST(base)
    Call<GenericResponse<Visitant>> saveVisitant(@Body Visitant visitant);
}

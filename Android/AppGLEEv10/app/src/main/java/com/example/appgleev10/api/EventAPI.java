package com.example.appgleev10.api;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventAPI {
    String base = "/event";

    @GET(base)
    Call<GenericResponse<List<Event>>> listRecomendedEvents();

    @GET(base + "/{idC}")
    Call<GenericResponse<List<Event>>> listEventsByCategory(@Path("idC") int idC);
}

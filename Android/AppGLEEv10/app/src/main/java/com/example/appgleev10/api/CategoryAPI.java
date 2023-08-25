package com.example.appgleev10.api;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    String base = "/category";

    @GET(base)
    Call<GenericResponse<List<Category>>> listActiveCategories();
}

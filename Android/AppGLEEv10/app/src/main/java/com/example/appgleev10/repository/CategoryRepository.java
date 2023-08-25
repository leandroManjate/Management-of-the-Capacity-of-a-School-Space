package com.example.appgleev10.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.CategoryAPI;
import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private final CategoryAPI categoryAPI;
    private static CategoryRepository categoryRepository;

    public CategoryRepository() {
        this.categoryAPI = ConfigAPI.getCategoryAPI();
    }
    public static CategoryRepository getInstance(){
        if(categoryRepository == null){
            categoryRepository = new CategoryRepository();
        }
        return categoryRepository;
    }
    public LiveData<GenericResponse<List<Category>>> listActiveCategories(){
        final MutableLiveData<GenericResponse<List<Category>>> mld = new MutableLiveData<>();
        this.categoryAPI.listActiveCategories().enqueue(new Callback<GenericResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Category>>> call, Response<GenericResponse<List<Category>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Category>>> call, Throwable t) {
                System.out.println("Error obtaining the categories: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}

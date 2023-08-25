package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Category;
import com.example.appgleev10.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private final CategoryRepository categoryRepository;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        this.categoryRepository = CategoryRepository.getInstance();
    }

    public LiveData<GenericResponse<List<Category>>> listActiveCategories(){
        return this.categoryRepository.listActiveCategories();
    }
}

package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.DocStored;
import com.example.appgleev10.repository.DocStoredRepository;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DocStoredViewModel extends AndroidViewModel {
    private final DocStoredRepository docStoredRepository;

    public DocStoredViewModel(@NonNull Application application) {
        super(application);
        this.docStoredRepository = DocStoredRepository.getInstance();
    }
    public LiveData<GenericResponse<DocStored>> save(MultipartBody.Part part, RequestBody requestBody){
        return this.docStoredRepository.savePhoto(part, requestBody);
    }
}

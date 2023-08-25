package com.example.appgleev10.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.api.DocStroredAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.DocStored;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocStoredRepository {
    private final DocStroredAPI docStroredAPI;
    private static DocStoredRepository docStoredRepository;

    public DocStoredRepository() {
        this.docStroredAPI = ConfigAPI.getDocStroredAPI();
    }

    public static DocStoredRepository getInstance(){
        if(docStoredRepository == null){
            docStoredRepository = new DocStoredRepository();
        }
        return docStoredRepository;
    }

    public LiveData<GenericResponse<DocStored>> savePhoto(MultipartBody.Part part, RequestBody requestBody){
        final MutableLiveData<GenericResponse<DocStored>> mld = new MutableLiveData<>();
        this.docStroredAPI.save(part, requestBody).enqueue(new Callback<GenericResponse<DocStored>>() {
            @Override
            public void onResponse(Call<GenericResponse<DocStored>> call, Response<GenericResponse<DocStored>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<DocStored>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Error detected: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}

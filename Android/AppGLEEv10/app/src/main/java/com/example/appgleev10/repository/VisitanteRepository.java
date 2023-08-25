package com.example.appgleev10.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.api.VisitantAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Visitant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitanteRepository {
    private static VisitanteRepository visitanteRepository;
    private final VisitantAPI visitantAPI;

    public static VisitanteRepository getInstance(){
        if(visitanteRepository == null){
            visitanteRepository = new VisitanteRepository();
        }
        return visitanteRepository;
    }
    private VisitanteRepository(){
        visitantAPI = ConfigAPI.getVisitantAPI();
    }

    public LiveData<GenericResponse<Visitant>> saveVisitant(Visitant visitant){
        final MutableLiveData<GenericResponse<Visitant>> mld = new MutableLiveData<>();
        this.visitantAPI.saveVisitant(visitant).enqueue(new Callback<GenericResponse<Visitant>>() {
            @Override
            public void onResponse(Call<GenericResponse<Visitant>> call, Response<GenericResponse<Visitant>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Visitant>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Error detected : " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}

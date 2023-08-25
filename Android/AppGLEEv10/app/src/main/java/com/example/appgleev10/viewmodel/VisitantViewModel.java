package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Visitant;
import com.example.appgleev10.repository.VisitanteRepository;

public class VisitantViewModel extends AndroidViewModel {
    private final VisitanteRepository visitanteRepository;

    public VisitantViewModel(@NonNull Application application) {
        super(application);
        this.visitanteRepository = VisitanteRepository.getInstance();
    }

    public LiveData<GenericResponse<Visitant>> saveVisitant(Visitant visitant){
        return  visitanteRepository.saveVisitant(visitant);
    }
}

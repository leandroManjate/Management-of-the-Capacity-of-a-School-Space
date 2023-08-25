package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Request;
import com.example.appgleev10.entity.service.dto.GenereteRequestDTO;
import com.example.appgleev10.entity.service.dto.RequestWithDetailDTO;
import com.example.appgleev10.repository.RequestRepository;

import java.util.List;

import okhttp3.ResponseBody;

public class RequestViewModel extends AndroidViewModel {
    private final RequestRepository requestRepository;

    public RequestViewModel(@NonNull Application application) {
        super(application);
        this.requestRepository = RequestRepository.getInstance();
    }
    public LiveData<GenericResponse<List<RequestWithDetailDTO>>> listRequestsByVisitant(int idVis){
        return this.requestRepository.listRequestsByVisitant(idVis);
    }

    public LiveData<GenericResponse<GenereteRequestDTO>> saveRequest(GenereteRequestDTO dto){
        return requestRepository.save(dto);
    }

    public LiveData<GenericResponse<Request>> cancelRequest(int id){
        return requestRepository.cancelRequest(id);
    }

}

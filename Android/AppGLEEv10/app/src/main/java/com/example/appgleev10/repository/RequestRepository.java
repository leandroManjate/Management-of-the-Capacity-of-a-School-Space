package com.example.appgleev10.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.api.RequestAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Request;
import com.example.appgleev10.entity.service.dto.GenereteRequestDTO;
import com.example.appgleev10.entity.service.dto.RequestWithDetailDTO;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepository {
    private final RequestAPI requestAPI;
    private static RequestRepository requestRepository;

    public RequestRepository() {
        this.requestAPI = ConfigAPI.getResquestAPI();
    }

    public static RequestRepository getInstance() {
        if (requestRepository == null) {
            requestRepository = new RequestRepository();
        }
        return requestRepository;
    }

    public LiveData<GenericResponse<List<RequestWithDetailDTO>>> listRequestsByVisitant(int idVis){
        final MutableLiveData<GenericResponse<List<RequestWithDetailDTO>>> mld = new MutableLiveData<>();
        this.requestAPI.listRequestsByVisitant(idVis).enqueue(new Callback<GenericResponse<List<RequestWithDetailDTO>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<RequestWithDetailDTO>>> call, Response<GenericResponse<List<RequestWithDetailDTO>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<RequestWithDetailDTO>>> call, Throwable t) {
                mld.setValue(new GenericResponse());
                System.out.println("Error getting request: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }

    //SAVE REQUEST WITH DETAIL
    public LiveData<GenericResponse<GenereteRequestDTO>> save(GenereteRequestDTO dto) {
        MutableLiveData<GenericResponse<GenereteRequestDTO>> data = new MutableLiveData<>();
        requestAPI.saveRequest(dto).enqueue(new Callback<GenericResponse<GenereteRequestDTO>>() {
            @Override
            public void onResponse(Call<GenericResponse<GenereteRequestDTO>> call, Response<GenericResponse<GenereteRequestDTO>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<GenereteRequestDTO>> call, Throwable t) {
                data.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return data;
    }

    //CANCEL REQUEST
    public LiveData<GenericResponse<Request>> cancelRequest(int id){
        MutableLiveData<GenericResponse<Request>> mld = new MutableLiveData<>();
        this.requestAPI.cancelRequest(id).enqueue(new Callback<GenericResponse<Request>>() {
            @Override
            public void onResponse(Call<GenericResponse<Request>> call, Response<GenericResponse<Request>> response) {
                if(response.isSuccessful()){
                    mld.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<Request>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }

}

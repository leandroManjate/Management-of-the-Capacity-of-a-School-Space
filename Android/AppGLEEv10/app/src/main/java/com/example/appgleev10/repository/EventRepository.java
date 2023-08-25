package com.example.appgleev10.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appgleev10.api.ConfigAPI;
import com.example.appgleev10.api.EventAPI;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {
    private final EventAPI eventAPI;
    private static EventRepository eventRepository;

    public EventRepository() {
        this.eventAPI = ConfigAPI.getEventAPI();
    }

    public static EventRepository getInstance() {
        if (eventRepository == null) {
            eventRepository = new EventRepository();
        }
        return eventRepository;
    }

    public LiveData<GenericResponse<List<Event>>> listRecomendedEvents(){
        final MutableLiveData<GenericResponse<List<Event>>> mld = new MutableLiveData<>();
        this.eventAPI.listRecomendedEvents().enqueue(new Callback<GenericResponse<List<Event>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Event>>> call, Response<GenericResponse<List<Event>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Event>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }

    public LiveData<GenericResponse<List<Event>>> listarPlatillosPorCategoria(int idC){
        final MutableLiveData<GenericResponse<List<Event>>> mld = new MutableLiveData<>();
        this.eventAPI.listEventsByCategory(idC).enqueue(new Callback<GenericResponse<List<Event>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Event>>> call, Response<GenericResponse<List<Event>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Event>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }
}

package com.example.appgleev10.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.appgleev10.entity.GenericResponse;
import com.example.appgleev10.entity.service.Event;
import com.example.appgleev10.repository.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private final EventRepository eventRepository;

    public EventViewModel(@NonNull Application application) {
        super(application);
       eventRepository = EventRepository.getInstance();
    }
    public LiveData<GenericResponse<List<Event>>> listRecomendedEvents(){
        return this.eventRepository.listRecomendedEvents();
    }
    public LiveData<GenericResponse<List<Event>>> listEventsByCategory(int idC){
        return this.eventRepository.listarPlatillosPorCategoria(idC);
    }
}

package com.project.appglee.AppGLEE_DB.service;
import com.project.appglee.AppGLEE_DB.repository.EventRepository;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public GenericResponse listEventsByRecomended(){
        return new GenericResponse(TYPE_DATA, RESP_OK, CORRECT_OPERATION, this.eventRepository.listEventsByRecomended());
    }
    public GenericResponse listEventsByCategory(int idC){
        return new GenericResponse(TYPE_DATA, RESP_OK, CORRECT_OPERATION, this.eventRepository.listEventByCategory(idC));
    }
}

package com.project.appglee.AppGLEE_DB.controller;
import com.project.appglee.AppGLEE_DB.service.EventService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public GenericResponse listEventsByRecomended(){
        return this.eventService.listEventsByRecomended();
    }
    @GetMapping("/{idC}")
    public GenericResponse listEventsByCategory(@PathVariable int idC){
        return this.eventService.listEventsByCategory(idC);
    }
}

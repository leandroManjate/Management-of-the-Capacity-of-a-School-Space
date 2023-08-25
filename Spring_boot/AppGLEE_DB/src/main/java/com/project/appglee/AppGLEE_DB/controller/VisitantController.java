package com.project.appglee.AppGLEE_DB.controller;

import com.project.appglee.AppGLEE_DB.entity.Visitant;
import com.project.appglee.AppGLEE_DB.service.VisitantService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/visitant")
public class VisitantController {
    private final VisitantService visitantServicee;

    public VisitantController(VisitantService visitantServicee) {

        this.visitantServicee = visitantServicee;
    }

    @PostMapping
    public GenericResponse save(@Valid @RequestBody Visitant visitant){

        return this.visitantServicee.save(visitant);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @Valid @RequestBody Visitant visitant){
        visitant.setId(id);
        return this.visitantServicee.save(visitant);
    }
}

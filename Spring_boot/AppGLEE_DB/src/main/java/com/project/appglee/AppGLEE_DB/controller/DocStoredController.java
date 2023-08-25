package com.project.appglee.AppGLEE_DB.controller;
import com.project.appglee.AppGLEE_DB.entity.DocStored;
import com.project.appglee.AppGLEE_DB.service.DocStoredService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/docstored")
public class DocStoredController {
    private DocStoredService service;

    public DocStoredController(DocStoredService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    @PostMapping
    public GenericResponse save(@ModelAttribute DocStored obj) {
        return service.save(obj);
    }

    public GenericResponse update(Long aLong, DocStored obj) {
        return null;
    }
    
    public GenericResponse delete(Long aLong) {
        return null;
    }
}

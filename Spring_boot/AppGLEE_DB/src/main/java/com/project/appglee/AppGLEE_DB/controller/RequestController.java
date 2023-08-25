package com.project.appglee.AppGLEE_DB.controller;
import com.project.appglee.AppGLEE_DB.entity.dto.GenereteRequestDTO;
import com.project.appglee.AppGLEE_DB.entity.dto.RequestWithDetailDTO;
import com.project.appglee.AppGLEE_DB.service.RequestService;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    //LIST REQUESTS WITH DETAIL
    @GetMapping("/myRequests/{idVis}")
    public GenericResponse<List<RequestWithDetailDTO>> giveMyRequestWithDetail(@PathVariable int idVis){
        return this.requestService.giveMyRequest(idVis);
    }
    //SAVE REQUEST
    @PostMapping
    public GenericResponse saveRequest(@RequestBody GenereteRequestDTO dto){
        return this.requestService.saveRequest(dto);
    }
    //CANCEL REQUEST
    @DeleteMapping("/{id}")
    public GenericResponse cancelRequest(@PathVariable int id){
        return this.requestService.cancelRequest(id);
    }

}

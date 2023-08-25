package com.project.appglee.AppGLEE_DB.entity.dto;


import com.project.appglee.AppGLEE_DB.entity.DetailRequest;
import com.project.appglee.AppGLEE_DB.entity.Request;

import java.util.ArrayList;

public class RequestWithDetailDTO {
    private Request request;
    private Iterable<DetailRequest> detailRequest;

    public RequestWithDetailDTO() {
        this.request = new Request();
        this.detailRequest = new ArrayList<>();
    }

    public RequestWithDetailDTO(Request request, Iterable<DetailRequest> detailRequest) {
        this.request = request;
        this.detailRequest = detailRequest;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Iterable<DetailRequest> getDetailRequest() {
        return detailRequest;
    }

    public void setDetailRequest(Iterable<DetailRequest> detailRequest) {
        this.detailRequest = detailRequest;
    }
}

package com.project.appglee.AppGLEE_DB.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.appglee.AppGLEE_DB.entity.DetailRequest;
import com.project.appglee.AppGLEE_DB.entity.Request;
import com.project.appglee.AppGLEE_DB.entity.Visitant;

public class GenereteRequestDTO {
    private Request request;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Iterable<DetailRequest> detailRequests;
    private Visitant visitant;

    public GenereteRequestDTO() {
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Iterable<DetailRequest> getDetailRequests() {
        return detailRequests;
    }

    public void setDetailRequests(Iterable<DetailRequest> detailRequests) {
        this.detailRequests = detailRequests;
    }

    public Visitant getVisitant() {
        return visitant;
    }

    public void setVisitant(Visitant visitant) {
        this.visitant = visitant;
    }
}

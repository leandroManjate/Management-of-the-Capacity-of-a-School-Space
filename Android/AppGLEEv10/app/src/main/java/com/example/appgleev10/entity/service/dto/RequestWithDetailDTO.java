package com.example.appgleev10.entity.service.dto;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestWithDetailDTO {

    private Request request;
    private List<DetailRequest> detailRequest;

    public RequestWithDetailDTO() {
        this.request = new Request();
        this.detailRequest = new ArrayList<>();
    }

    public RequestWithDetailDTO(Request request, List<DetailRequest> detailRequest) {
        this.request = request;
        this.detailRequest = detailRequest;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<DetailRequest> getDetailRequest() {
        return detailRequest;
    }

    public void setDetailRequest(List<DetailRequest> detailRequest) {
        this.detailRequest = detailRequest;
    }
}

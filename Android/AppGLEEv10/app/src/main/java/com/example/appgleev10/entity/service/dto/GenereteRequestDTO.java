package com.example.appgleev10.entity.service.dto;
import com.example.appgleev10.entity.service.DetailRequest;
import com.example.appgleev10.entity.service.Request;
import com.example.appgleev10.entity.service.Visitant;

import java.util.ArrayList;

public class GenereteRequestDTO {
    private Request request;
    private ArrayList<DetailRequest>detailRequests;
    private Visitant visitant;

    public GenereteRequestDTO() {
        this.request = new Request();
        this.detailRequests = new ArrayList<>();
        this.visitant = new Visitant();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ArrayList<DetailRequest> getDetailRequests() {
        return detailRequests;
    }

    public void setDetailRequests(ArrayList<DetailRequest> detailRequests) {
        this.detailRequests = detailRequests;
    }

    public Visitant getVisitant() {
        return visitant;
    }

    public void setVisitant(Visitant visitant) {
        this.visitant = visitant;
    }
}

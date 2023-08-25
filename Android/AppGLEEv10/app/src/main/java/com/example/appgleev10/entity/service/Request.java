package com.example.appgleev10.entity.service;

//import java.util.Date;

import java.sql.Date;

public class Request {
    private int id;
    private Date confirmResquest;
    private Visitant visitant;
    private boolean cancelRequest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getConfirmResquest() {
        return confirmResquest;
    }

    public void setConfirmResquest(Date confirmResquest) {
        this.confirmResquest = confirmResquest;
    }

    public Visitant getVisitant() {
        return visitant;
    }

    public void setVisitant(Visitant visitant) {
        this.visitant = visitant;
    }

    public boolean isCancelRequest() {
        return cancelRequest;
    }

    public void setCancelRequest(boolean cancelRequest) {
        this.cancelRequest = cancelRequest;
    }
}

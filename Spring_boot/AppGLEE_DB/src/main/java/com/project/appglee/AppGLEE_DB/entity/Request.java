package com.project.appglee.AppGLEE_DB.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Europe/Lisbon")

    private Date confirmResquest;
    @ManyToOne
    private Visitant visitant;
    @Column
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

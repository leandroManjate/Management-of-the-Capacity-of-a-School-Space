package com.example.appgleev10.entity.service;

public class Admin {

    private int id;
    private String email;
    private String password;
    private boolean isvalid;
    private Visitant visitant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public Visitant getVisitant() {
        return visitant;
    }

    public void setVisitant(Visitant visitant) {
        this.visitant = visitant;
    }
}

package com.example.appgleev10.entity.service;

public class Event {

    private int id;
    private String name;
    private DocStored picture;
    private int vacancy;
    private String descripcionEvent;
    private String dateEvent;
    private String authorEvent;
    private Category category;
    private boolean isvalid;
    private boolean recomended;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocStored getPicture() {
        return picture;
    }

    public void setPicture(DocStored picture) {
        this.picture = picture;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getDescripcionEvent() {
        return descripcionEvent;
    }

    public void setDescripcionEvent(String descripcionEvent) {
        this.descripcionEvent = descripcionEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getAuthorEvent() {
        return authorEvent;
    }

    public void setAuthorEvent(String authorEvent) {
        this.authorEvent = authorEvent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public boolean isRecomended() {
        return recomended;
    }

    public void setRecomended(boolean recomendado) {
        this.recomended = recomendado;
    }

}

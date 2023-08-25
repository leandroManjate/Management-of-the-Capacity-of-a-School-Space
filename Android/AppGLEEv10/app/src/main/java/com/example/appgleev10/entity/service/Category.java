package com.example.appgleev10.entity.service;

public class Category {
    private int id;
    private String name;
    private boolean isvalid;
    private DocStored picture;

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

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public DocStored getPicture() {
        return picture;
    }

    public void setPicture(DocStored picture) {
        this.picture = picture;
    }
}

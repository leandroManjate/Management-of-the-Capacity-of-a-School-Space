package com.example.appgleev10.entity.service;

public class Visitant {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String school;

    private DocStored picture;

    public Visitant() {
    }

    public Visitant(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public DocStored getPicture() {
        return picture;
    }

    public void setPicture(DocStored picture) {
        this.picture = picture;
    }

    public String getCompleteName(){
        return this.name != null ?
                this.name + " " : "------";
    }
}

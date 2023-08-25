package com.project.appglee.AppGLEE_DB.entity;

import javax.persistence.*;

@Entity
public class Visitant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private String email;
    @Column(length = 11)
    private String phone;
    @Column(length = 500)
    private String city;
    @Column(length = 100)
    private String school;

    @OneToOne
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

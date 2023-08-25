package com.project.appglee.AppGLEE_DB.entity;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String name;
    @OneToOne
    private DocStored picture;
    @Column
    private int vacancy;
    @Column(length = 500)
    private String descripcionEvent;
    @Column(length = 10)
    private String dateEvent;
    @Column(length = 50)
    private String authorEvent;
    @OneToOne
    private Category category;
    @Column
    private boolean isvalid;
    @Column
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

    public Category getCategory() {
        return category;
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

    public void setRecomended(boolean recomended) {
        this.recomended = recomended;
    }
}

package com.example.appgleev10.entity;

public class SliderItem {
    private String title;
    private int image;

    public SliderItem() {
    }

    public SliderItem(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

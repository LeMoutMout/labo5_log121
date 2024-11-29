package com.example.labo5_log121.models;

public class ImageModel {
    private String imagePath;

    public ImageModel(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void save(){}

    public void load(){}
}


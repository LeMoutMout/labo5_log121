package com.example.labo5_log121.models;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private static final long serialVersionUID = 1L;
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


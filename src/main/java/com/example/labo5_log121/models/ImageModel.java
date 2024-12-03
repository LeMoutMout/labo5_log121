package com.example.labo5_log121.models;

import java.io.Serial;
import java.io.Serializable;

public class ImageModel implements Serializable {
    private String imagePath;
    @Serial
    private static final long serialVersionUID = 1L;

    public ImageModel(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
package com.example.labo5_log121.models;

public class ImageModel {
    private String imagePath;
<<<<<<< HEAD
    private double zoomFactor;

    public ImageModel(String imagePath) {
        this.imagePath = imagePath;
        this.zoomFactor = 1.0;
=======

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
>>>>>>> origin/main
    }

    public String getImagePath() {
        return imagePath;
    }

<<<<<<< HEAD
    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
=======
    public void save(){}

    public void load(){}
>>>>>>> origin/main
}


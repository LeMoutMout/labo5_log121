package com.example.labo5_log121.models;

public class ImageModel {
    private String imagePath;
    private double zoomFactor;

    public ImageModel(String imagePath) {
        this.imagePath = imagePath;
        this.zoomFactor = 1.0;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
}


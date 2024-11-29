package com.example.labo5_log121.models;

public class Perspective {
    private double scaleFactor;
    private double translationX;
    private double translationY;
    private ImageModel imageModel;

    public Perspective(ImageModel imageModel) {
        this.imageModel = imageModel;
        this.translationX = 0;
        this.translationY = 0;
    }

    public double getTranslationX() {
        return translationX;
    }

    public void setTranslationX(double translationX) {
        this.translationX = translationX;
    }

    public double getTranslationY() {
        return translationY;
    }

    public void setTranslationY(double translationY) {
        this.translationY = translationY;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }
}

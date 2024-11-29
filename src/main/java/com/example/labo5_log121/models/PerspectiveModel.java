package com.example.labo5_log121.models;

import com.example.labo5_log121.commands.Memento;

public class PerspectiveModel extends Subject {

    private double scaleFactor;
    private double translationX;
    private double translationY;
    private ImageModel image;

    public PerspectiveModel(double scaleFactor, double translationX, double translationY, ImageModel image) {
        this.scaleFactor = scaleFactor;
        this.translationX = translationX;
        this.translationY = translationY;
        this.image = image;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public double getTranslation() {
        return translationX;
    }

    public double getTranslationY() {
        return translationY;
    }

    public void setTranslation(double translationX, double translationY) {
        this.translationX = translationX;
        this.translationY = translationY;
    }

    public Memento createMemento(){
        return new Memento(scaleFactor, translationX, translationY);
    }

    public void restore(Memento memento){
        scaleFactor = memento.getScaleFactor();
        translationX = memento.getTranslationX();
        translationY = memento.getTranslationY();
    }
}

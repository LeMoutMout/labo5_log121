package com.example.labo5_log121.models;

import com.example.labo5_log121.commands.Memento;

import java.io.Serializable;

public class PerspectiveModel extends Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private double scaleFactor;
    private double translationX;
    private double translationY;
    private final ImageModel image;

    public PerspectiveModel(ImageModel image) {
        this.scaleFactor = 1;
        this.translationX = 0;
        this.translationY = 0;
        this.image = image;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public double getTranslationX() {
        return translationX;
    }

    public double getTranslationY() {
        return translationY;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        notifyObservers();
    }

    public void setTranslation(double translationX, double translationY) {
        this.translationX = translationX;
        this.translationY = translationY;
        notifyObservers();
    }

    public Memento createMemento(){
        return new Memento(scaleFactor, translationX, translationY);
    }

    public void restore(Memento memento){
        scaleFactor = memento.getScaleFactor();
        translationX = memento.getTranslationX();
        translationY = memento.getTranslationY();
        notifyObservers();
    }
}

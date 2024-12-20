package com.example.labo5_log121.models;

import com.example.labo5_log121.commands.Memento;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class PerspectiveModel extends Subject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double scaleFactor;
    private double translationX;
    private double translationY;
    private boolean isUndoDisabled;
    private boolean isRedoDisabled;
    private String uniqueId;
    private final ImageModel image;

    public PerspectiveModel(ImageModel image) {
        this.scaleFactor = 1.0;
        this.translationX = 0.0;
        this.translationY = 0.0;
        this.isUndoDisabled = true;
        this.isRedoDisabled = true;
        this.image = image;
        this.uniqueId = UUID.randomUUID().toString();
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

    public boolean isUndoDisabled() {
        return isUndoDisabled;
    }

    public boolean isRedoDisabled() {
        return isRedoDisabled;
    }

    public ImageModel getImage() {
        return image;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
        notifyObservers("scaleFactorChanged");
    }

    public void setTranslation(double translationX, double translationY) {
        this.translationX = translationX;
        this.translationY = translationY;
        notifyObservers("translationChanged");
    }

    public void setUndoButtonDisabled(boolean disabled) {
        this.isUndoDisabled = disabled;
        notifyObservers("undoButtonStateChanged");
    }

    public void setRedoButtonDisabled(boolean disabled) {
        this.isRedoDisabled = disabled;
        notifyObservers("redoButtonStateChanged");
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Memento createMemento(){
        return new Memento(scaleFactor, translationX, translationY);
    }

    public void restore(Memento memento){
        scaleFactor = memento.getScaleFactor();
        translationX = memento.getTranslationX();
        translationY = memento.getTranslationY();
        notifyObservers("scaleFactorChanged");
        notifyObservers("translationChanged");
    }
}

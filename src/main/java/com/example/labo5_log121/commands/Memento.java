package com.example.labo5_log121.commands;

import java.io.Serial;
import java.io.Serializable;

public class Memento implements Serializable {
    private final double scaleFactor;
    private final double translationY;
    private final double translationX;
    @Serial
    private static final long serialVersionUID = 1L;

    public Memento(double scaleFactor, double translationX, double translationY) {
        this.scaleFactor = scaleFactor;
        this.translationX = translationX;
        this.translationY = translationY;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public double getTranslationY() {
        return translationY;
    }

    public double getTranslationX() {
        return translationX;
    }
}

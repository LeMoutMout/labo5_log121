package com.example.labo5_log121.commands;


import java.io.Serializable;

public class Memento implements Serializable {
    private final double scaleFactor;
    private final double translationAxeY;
    private final double translationAxeX;
    private static final long serialVersionUID = 1L;

    public Memento(double scaleFactor, double translationAxeX, double translationAxeY) {
        this.scaleFactor = scaleFactor;
        this.translationAxeX = translationAxeX;
        this.translationAxeY = translationAxeY;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public double getTranslationY() {
        return translationAxeY;
    }

    public double getTranslationX() {
        return translationAxeX;
    }
}

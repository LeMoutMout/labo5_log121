package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;

public class Memento {
    private final double scaleFactor;
    private final double translationAxeY;
    private final double translationAxeX;
    private final PerspectiveModel perspectiveModel;

    public Memento(double scaleFactor, double translationAxeX, double translationAxeY) {
        this.scaleFactor = scaleFactor;
        this.translationAxeX = translationAxeX;
        this.translationAxeY = translationAxeY;
        this.perspectiveModel = perspectiveModel;
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

    public void restore() {
        perspectiveModel.restore(this);
    }
}

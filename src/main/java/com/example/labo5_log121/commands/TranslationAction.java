package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class TranslationAction extends AbstractAction {
    private final double deltaX;
    private final double deltaY;

    public TranslationAction(PerspectiveModel perspective, double deltaX, double deltaY) {
        super(perspective);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void actionPerformed(Event event) {
        double newTranslationX = perspective.getTranslationX() + deltaX;
        double newTranslationY = perspective.getTranslationY() + deltaY;

        perspective.setTranslation(newTranslationX, newTranslationY);

        System.out.println("Translation appliquée : X=" + newTranslationX + ", Y=" + newTranslationY);
    }
}


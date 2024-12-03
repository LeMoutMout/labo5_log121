package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public class CopyAction extends AbstractAction {

    private static CopyAction copyActionInstance;
    private static double translationX;
    private static double translationY;
    private static double scaleFactor;

    public CopyAction(PerspectiveModel perspective) {
        super(perspective);
    }

    public static CopyAction getInstance(PerspectiveModel perspective) {
        if (copyActionInstance == null) {
            copyActionInstance = new CopyAction(perspective);
            translationX = perspective.getTranslationX();
            translationY = perspective.getTranslationY();
            scaleFactor = perspective.getScaleFactor();
        }
        return copyActionInstance;
    }

    // Méthode pour mettre à jour la perspective
    public void updatePerspective(PerspectiveModel newPerspective) {
        this.perspective = newPerspective;
    }

    @Override
    public void actionPerformed(Event event) {
        translationX = perspective.getTranslationX();
        translationY = perspective.getTranslationY();
        scaleFactor = perspective.getScaleFactor();
    }

    public double getTranslationX() {
        return translationX;
    }
    public double getTranslationY() {
        return translationY;
    }
    public double getScaleFactor() {
        return scaleFactor;
    }
}

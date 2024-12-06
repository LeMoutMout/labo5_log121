package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;

public class CopyAction extends AbstractAction {

    private static CopyAction copyActionInstance;

    private CopyAction(PerspectiveModel perspective) {
        super(perspective);
    }

    public static CopyAction getInstance(PerspectiveModel perspective) {
        if (copyActionInstance == null) {
            copyActionInstance = new CopyAction(perspective);
        }
        return copyActionInstance;
    }

    public void updatePerspective(PerspectiveModel newPerspective) {
        this.perspective = newPerspective;
    }

    @Override
    public void actionPerformed(Event event) {}

    public double getTranslationX() {
        return perspective.getTranslationX();
    }
    public double getTranslationY() {
        return perspective.getTranslationY();
    }
    public double getScaleFactor() {
        return perspective.getScaleFactor();
    }
}

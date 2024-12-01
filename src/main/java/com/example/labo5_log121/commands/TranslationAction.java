package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class TranslationAction extends AbstractAction {
    private final double initialDeltaX;
    private final double initialDeltaY;

    public TranslationAction(PerspectiveModel perspective, double initialDeltaX, double initialDeltaY) {
        super(perspective);
        this.initialDeltaX = initialDeltaX;
        this.initialDeltaY = initialDeltaY;
    }

    @Override
    public void actionPerformed(Event event) {
        // Sauvegarde de l'état actuel dans le Memento
        CommandManager.getInstance().add(perspective,perspective.createMemento());

        MouseEvent mouseEvent = (MouseEvent) event;

        double deltaX = mouseEvent.getSceneX() - initialDeltaX;
        double deltaY = mouseEvent.getSceneY() - initialDeltaY;

        perspective.setTranslation(deltaX, deltaY);
        perspective.setUndoButtonDisabled(false);
    }
}


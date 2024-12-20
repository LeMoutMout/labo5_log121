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
        // Sauvegarder l'état après modification
        CommandManager.getInstance().add(perspective.getUniqueId(), perspective.createMemento());

        MouseEvent mouseEvent = (MouseEvent) event;

        // Calculer la nouvelle translation
        double deltaX = mouseEvent.getSceneX() - initialDeltaX;
        double deltaY = mouseEvent.getSceneY() - initialDeltaY;

        // Mettre à jour le modèle
        perspective.setTranslation(
                perspective.getTranslationX() + deltaX,
                perspective.getTranslationY() + deltaY
        );
        perspective.setUndoButtonDisabled(false);
    }
}


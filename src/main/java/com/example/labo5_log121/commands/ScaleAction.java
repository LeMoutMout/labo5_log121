package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import javafx.event.Event;
import javafx.scene.input.ScrollEvent;

public class ScaleAction extends AbstractAction {

    private final Double manualZoomFactor;

    // Constructeur pour l'utilisation avec la molette de souris
    public ScaleAction(PerspectiveModel perspective) {
        super(perspective);
        this.manualZoomFactor = null;  // Aucun zoom manuel, ce sera calculé via l'événement
    }

    // Constructeur pour l'utilisation avec le slider
    public ScaleAction(PerspectiveModel perspective, double manualZoomFactor) {
        super(perspective);
        this.manualZoomFactor = manualZoomFactor;  // Facteur de zoom manuel
    }

    @Override
    public void actionPerformed(Event event) {
        // Sauvegarde de l'état actuel dans le Memento
        CommandManager.getInstance().add(perspective,perspective.createMemento());

        double newZoom;


        if(event instanceof ScrollEvent) {
            ScrollEvent scrollEvent = (ScrollEvent) event;
            double delta = scrollEvent.getDeltaY();
            double currentZoom = perspective.getScaleFactor();
            newZoom = currentZoom + delta / 100;
            newZoom = Math.max(0.1, Math.min(5.0, newZoom));
        } else {
            newZoom = manualZoomFactor;
        }

        perspective.setScaleFactor(newZoom);
        perspective.setUndoButtonDisabled(false);
    }
}

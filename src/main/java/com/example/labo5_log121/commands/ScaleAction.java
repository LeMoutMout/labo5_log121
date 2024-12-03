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
        CommandManager.getInstance().add(perspective.getUniqueId(),perspective.createMemento());

        double newZoom;

        if(event instanceof ScrollEvent) {
            ScrollEvent scrollEvent = (ScrollEvent) event;
            double delta = scrollEvent.getDeltaY();
            double currentZoom = perspective.getScaleFactor();

            // Calculer le nouvel incrément (0.1 = 10 %)
            double increment = (delta > 0) ? 0.1 : -0.1;
            newZoom = currentZoom + increment;

            // Arrondir le nouveau facteur de zoom au multiple de 0.1 le plus proche
            newZoom = Math.round(newZoom * 10) / 10.0;

            // Limiter le zoom entre 0.1 (10 %) et 5.0 (500 %)
            newZoom = Math.max(0.1, Math.min(5.0, newZoom));
        } else {
            newZoom = (double) (Math.round(manualZoomFactor / 10) * 10) / 100;;
        }

        perspective.setScaleFactor(newZoom);
        perspective.setUndoButtonDisabled(false);
    }
}

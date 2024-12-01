package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
<<<<<<< HEAD
import com.example.labo5_log121.views.PerspectiveView;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.ScrollEvent;
=======
import javafx.event.ActionEvent;
import javafx.event.Event;
>>>>>>> thomas

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
<<<<<<< HEAD

        // Sauvegarde de l'état actuel dans le Memento
        CommandManager.getInstance().add(perspective,perspective.createMemento());

        double newZoom;

        double zoomMin = 0.1;
        double zoomMax = 5.0;

        if(event instanceof ScrollEvent) {
            ScrollEvent scrollEvent = (ScrollEvent) event;
            double delta = scrollEvent.getDeltaY();
            double currentZoom = perspective.getScaleFactor();
            newZoom = currentZoom + delta / 100;
            newZoom = Math.max(0.1, Math.min(5.0, newZoom));
        } else {
            newZoom = manualZoomFactor;
        }


        // Met à jour le facteur de zoom
        perspective.setScaleFactor(newZoom);
=======
        //perspective.setScaleFactor(event.get);
>>>>>>> thomas
    }
}

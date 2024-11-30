package com.example.labo5_log121.commands;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

public class ScaleAction extends AbstractAction {

    public ScaleAction(PerspectiveModel perspective) {
        super(perspective);
    }

    @Override
    public void actionPerformed(Event event) {
        CommandManager.getInstance().add(perspective.createMemento());
        Object source = event.getSource();

        if (source instanceof Node) {
            Node sourceNode = (Node) source;
            PerspectiveView view = (PerspectiveView) sourceNode.getScene().getRoot();

            ScrollEvent scrollEvent = (ScrollEvent) event;
            double delta = scrollEvent.getDeltaY();
            double currentZoom = perspective.getScaleFactor();
            double newZoom = currentZoom + delta / 10;

            newZoom = Math.max(view.getZoomSlider().getMin(), Math.min(view.getZoomSlider().getMax(), newZoom));

            double zoomFactor = newZoom / 100.0;

            System.out.println(zoomFactor);

            perspective.setScaleFactor(zoomFactor);
        }
    }
}

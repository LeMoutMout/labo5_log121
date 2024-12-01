package com.example.labo5_log121.controllers;

import com.example.labo5_log121.commands.*;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class PerspectiveController {
    private final PerspectiveView view;
    private final PerspectiveModel perspectiveModel;
    private double initialMouseX;
    private double initialMouseY;

    public PerspectiveController(PerspectiveView view, ImageModel imageModel) {
        this.view = view;
        perspectiveModel = new PerspectiveModel(imageModel);
        perspectiveModel.addObserver(view);
        initialize();
    }

    private void initialize() {
        //récupère la bottomBar
        HBox bottomBar = view.getBottomBar();

        //récupère les éléments de la bottomBar
        Button undoButton = (Button) bottomBar.getChildren().get(3);
        Button redoButton = (Button) bottomBar.getChildren().get(4);
        Slider zoomSlider = (Slider) bottomBar.getChildren().get(6);

        // Zoom via le slider
        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double zoomFactor = newVal.doubleValue() / 100.0;
            ScaleAction scaleAction = new ScaleAction(perspectiveModel, zoomFactor);
            scaleAction.actionPerformed(null);
        });

        // Zoom avec la molette de la souris
        view.setOnScroll(event -> {
            ScaleAction scaleAction = new ScaleAction(perspectiveModel);
            scaleAction.actionPerformed(event);
        });

        // Clique sur le bouton undo
        undoButton.setOnMouseClicked(event -> {
            UndoAction undoAction = new UndoAction(perspectiveModel);
            undoAction.actionPerformed(event);
        });

        // Clique sur le bouton redo
        redoButton.setOnMouseClicked(event -> {
            RedoAction redoAction = new RedoAction(perspectiveModel);
            redoAction.actionPerformed(event);
        });

        //gestion des translations avec la souris
        view.getImageView().setOnMouseClicked(event -> {
            initialMouseX = event.getSceneX();
            initialMouseY = event.getSceneY();
        });

        view.getImageView().setOnMouseDragged(event -> {
            TranslationAction translationAction = new TranslationAction(perspectiveModel, initialMouseX, initialMouseY);
            translationAction.actionPerformed(event);

            /*initialMouseX = event.getSceneX();
            initialMouseY = event.getSceneY();*/
        });
    }
}


package com.example.labo5_log121.controllers;

import com.example.labo5_log121.commands.ScaleAction;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.beans.value.ChangeListener;


import java.io.File;

public class PerspectiveController {
    private final PerspectiveView view;
    private final PerspectiveModel perspectiveModel;

    public PerspectiveController(PerspectiveView view, ImageModel imageModel) {
        this.view = view;
        perspectiveModel = new PerspectiveModel(imageModel);
        perspectiveModel.addObserver(view);
        initialize();
    }

    private void initialize() {

        // Zoom via le slider
        view.getZoomSlider().valueProperty().addListener((obs, oldVal, newVal) -> {
            double zoomFactor = newVal.doubleValue() / 100.0;
            ScaleAction scaleAction = new ScaleAction(perspectiveModel, zoomFactor);
            scaleAction.actionPerformed(null);
        });

        // Zoom avec la molette de la souris
        view.setOnScroll(event -> {
            ScaleAction scaleAction = new ScaleAction(perspectiveModel);
            scaleAction.actionPerformed(event);
        });
    }
}


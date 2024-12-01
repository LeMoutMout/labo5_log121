package com.example.labo5_log121.controllers;

import com.example.labo5_log121.commands.CommandManager;
import com.example.labo5_log121.commands.ScaleAction;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
=======
import com.example.labo5_log121.commands.TranslationAction;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
>>>>>>> thomas
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.File;

public class PerspectiveController {
    private final PerspectiveView view;
    private final PerspectiveModel perspectiveModel;
=======
    private PerspectiveModel perspectiveModel;
    private String lastLoadedImagePath = null; // Pour stocker le chemin de la dernière image chargée
    private double initialMouseX, initialMouseY; // Pour stocker la position initiale de la souris lors d'une translation
>>>>>>> thomas

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
            CommandManager.getInstance().undo(perspectiveModel);
        });

        // Clique sur le bouton redo
        redoButton.setOnMouseClicked(event -> {
            CommandManager.getInstance().redo(perspectiveModel);
        });
    }
}


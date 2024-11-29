package com.example.labo5_log121.controllers;

import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.Perspective;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PerspectiveController {
    private PerspectiveView view;

    public PerspectiveController(PerspectiveView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        view.getMenuBar().getMenus().get(0).getItems().get(1).setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir une image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                addNewPerspective(file.getAbsolutePath());
            }
        });

        /*view.getZoomSlider().valueProperty().addListener((obs, oldVal, newVal) -> {
            Tab selectedTab = view.getTabPane().getSelectionModel().getSelectedItem();
            if (selectedTab != null && selectedTab.getContent() instanceof Perspective) {
                Perspective perspective = (Perspective) selectedTab.getContent();
                perspective.getImageModel().setScaleFactor(newVal.doubleValue() / 100);
            }
        });*/
    }

    private void addNewPerspective(String imagePath) {
        ImageModel imageModel = new ImageModel(imagePath);
        Perspective perspective = new Perspective(imageModel);

        //Tab tab = new Tab("Nouvelle Perspective", perspective);
        //view.getTabPane().getTabs().add(tab);
        //view.getTabPane().getSelectionModel().select(tab);
    }
}


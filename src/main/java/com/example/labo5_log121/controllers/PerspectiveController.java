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
    private String lastLoadedImagePath = null;// Pour stocker l'image chargée

    public PerspectiveController(PerspectiveView view, ImageModel imageModel) {
        this.view = view;
        perspectiveModel = new PerspectiveModel(imageModel);
        perspectiveModel.addObserver(view);
        initialize();
        System.out.println("vouvou");
    }

    private void initialize() {

        // Affichage des boutonnn en bas pourle zomm etc...
        /*view.getTabPane().getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null && !"read-only".equals(newTab.getUserData())) {
                view.showBottomBar(true); // Afficher la barre d'outils
            } else {
                view.showBottomBar(false); // Cacher la barre d'outils
            }
        });


        // Nouveau
        view.getMenuBar().getMenus().get(0).getItems().get(0).setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner une image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                lastLoadedImagePath = file.getAbsolutePath();
                newImage(file.getAbsolutePath());
            }
        });

        // Nouvelle perspective
        view.getMenuBar().getMenus().get(1).getItems().get(0).setOnAction(event -> {
            addNewPerspective();
        });

        // Zoom
        view.getZoomSlider().valueProperty().addListener((obs, oldVal, newVal) -> {

            Tab selectedTab = view.getTabPane().getSelectionModel().getSelectedItem();
            if (selectedTab != null && selectedTab.getContent() instanceof Pane) {
                Pane pane = (Pane) selectedTab.getContent();
                if (!pane.getChildren().isEmpty() && pane.getChildren().get(0) instanceof ImageView) {
                    ImageView imageView = (ImageView) pane.getChildren().get(0);

                    double scale = newVal.doubleValue() / 100; // Conversion en pourcentage
                    imageView.setScaleX(scale);
                    imageView.setScaleY(scale);

                    System.out.println("Zoom appliqué : " + scale); // Debugging
                }
            } else {
                System.out.println("Aucun onglet actif ou pas d'image à zoomer.");
            }
        });

        // Zoom avec souris
        view.getTabPane().setOnScroll(event -> {
            Tab selectedTab = view.getTabPane().getSelectionModel().getSelectedItem();

            if (selectedTab != null && !"read-only".equals(selectedTab.getUserData())) {
                Pane pane = (Pane) selectedTab.getContent();
                if (!pane.getChildren().isEmpty() && pane.getChildren().get(0) instanceof ImageView) {
                    new ScaleAction(perspectiveModel).actionPerformed(event);
                }
            }
        });*/
    }

    private void newImage(String imagePath) {
        ImageModel imageModel = new ImageModel(imagePath);
        imageModel.setImagePath(imagePath);

        ImageView imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        Pane pane = new Pane(imageView);
        pane.setPrefSize(800, 600);

        Tab tab = new Tab(new File(imagePath).getName());
        tab.setContent(pane);

        tab.setUserData("read-only");
        tab.setClosable(false);

        //view.getTabPane().getTabs().add(tab);
        //view.getTabPane().getSelectionModel().select(tab);
    }

    private void addNewPerspective() {
        ImageModel imageModel = new ImageModel(lastLoadedImagePath);
        String imagePath = imageModel.getImagePath();
        ImageView imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        Pane pane = new Pane(imageView);
        pane.setPrefSize(800, 600);

        Tab tab = new Tab("Nouvelle Perspective");
        tab.setContent(pane);

        //view.getTabPane().getTabs().add(tab);
        //view.getTabPane().getSelectionModel().select(tab);
    }
}


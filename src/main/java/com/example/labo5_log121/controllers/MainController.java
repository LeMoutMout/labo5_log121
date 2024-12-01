package com.example.labo5_log121.controllers;

import com.example.labo5_log121.commands.ScaleAction;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.views.MainView;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {
    private MainView mainView;
    private String lastLoadedImagePath;
    private ImageModel lastLoadedImageModel;

    public MainController(MainView mainView) {
        this.mainView = mainView;
        initialize();
    }

    private void initialize() {
        // Gestion des événements du menu

        // Menu "Nouveau" pour charger une image
        mainView.getMenuBar().getMenus().get(0).getItems().get(0).setOnAction(event -> openImage());

        // Menu "Nouvelle perspective"
        mainView.getMenuBar().getMenus().get(1).getItems().get(0).setOnAction(event -> createNewPerspective());
    }

    // Méthode pour ouvrir une image et l'afficher dans un onglet Thumbnail
    private void openImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            lastLoadedImagePath = file.getAbsolutePath();
            addThumbnailTab(lastLoadedImagePath); // Ajouter l'onglet d'image statique
            ImageModel imageModel = new ImageModel(lastLoadedImagePath);
            imageModel.setImagePath(lastLoadedImagePath);
            lastLoadedImageModel = imageModel;
        }
    }

    // Méthode pour ajouter un onglet avec une image statique (Thumbnail)
    private void addThumbnailTab(String imagePath) {
        File file = new File(imagePath);
        ThumbnailView thumbnailView = new ThumbnailView(imagePath);
        TabPane tabPane = mainView.getTabPane();
        Tab thumbnailTab = new Tab(file.getName());
        thumbnailTab.setClosable(false); // L'onglet ne peut pas être fermé
        thumbnailTab.setContent(thumbnailView);
        tabPane.getTabs().add(thumbnailTab);
    }

    // Méthode pour créer une nouvelle perspective et l'ajouter dans l'onglet
    private void createNewPerspective() {
        if (!lastLoadedImagePath.isEmpty() && lastLoadedImageModel != null) {
            PerspectiveView perspectiveView = new PerspectiveView(lastLoadedImagePath);
            new PerspectiveController(perspectiveView, lastLoadedImageModel);
            TabPane tabPane = mainView.getTabPane();
            Tab perspectiveTab = new Tab("Perspective");
            perspectiveTab.setClosable(true); // L'onglet peut être fermé
            perspectiveTab.setContent(perspectiveView);
            tabPane.getTabs().add(perspectiveTab);
        }
    }
}

package com.example.labo5_log121.controllers;

import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.MainView;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        MenuItem newProjectMenuItem = mainView.getNewProjectMenuItem();
        newProjectMenuItem.setOnAction(event -> openImage());

        // Menu "Ouvrir"
        MenuItem openPerspectiveMenuItem = mainView.getOpenPerspectiveMenuItem();
        openPerspectiveMenuItem.setOnAction(event -> openState());

        // Menu "Sauvegarder"
        MenuItem saveMenuItem = mainView.getSaveMenuItem();
        saveMenuItem.setOnAction(event -> saveState());

        // Menu "Fermer tout"
        MenuItem closeMenuItem = mainView.getCloseMenuItem();
        closeMenuItem.setOnAction(event -> closeTab());

        // Menu "Quitter"
        MenuItem exit = mainView.getExitMenuItem();
        exit.setOnAction(event -> System.exit(0));

        // Menu "Nouvelle perspective"
        MenuItem newPerspectiveMenuItem = mainView.getNewPerspectiveMenuItem();
        newPerspectiveMenuItem.setOnAction(event -> createNewPerspective());
    }

    // Méthode pour ouvrir une image et l'afficher dans un onglet Thumbnail
    private void openImage() {
        if(mainView.getTabPane().getTabs().isEmpty()){
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
                MenuItem newPerspectiveMenuItem = mainView.getNewPerspectiveMenuItem();
                MenuItem saveMenuItem = mainView.getSaveMenuItem();
                MenuItem closeMenuItem = mainView.getCloseMenuItem();
                newPerspectiveMenuItem.setDisable(false);
                saveMenuItem.setDisable(false);
                closeMenuItem.setDisable(false);
            }
        } else {
            // à faire
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
            tabPane.getSelectionModel().select(perspectiveTab);
        }
    }

    // Méthode pour sauvegarder l'état
    private void saveState() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save State");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                List<PerspectiveModel> perspectives = getAllPerspectiveModels();
                oos.writeObject(perspectives); // Sérialiser la liste des modèles
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour ouvrir l'état
    private void openState() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open State");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.ser"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<PerspectiveModel> perspectives = (List<PerspectiveModel>) ois.readObject();
                restoreAllPerspectives(perspectives);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour récupérer tous les modèles de perspective
    private List<PerspectiveModel> getAllPerspectiveModels() {
        List<PerspectiveModel> perspectives = new ArrayList<>();
        for (Tab tab : mainView.getTabPane().getTabs()) {
            if (tab.getContent() instanceof PerspectiveView) {
                PerspectiveView view = (PerspectiveView) tab.getContent();
                if (view.getLastSubject() instanceof PerspectiveModel) {
                    perspectives.add((PerspectiveModel) view.getLastSubject());
                }
            }
        }
        return perspectives;
    }

    // Méthode pour restaurer toutes les perspectives
    private void restoreAllPerspectives(List<PerspectiveModel> perspectives) {
        mainView.getTabPane().getTabs().clear();
        for (PerspectiveModel model : perspectives) {
            PerspectiveView view = new PerspectiveView(model.getImage().getImagePath());
            new PerspectiveController(view, model.getImage());

            // Applique les états restaurés (zoom, translation)
            view.update(model);

            Tab tab = new Tab("Nouvelle Perspective");
            tab.setClosable(true);
            tab.setContent(view);
            mainView.getTabPane().getTabs().add(tab);
        }
    }

    private void closeTab() {
        mainView.getTabPane().getTabs().clear();
    }
}

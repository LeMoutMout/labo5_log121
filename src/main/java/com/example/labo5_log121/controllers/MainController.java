package com.example.labo5_log121.controllers;

import com.example.labo5_log121.SaveData;
import com.example.labo5_log121.commands.CommandManager;
import com.example.labo5_log121.commands.Memento;
import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.views.MainView;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainController {
    private final MainView mainView;
    private String lastLoadedImagePath;
    private ImageModel lastLoadedImageModel;
    private static int perspectiveNumber = 1;

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
            Tab perspectiveTab = new Tab("Perspective " + perspectiveNumber);
            perspectiveNumber++;
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
                List<String> thumbnailPaths = getAllThumbnailPaths();
                Map<PerspectiveModel, Stack<Memento>> undoStacks = CommandManager.getInstance().getUndoStacks();

                SaveData saveData = new SaveData(perspectives, thumbnailPaths, undoStacks);
                oos.writeObject(saveData);
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
                SaveData saveData = (SaveData) ois.readObject();

                restoreAllThumbnails(saveData.getThumbnailPaths());
                restoreAllPerspectives(saveData.getPerspectiveModels());
                CommandManager.getInstance().restoreUndoStacks(saveData.getUndoStacks());

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

    private List<String> getAllThumbnailPaths() {
        List<String> thumbnailPaths = new ArrayList<>();
        for (Tab tab : mainView.getTabPane().getTabs()) {
            if (tab.getContent() instanceof ThumbnailView) {
                ThumbnailView thumbnailView = (ThumbnailView) tab.getContent();
                ImageView imageView = thumbnailView.getImageView();
                Image image = imageView.getImage();
                String imageUrl = image.getUrl();
                thumbnailPaths.add(imageUrl.substring(5));
            }
        }
        return thumbnailPaths;
    }

    private void restoreAllPerspectives(List<PerspectiveModel> perspectives) {
        perspectiveNumber = 1;

        for (PerspectiveModel model : perspectives) {
            PerspectiveView view = new PerspectiveView(model.getImage().getImagePath());
            PerspectiveController perspectiveController = new PerspectiveController(view, model.getImage());

            perspectiveController.setPerspectiveModel(model);

            // Appliquer l'état restauré (zoom, translation)
            view.update(model);

            // Crée l'onglet de perspective
            Tab tab = new Tab("Perspective " + perspectiveNumber++);
            tab.setClosable(true);
            tab.setContent(view);
            mainView.getTabPane().getTabs().add(tab);
        }
    }


    private void restoreAllThumbnails(List<String> thumbnailPaths) {
        mainView.getTabPane().getTabs().clear();
        for (String imagePath : thumbnailPaths) {
            addThumbnailTab(imagePath);
        }
    }

    private void closeTab() {
        perspectiveNumber = 1;
        mainView.getCloseMenuItem().setDisable(true);
        mainView.getSaveMenuItem().setDisable(true);
        mainView.getNewPerspectiveMenuItem().setDisable(true);
        mainView.getTabPane().getTabs().clear();
    }
}

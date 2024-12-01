package com.example.labo5_log121.controllers;

import com.example.labo5_log121.commands.CommandManager;
<<<<<<< HEAD
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
<<<<<<< HEAD
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
<<<<<<< HEAD

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
=======
        // Affichage des boutons en bas (Zoom, etc.)
        view.getTabPane().getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null && !"read-only".equals(newTab.getUserData())) {
                view.showBottomBar(true); // Afficher la barre d'outils
            } else {
                view.showBottomBar(false); // Cacher la barre d'outils
            }
        });

        // Gestion du menu "Nouveau"
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

        // Gestion du menu "Nouvelle Perspective"
        view.getMenuBar().getMenus().get(1).getItems().get(0).setOnAction(event -> addNewPerspective());

        // Gestion du Zoom
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

        // Gestion des translations avec la souris
        view.getTabPane().setOnMousePressed(this::handleMousePressed);
        view.getTabPane().setOnMouseDragged(this::handleMouseDragged);
    }

    private void handleMousePressed(MouseEvent event) {
        initialMouseX = event.getSceneX();
        initialMouseY = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        Tab selectedTab = view.getTabPane().getSelectionModel().getSelectedItem();
        if (selectedTab != null && selectedTab.getUserData() instanceof PerspectiveModel) {
            perspectiveModel = (PerspectiveModel) selectedTab.getUserData();

            double deltaX = event.getSceneX() - initialMouseX;
            double deltaY = event.getSceneY() - initialMouseY;

            perspectiveModel.setTranslation(
                    perspectiveModel.getTranslationX() + deltaX,
                    perspectiveModel.getTranslationY() + deltaY
            );

            initialMouseX = event.getSceneX();
            initialMouseY = event.getSceneY();
        }
    }





    private void newImage(String imagePath) {
        ImageModel imageModel = new ImageModel(imagePath);

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

        view.getTabPane().getTabs().add(tab);
        view.getTabPane().getSelectionModel().select(tab);
    }

    private void addNewPerspective() {
        ImageModel imageModel = new ImageModel(lastLoadedImagePath);
        PerspectiveModel newPerspectiveModel = new PerspectiveModel(imageModel);

        ImageView imageView = new ImageView("file:" + imageModel.getImagePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        Pane pane = new Pane(imageView);
        pane.setPrefSize(800, 600);

        Tab tab = new Tab("Nouvelle Perspective");
        tab.setContent(pane);
        tab.setUserData(newPerspectiveModel);

        newPerspectiveModel.addObserver(view);

        view.getTabPane().getTabs().add(tab);
        view.getTabPane().getSelectionModel().select(tab);

        view.enableDrag(imageView, newPerspectiveModel);
>>>>>>> thomas
    }
}


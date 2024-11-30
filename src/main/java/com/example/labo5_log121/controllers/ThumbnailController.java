package com.example.labo5_log121.controllers;

import com.example.labo5_log121.models.ImageModel;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ThumbnailController {
    private ThumbnailView view;
    private String lastLoadedImagePath = null;

    public ThumbnailController(ThumbnailView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        // Nouveau
        /*view.getMenuBar().getMenus().get(0).getItems().get(0).setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionnerune image");
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

        //PerspectiveView perspectiveView = new PerspectiveView(imageView);
        new PerspectiveController(perspectiveView, imageModel);
        System.out.println("yoyo");

        Pane pane = new Pane(imageView);
        pane.setPrefSize(800, 600);

        Tab tab = new Tab("Nouvelle Perspective");
        tab.setContent(pane);

        //view.getTabPane().getTabs().add(tab);
        //view.getTabPane().getSelectionModel().select(tab);
    }
}

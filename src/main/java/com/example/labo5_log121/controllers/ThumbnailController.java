package com.example.labo5_log121.controllers;

import com.example.labo5_log121.models.ImageModel;
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

    public ThumbnailController(ThumbnailView view) {
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
                addNewTab(file.getAbsolutePath());
            }
        });
    }

    private void addNewTab(String imagePath) {
        ImageModel imageModel = new ImageModel(imagePath);
        imageModel.setImagePath(imagePath);


        Image image = new Image("file:" + imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        enableDrag(imageView);

        Pane pane = new Pane(imageView);
        pane.setPrefSize(800, 600);

        Tab tab = new Tab("Nouvelle Perspective");
        tab.setContent(pane);
        view.getTabPane().getTabs().add(tab);
        view.getTabPane().getSelectionModel().select(tab);
    }

    private void enableDrag(ImageView imageView) {
        final double[] offsetX = {0};
        final double[] offsetY = {0};

        imageView.setOnMousePressed(event -> {
            System.out.println("Mouse pressed at: " + event.getSceneX() + ", " + event.getSceneY());
            offsetX[0] = event.getSceneX() - imageView.getLayoutX();
            offsetY[0] = event.getSceneY() - imageView.getLayoutY();
        });

        imageView.setOnMouseDragged(event -> {
            System.out.println("Mouse dragged to: " + event.getSceneX() + ", " + event.getSceneY());
            imageView.setLayoutX(event.getSceneX() - offsetX[0]);
            imageView.setLayoutY(event.getSceneY() - offsetY[0]);
        });
    }
}

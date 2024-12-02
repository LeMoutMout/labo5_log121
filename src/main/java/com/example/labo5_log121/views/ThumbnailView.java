package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ThumbnailView extends Pane {

    private final ImageView imageView;

    public ThumbnailView(String imagePath) {
        imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        getChildren().add(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

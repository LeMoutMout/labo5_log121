package com.example.labo5_log121.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ThumbnailView extends VBox {
    private ImageView imageView;

    public ThumbnailView(String imagePath) {
        imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(150); // Taille de la vignette
        imageView.setPreserveRatio(true);

        getChildren().add(imageView);
    }

    public void setImage(String imagePath) {
        imageView.setImage(new Image(imagePath));
    }
}

package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

public class ThumbnailView extends BorderPane {
    private final ImageView imageView;
    private final MenuBar menuBar;

    public ThumbnailView() {

        menuBar = new MenuBar();

        Menu fileMenu = new Menu("Fichier");
        fileMenu.getItems().addAll(
                new MenuItem("Nouveau"),
                new MenuItem("Ouvrir"),
                new MenuItem("Sauvegarder"),
                new MenuItem("Fermer tout"),
                new MenuItem("Quitter")
        );

        Menu perspectiveMenu = new Menu("Perspective");
        perspectiveMenu.getItems().add(new MenuItem("Nouvelle perspective"));

        Menu helpMenu = new Menu("Aide");

        menuBar.getMenus().addAll(fileMenu, perspectiveMenu, helpMenu);
        setTop(menuBar);

        imageView = new ImageView();
        setCenter(imageView);
    }

    public ImageView getImageView() {
        return imageView;
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }
}

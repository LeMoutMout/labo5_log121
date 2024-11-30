package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

public class ThumbnailView extends BorderPane {
    private final MenuBar menuBar;
    private final TabPane tabPane;

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

        tabPane = new TabPane();
        setCenter(tabPane);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }
}

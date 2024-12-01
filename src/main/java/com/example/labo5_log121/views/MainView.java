package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
    private final MenuBar menuBar;
    private final TabPane tabPane;
    private final MenuItem newProjectMenuItem;
    private final MenuItem openPerspectiveMenuItem;
    private final MenuItem saveMenuItem;
    private final MenuItem closeMenuItem;
    private final MenuItem newPerspectiveMenuItem;
    private final MenuItem exitMenuItem;


    public MainView() {
        menuBar = new MenuBar();

        Menu fileMenu = new Menu("Fichier");

        newProjectMenuItem = new MenuItem("Nouveau");
        openPerspectiveMenuItem = new MenuItem("Ouvrir");
        saveMenuItem = new MenuItem("Sauvegarder");
        saveMenuItem.setDisable(true);
        closeMenuItem = new MenuItem("Fermer tout");
        closeMenuItem.setDisable(true);
        exitMenuItem = new MenuItem("Quitter");


        fileMenu.getItems().addAll(
                newProjectMenuItem,
                openPerspectiveMenuItem,
                saveMenuItem,
                closeMenuItem,
                exitMenuItem
        );

        Menu perspectiveMenu = new Menu("Perspective");

        newPerspectiveMenuItem = new MenuItem("Nouvelle perspective");
        newPerspectiveMenuItem.setDisable(true);

        perspectiveMenu.getItems().add(newPerspectiveMenuItem);

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
    public MenuItem getNewProjectMenuItem() {
        return newProjectMenuItem;
    }
    public MenuItem getOpenPerspectiveMenuItem() {
        return openPerspectiveMenuItem;
    }
    public MenuItem getExitMenuItem() {
        return exitMenuItem;
    }
    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }
    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }
    public MenuItem getNewPerspectiveMenuItem() {
        return newPerspectiveMenuItem;
    }
}

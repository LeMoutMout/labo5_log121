package com.example.labo5_log121.controllers;

import com.example.labo5_log121.models.MainModel;
import com.example.labo5_log121.views.MainView;
import javafx.scene.control.MenuItem;

public class MainController {
    private final MainView mainView;
    private MainModel mainModel;

    public MainController(MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModel();
        mainModel.addObserver(mainView);
        initialize();
    }

    private void initialize() {
        // Gestion des événements du menu

        // Menu "Nouveau" pour charger une image
        MenuItem newProjectMenuItem = mainView.getNewProjectMenuItem();
        newProjectMenuItem.setOnAction(event -> mainModel.openImage());

        // Menu "Ouvrir"
        MenuItem openPerspectiveMenuItem = mainView.getOpenPerspectiveMenuItem();
        openPerspectiveMenuItem.setOnAction(event -> mainModel.openState());

        // Menu "Sauvegarder"
        MenuItem saveMenuItem = mainView.getSaveMenuItem();
        saveMenuItem.setOnAction(event -> mainModel.saveState());

        // Menu "Fermer tout"
        MenuItem closeMenuItem = mainView.getCloseMenuItem();
        closeMenuItem.setOnAction(event -> mainModel.closeTab());

        // Menu "Quitter"
        MenuItem exit = mainView.getExitMenuItem();
        exit.setOnAction(event -> mainModel.exit());

        // Menu "Nouvelle perspective"
        MenuItem newPerspectiveMenuItem = mainView.getNewPerspectiveMenuItem();
        newPerspectiveMenuItem.setOnAction(event -> mainModel.addPerspectiveTab());
    }
}

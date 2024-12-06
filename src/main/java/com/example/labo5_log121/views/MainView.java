package com.example.labo5_log121.views;

import com.example.labo5_log121.models.MainModel;
import com.example.labo5_log121.models.Subject;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane implements Observer{
    private final TabPane tabPane;
    private final MenuItem newProjectMenuItem;
    private final MenuItem openPerspectiveMenuItem;
    private final MenuItem saveMenuItem;
    private final MenuItem closeMenuItem;
    private final MenuItem newPerspectiveMenuItem;
    private final MenuItem exitMenuItem;


    public MainView() {
        MenuBar menuBar = new MenuBar();

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

    @Override
    public void update(Subject subject, String message) {
        if(subject instanceof MainModel mainModel){
            switch (message){
                case "addThumbnailTab" :
                    Tab thumbnailTab = new Tab(mainModel.getLastLoadedImagePath());
                    thumbnailTab.setClosable(false);
                    thumbnailTab.setContent(mainModel.getThumbnailView());
                    tabPane.getTabs().add(thumbnailTab);
                    break;
                case "addPerspectiveTab" :
                    Tab perspectiveTab = new Tab("Perspective " + mainModel.getPerspectiveNumber());
                    mainModel.setPerspectiveNumber(mainModel.getPerspectiveNumber()+1);
                    perspectiveTab.setClosable(true);
                    perspectiveTab.setContent(mainModel.getLastPerspectiveView());
                    tabPane.getTabs().add(perspectiveTab);
                    tabPane.getSelectionModel().select(perspectiveTab);
                    break;
                case "clearTab" :
                    tabPane.getTabs().clear();
                    break;
                case "disabledMenuItem" :
                    closeMenuItem.setDisable(true);
                    saveMenuItem.setDisable(true);
                    newPerspectiveMenuItem.setDisable(true);
                    break;
                case "enabledMenuItem" :
                    closeMenuItem.setDisable(false);
                    saveMenuItem.setDisable(false);
                    newPerspectiveMenuItem.setDisable(false);
                    break;
            }
        }
    }
}

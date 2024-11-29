package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class PerspectiveView extends BorderPane {
    private MenuBar menuBar;
    private TabPane tabPane;
    private Label coordinatesLabel;
    private Slider zoomSlider;
    private Button copyButton, pasteButton, undoButton, redoButton;

    public PerspectiveView() {
        // Barre de menu
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

        // Onglets pour les perspectives
        tabPane = new TabPane();
        setCenter(tabPane);

        // Barre en bas
        HBox bottomBar = new HBox();
        bottomBar.setSpacing(10);

        coordinatesLabel = new Label("x = 0 ; y = 0");

        copyButton = new Button("Copier");
        pasteButton = new Button("Coller");
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");

        zoomSlider = new Slider(0, 100, 50);
        Label zoomValueLabel = new Label("50%");

        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                zoomValueLabel.setText(String.format("%.0f%%", newVal.doubleValue()))
        );

        bottomBar.getChildren().addAll(coordinatesLabel, copyButton, pasteButton, undoButton, redoButton, zoomSlider, zoomValueLabel);
        setBottom(bottomBar);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Slider getZoomSlider() {
        return zoomSlider;
    }
}

package com.example.labo5_log121.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class PerspectiveView extends BorderPane {
    private final MenuBar menuBar;
    private final TabPane tabPane;
    private final Slider zoomSlider;
    /*private final Label coordinatesLabel;

    private final Button copyButton, pasteButton, undoButton, redoButton;*/
    private final HBox bottomBar;

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
        //HBox bottomBar = new HBox();
        //bottomBar.setSpacing(10);

        zoomSlider = new Slider(50, 200, 100);
        bottomBar = new HBox(10);
        bottomBar.getChildren().addAll(
                new Label("x = 0 ; y = 0"),
                new Button("Copier"),
                new Button("Coller"),
                new Button("Undo"),
                new Button("Redo"),
                new Label("Zoom:"),
                zoomSlider
        );

        bottomBar.setVisible(false); // Barre inférieure masquée par défaut
        setBottom(bottomBar);


        /*coordinatesLabel = new Label("x = 0 ; y = 0");

        copyButton = new Button("Copier");
        pasteButton = new Button("Coller");
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");

        zoomSlider = new Slider(50, 200, 100); // Min: 50%, Max: 200%, Default: 100%
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setShowTickMarks(true);
        Label zoomValueLabel = new Label("50%");

        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                zoomValueLabel.setText(String.format("%.0f%%", newVal.doubleValue()))
        );
        HBox bottomBar = new HBox(10);
        bottomBar.getChildren().addAll(coordinatesLabel, copyButton, pasteButton, undoButton, redoButton, new Label("Zoom:"), zoomSlider, zoomValueLabel);
        setBottom(bottomBar)*/;
    }

    public void showBottomBar(boolean show) {
        bottomBar.setVisible(show);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Slider getZoomSlider() {
        return zoomSlider;
    }
    /*public Button getCopyButton() {
        return copyButton;
    }
    public Button getPasteButton() {
        return pasteButton;
    }
    public Button getUndoButton() {
        return undoButton;
    }
    public Button getRedoButton() {
        return redoButton;
    }*/
    public TabPane getTabPane() {
        return tabPane;
    }
}

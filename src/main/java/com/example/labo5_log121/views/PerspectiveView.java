package com.example.labo5_log121.views;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class PerspectiveView extends BorderPane implements Observer {
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

    public void enableDrag(ImageView imageView, PerspectiveModel model) {
        final double[] initialX = {0};
        final double[] initialY = {0};

        imageView.setOnMousePressed(event -> {
            initialX[0] = event.getSceneX() - model.getTranslationX();
            initialY[0] = event.getSceneY() - model.getTranslationY();
        });

        imageView.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - initialX[0];
            double deltaY = event.getSceneY() - initialY[0];

            model.setTranslation(deltaX, deltaY);
        });
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof PerspectiveModel) {
            PerspectiveModel model = (PerspectiveModel) subject;
            System.out.println("Vue mise à jour : Translation X=" + model.getTranslationX() + ", Y=" + model.getTranslationY());

            Tab selectedTab = getTabPane().getSelectionModel().getSelectedItem();
            if (selectedTab != null && selectedTab.getContent() instanceof Pane) {
                Pane pane = (Pane) selectedTab.getContent();
                if (!pane.getChildren().isEmpty() && pane.getChildren().get(0) instanceof ImageView) {
                    ImageView imageView = (ImageView) pane.getChildren().get(0);

                    // Applique la translation
                    imageView.setTranslateX(model.getTranslationX());
                    imageView.setTranslateY(model.getTranslationY());
                } else {
                    System.out.println("Aucun ImageView trouvé dans l'onglet sélectionné.");
                }
            } else {
                System.out.println("Aucun onglet valide sélectionné.");
            }
        } else {
            System.out.println("Mise à jour ignorée : Sujet non reconnu.");
        }
    }


}

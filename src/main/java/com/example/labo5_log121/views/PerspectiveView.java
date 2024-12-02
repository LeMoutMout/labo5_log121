package com.example.labo5_log121.views;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class PerspectiveView extends Pane implements Observer {
    private final ImageView imageView;
    private final Slider zoomSlider;
    private final Button undoButton;
    private final Button redoButton;
    private final HBox bottomBar;
    private final Label coordX;
    private final Label coordY;
    private transient Subject lastSubject;

    public PerspectiveView(String imagePath) {
        // Crée l'ImageView pour l'image
        imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(700);
        imageView.setFitHeight(500);

        // Mettre l'ImageView dans un ScrollPane pour permettre le défilement si nécessaire
        ScrollPane scrollPane = new ScrollPane(imageView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Création de la bottomBar
        bottomBar = new HBox(10);

        coordX = new Label();
        coordX.setText("0");

        coordY = new Label();
        coordY.setText("0");

        undoButton = new Button("Undo");
        undoButton.setDisable(true);

        redoButton = new Button("Redo");
        redoButton.setDisable(true);

        zoomSlider = new Slider(10, 500, 100);
        zoomSlider.setBlockIncrement(10);

        // Ajouter les autres éléments à la bottomBar
        bottomBar.getChildren().addAll(
                new Label("x : "),
                coordX,
                new Label("y : "),
                coordY,
                new Button("Copier"),
                new Button("Coller"),
                undoButton,
                redoButton,
                new Label("Zoom:"),
                zoomSlider
        );

        // Ajouter l'image (avec le ScrollPane) et la bottomBar dans une VBox
        VBox layout = new VBox();
        layout.getChildren().addAll(scrollPane, bottomBar);

        // Assurer que la bottomBar reste visible
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Ajouter la VBox dans le Pane de PerspectiveView
        this.getChildren().add(layout);
    }

    public HBox getBottomBar(){return bottomBar;}
    public ImageView getImageView(){return imageView;}
    public Subject getLastSubject() {
        return lastSubject; // Récupéré via update() dans Observer
    }

    @Override
    public void update(Subject subject) {
        PerspectiveModel perspectiveModel = (PerspectiveModel) subject;
        imageView.setScaleY(perspectiveModel.getScaleFactor());
        imageView.setScaleX(perspectiveModel.getScaleFactor());
        imageView.setTranslateX(perspectiveModel.getTranslationX());
        imageView.setTranslateY(perspectiveModel.getTranslationY());
        coordX.setText(String.valueOf(perspectiveModel.getTranslationX()));
        coordY.setText(String.valueOf(perspectiveModel.getTranslationY()));
        zoomSlider.setValue(perspectiveModel.getScaleFactor()*100);
        undoButton.setDisable(perspectiveModel.isUndoDisabled());
        redoButton.setDisable(perspectiveModel.isRedoDisabled());
        this.lastSubject = perspectiveModel;
    }
}

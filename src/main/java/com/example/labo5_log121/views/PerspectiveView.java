package com.example.labo5_log121.views;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
<<<<<<< HEAD
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
=======
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
>>>>>>> momo
import javafx.scene.control.ScrollPane;

public class PerspectiveView extends Pane implements Observer {
    private final ImageView imageView;
    private final Slider zoomSlider;
    private final HBox bottomBar;
    private transient Subject lastSubject;

    public PerspectiveView(String imagePath) {
        // Crée l'ImageView pour l'image
        imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(700);  // Ajustez selon vos besoins
        imageView.setFitHeight(500); // Ajustez selon vos besoins

        // Mettre l'ImageView dans un ScrollPane pour permettre le défilement si nécessaire
        ScrollPane scrollPane = new ScrollPane(imageView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Création de la bottomBar
        bottomBar = new HBox(10);
        zoomSlider = new Slider(10, 500, 100);
        zoomSlider.setBlockIncrement(10);

        // Ajouter les autres éléments à la bottomBar
        bottomBar.getChildren().addAll(
                new Label("x = 0 ; y = 0"),
                new Button("Copier"),
                new Button("Coller"),
                new Button("Undo"),
                new Button("Redo"),
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

    @Override
    public void update(Subject subject) {
        PerspectiveModel perspectiveModel = (PerspectiveModel) subject;
        imageView.setScaleY(perspectiveModel.getScaleFactor());
        imageView.setScaleX(perspectiveModel.getScaleFactor());
        imageView.setTranslateX(perspectiveModel.getTranslationX());
        imageView.setTranslateY(perspectiveModel.getTranslationY());
        zoomSlider.setValue(perspectiveModel.getScaleFactor()*100);
    }

    /*@Override
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
    }*/
=======

    public Subject getLastSubject() {
        return lastSubject; // Récupéré via update() dans Observer
    }

    @Override
    public void update(Subject subject) {
        PerspectiveModel perspectiveModel = (PerspectiveModel) subject;
        imageView.setScaleY(perspectiveModel.getScaleFactor());
        imageView.setScaleX(perspectiveModel.getScaleFactor());
        imageView.setLayoutX(perspectiveModel.getTranslationX());
        imageView.setLayoutY(perspectiveModel.getTranslationY());
        zoomSlider.setValue(perspectiveModel.getScaleFactor()*100);
        this.lastSubject = perspectiveModel;

    }
>>>>>>> momo
}

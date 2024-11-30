package com.example.labo5_log121.views;

import com.example.labo5_log121.models.PerspectiveModel;
import com.example.labo5_log121.models.Subject;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class PerspectiveView extends Pane implements Observer {
    private ImageView imageView;

    public PerspectiveView(String imagePath) {
        imageView = new ImageView("file:" + imagePath);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800); // Ajustez selon vos besoins
        imageView.setFitHeight(600); // Ajustez selon vos besoins
    }

    @Override
    public void update(Subject subject) {
        PerspectiveModel perspectiveModel = (PerspectiveModel) subject;
        imageView.setScaleY(perspectiveModel.getScaleFactor());
        imageView.setScaleX(perspectiveModel.getScaleFactor());
        imageView.setLayoutX(perspectiveModel.getTranslationX());
        imageView.setLayoutY(perspectiveModel.getTranslationY());
    }
}

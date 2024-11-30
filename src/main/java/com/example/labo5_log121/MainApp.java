package com.example.labo5_log121;

import com.example.labo5_log121.controllers.PerspectiveController;
import com.example.labo5_log121.controllers.ThumbnailController;
import com.example.labo5_log121.views.PerspectiveView;
import com.example.labo5_log121.views.ThumbnailView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ThumbnailView view = new ThumbnailView();
        new ThumbnailController(view);

        Scene scene = new Scene(view, 800, 600);
        stage.setTitle("PhotoEditor - MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


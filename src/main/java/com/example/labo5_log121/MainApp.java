package com.example.labo5_log121;

import com.example.labo5_log121.controllers.PerspectiveController;
import com.example.labo5_log121.views.PerspectiveView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        PerspectiveView view = new PerspectiveView();
        new PerspectiveController(view);

        Scene scene = new Scene(view, 800, 600);
        primaryStage.setTitle("PhotoEditor - MVC");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


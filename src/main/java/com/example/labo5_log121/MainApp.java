package com.example.labo5_log121;

import com.example.labo5_log121.controllers.MainController;
import com.example.labo5_log121.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();
        new MainController(mainView);

        Scene scene = new Scene(mainView, 800, 600);
        stage.setTitle("PhotoEditor - MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


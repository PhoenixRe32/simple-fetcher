package com.pittacode.apihelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApiHelperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final var apiHelperViewLoader = new FXMLLoader(ApiHelperApplication.class.getResource("api-helper-view.fxml"));

        final var apiHelperView = apiHelperViewLoader.load();

        Scene scene = new Scene((Parent) apiHelperView);

        stage.setTitle("Api Helper");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}
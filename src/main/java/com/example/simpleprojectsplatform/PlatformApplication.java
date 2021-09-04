package com.example.simpleprojectsplatform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlatformApplication extends Application implements PathsAndTitles {

    @Override
    public void start(Stage stage) throws IOException {
        stage.resizableProperty().set(false);
        FXMLLoader fxmlLoader = new FXMLLoader(PlatformApplication.class.getResource(platformPath));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(mainTitle);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
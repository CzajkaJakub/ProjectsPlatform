package com.example.simpleprojectsplatform;

import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PlatformApplication extends Application implements PathsTitlesFiles {

    @Override
    public void start(Stage stage) throws IOException {
        stage.resizableProperty().set(false);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.getIcons().addAll(platformIcon, musicPlayerIcon);
        PlatformController platformController = new PlatformController();
        PlatformController.setStage(stage);
        platformController.mainScene();
    }

    public static void main(String[] args) {
        launch();
    }
}
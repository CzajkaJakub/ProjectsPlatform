package com.example.simpleprojectsplatform;

import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class PlatformApplication extends Application implements PathsTitlesFiles {

    @Override
    public void start(Stage stage) throws IOException {
        stage.resizableProperty().set(false);
        stage.setOnCloseRequest(windowEvent -> exitTheProgram(windowEvent, stage));
        stage.getIcons().addAll(platformIcon, musicPlayerIcon);
        PlatformController platformController = new PlatformController();
        PlatformController.setStage(stage);
        platformController.mainScene();
    }

    private void exitTheProgram(WindowEvent windowEvent, Stage stage) {
        windowEvent.consume();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit the program");
        alert.setHeaderText("Do you really want to exit?");
        alert.setContentText("Press OK to exit.");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
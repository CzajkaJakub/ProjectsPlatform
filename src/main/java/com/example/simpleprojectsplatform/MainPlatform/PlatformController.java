package com.example.simpleprojectsplatform.MainPlatform;

import com.example.simpleprojectsplatform.PathsTitlesFiles;
import com.example.simpleprojectsplatform.PlatformApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PlatformController implements PathsTitlesFiles {

    private static Stage stage;

    public static void setStage(Stage stage) {
        PlatformController.stage = stage;
    }

    public static void changeScene(String path, String frameTitle, Image icon) throws IOException {
        FXMLLoader loader = new FXMLLoader(PlatformApplication.class.getResource(path));
        Scene newScene = new Scene(loader.load());
        stage.getIcons().set(0, icon);
        stage.setTitle(frameTitle);
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void newMusicPlayer() throws IOException {
        changeScene(musicPlayerPath, musicPlayerTitle, musicPlayerIcon);
    }

    @FXML
    private void showMusicFiles() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(musicFilesPath));
    }


}
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
import java.util.Objects;


public class PlatformController implements PathsTitlesFiles {

    private static Stage stage;

    private static Image icon;

    @FXML
    private void showMusicFiles() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(musicFilesPath));
    }

    @FXML
    private void newMusicPlayer() throws IOException {
        changeScene(musicPlayerPath, musicPlayerTitle, musicPlayerIconPath);
    }

    public static void changeScene(String path, String frameTitle, String iconPth) throws IOException {
        FXMLLoader loader = new FXMLLoader(PlatformApplication.class.getResource(path));
        Scene newScene = new Scene(loader.load());
        setIcon(iconPth);
        stage.getIcons().add(icon);
        stage.getIcons().set(0, icon);
        stage.setTitle(frameTitle);
        stage.setScene(newScene);
        stage.show();
    }

    public static void setIcon(String icon) {
        PlatformController.icon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(icon)));
    }

    public static void setStage(Stage stage) {
        PlatformController.stage = stage;
    }

}
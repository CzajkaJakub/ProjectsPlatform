package com.example.simpleprojectsplatform.MainPlatform;

import com.example.simpleprojectsplatform.PathsAndTitles;
import com.example.simpleprojectsplatform.PlatformApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PlatformController implements PathsAndTitles {

    @FXML
    AnchorPane background;

    @FXML
    private void newMusicPlayer() throws IOException {
        changeScene(musicPlayerPath, background.getScene(), musicPlayerTitle);

    }

    public static void changeScene(String path, Scene scene, String frameTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(PlatformApplication.class.getResource(path));
        Stage stage = (Stage)(scene.getWindow());
        stage.setTitle(frameTitle);
        Scene newScene = new Scene(loader.load());
        stage.setScene(newScene);
        stage.show();
    }
}
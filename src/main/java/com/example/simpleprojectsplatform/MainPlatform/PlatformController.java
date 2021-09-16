package com.example.simpleprojectsplatform.MainPlatform;

import com.example.simpleprojectsplatform.PathsTitlesFiles;
import com.example.simpleprojectsplatform.PlatformApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PlatformController implements PathsTitlesFiles, Initializable {

    private static Stage stage;
    private static DiamondAnimation task;

    @FXML
    ImageView diamondIcon;

    @FXML
    ImageView backgroundLight;

    @FXML
    AnchorPane background;


    public static void setStage(Stage stage) {
        PlatformController.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task = new DiamondAnimation(diamondIcon, backgroundLight, background.getPrefWidth(), background.getPrefHeight());
        Thread animation = new Thread(task);
        animation.start();
    }

    private void changeScene(String path, String frameTitle, Image icon) throws IOException {
        FXMLLoader loader = new FXMLLoader(PlatformApplication.class.getResource(path));
        Scene newScene = new Scene(loader.load());
        stage.getIcons().set(0, icon);
        stage.setTitle(frameTitle);
        stage.setScene(newScene);
        stage.show();

        if(path.equals(platformPath)){
            addKeyEventsForAnimations(newScene);
        }
    }

    private void addKeyEventsForAnimations(Scene mainScene) {
        mainScene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> task.moveUP();
                case LEFT -> task.moveLEFT();
                case DOWN -> task.moveDOWN();
                case RIGHT -> task.moveRIGHT();
                case Q -> task.rotateDiamondZ();
                case W -> task.rotateDiamondX();
                case E -> task.rotateDiamondY();
                case R -> task.hideDiamond();
                case T -> task.showDiamond();
                case Y -> task.makeTheDiamondBig();
                case U -> task.makeTheDiamondSmall();
            }
        });
    }

    public void mainScene() throws IOException {
        changeScene(platformPath, mainTitle, platformIcon);
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
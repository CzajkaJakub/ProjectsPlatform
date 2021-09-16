package com.example.simpleprojectsplatform;

import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.scene.image.Image;

import java.util.Objects;

public interface PathsTitlesFiles {
    String musicPlayerPath = "FXMLFiles/MusicPlayer.fxml";
    String musicPlayerTitle = "Music Player";
    String musicFilesPath = "src/main/resources/com/example/simpleprojectsplatform/MusicFiles";
    String musicPlayerIconPath = "/com/example/simpleprojectsplatform/Images/MusicPlayer/MusicPlayerIco.png";
    Image musicPlayerIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(musicPlayerIconPath)));

    String platformPath = "FXMLFiles/PlatformScene.fxml";
    String mainTitle = "Simple projects platform";
    String platformIconPath = "/com/example/simpleprojectsplatform/Images/Platform/platformIcon.png";
    Image platformIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(platformIconPath)));
}

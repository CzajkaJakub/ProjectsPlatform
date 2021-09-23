package com.example.simpleprojectsplatform;

import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.scene.image.Image;

import java.util.Objects;

public interface PathsTitlesFiles {

    // music player
    String musicPlayerPath = "FXMLFiles/MusicPlayer.fxml";
    String musicPlayerTitle = "Music Player";
    String musicFilesPath = "src/main/resources/com/example/simpleprojectsplatform/MusicFiles";
    String musicPlayerIconPath = "/com/example/simpleprojectsplatform/Images/MusicPlayer/MusicPlayerIco.png";

    Image musicPlayerIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(musicPlayerIconPath)));


    //platform
    String platformFxmlPath = "FXMLFiles/PlatformScene.fxml";
    String mainTitle = "Simple projects platform";
    String platformIconPath = "/com/example/simpleprojectsplatform/Images/Platform/platformIcon.png";

    Image platformIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(platformIconPath)));


    //country wiki
    String theCountriesWikiFxmlPath = "FXMLFiles/TheCountriesWiki.fxml";
    String theCountriesWikiTitle = "Country Wiki";
    String theCountriesWikiIconPath = "/com/example/simpleprojectsplatform/Images/CountryWiki/wikiCountryIcon.png";
    String connectionYesPath = "/com/example/simpleprojectsplatform/Images/CountryWiki/connectionYes.png";
    String connectionNoPath = "/com/example/simpleprojectsplatform/Images/CountryWiki/connectionNo.png";

    Image theCountriesWikiIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(theCountriesWikiIconPath)));
    Image connectionYesIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(connectionYesPath)));
    Image connectionNoIcon = new Image(Objects.requireNonNull(PlatformController.class.getResourceAsStream(connectionNoPath)));

}

module com.example.simpleprojectsplatform {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;


    opens com.example.simpleprojectsplatform to javafx.fxml;
    exports com.example.simpleprojectsplatform;
    exports com.example.simpleprojectsplatform.MusicPlayer;
    opens com.example.simpleprojectsplatform.MusicPlayer to javafx.fxml;
    exports com.example.simpleprojectsplatform.MainPlatform;
    opens com.example.simpleprojectsplatform.MainPlatform to javafx.fxml;

}
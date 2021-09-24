package com.example.simpleprojectsplatform.TheCountriesWiki;

import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class TheCountriesWiki implements Initializable {

    @FXML
    TextField loginField, databaseNameField, searchValue;

    @FXML
    PasswordField passwordField;

    @FXML
    ImageView connectionMark;

    @FXML
    ComboBox<String> searchType;

    @FXML
    Button makeConnectionButton, searchButton;

    @FXML
    Label isoLabel, nameLabel, iso3Label, numCodeLabel, phoneCodeLabel;

    @FXML
    WebView browser;


    private WebEngine engine;
    private SqlConnection sqlConnection;
    private final String[] searchBy = {"Country name", "Num code", "Phone code"};
    private final String initialComboboxValue = "Search by";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sqlConnection = new SqlConnection();
        searchButton.disableProperty().set(true);
        engine = browser.getEngine();
        fillComboBox();
    }

    private void fillComboBox() {
        searchType.setValue(initialComboboxValue);
        searchType.getItems().addAll(searchBy);
    }

    @FXML
    private void makeConnection(){
        String databaseLogin = loginField.getText();
        String databasePassword = passwordField.getText();
        String databaseName = databaseNameField.getText();
        sqlConnection.insertDataConnection(databaseLogin, databasePassword, databaseName);
        sqlConnection.makeConnection(connectionMark, makeConnectionButton, searchButton);
    }

    @FXML
    private void searchData(){
        String searchByType = searchType.getValue();
        String searchValInput = searchValue.getText().trim().toUpperCase(Locale.ROOT);

        if(sqlConnection.connectionStatus() == ConnectionStatus.CONNECTED &&
                !searchByType.equals(initialComboboxValue) &&
                !searchValInput.isBlank()){

            ArrayList<String> result = sqlConnection.getData(searchByType, searchValInput);
            fillLabels(result);
        }
    }

    private void fillLabels(ArrayList<String> result) {
        if(result.isEmpty()){
            nameLabel.setText("No data");
            isoLabel.setText("");
            iso3Label.setText("");
            numCodeLabel.setText("");
            phoneCodeLabel.setText("");
        }else{
            nameLabel.setText("Country name : " + result.get(1));
            isoLabel.setText("Iso : " + result.get(0));
            iso3Label.setText("Iso 3 : " + result.get(2));
            numCodeLabel.setText("Num code : " + result.get(3));
            phoneCodeLabel.setText("Phone code : " + result.get(4));
            createWeb(result.get(1).replace(" ", "_"));
        }
    }

    private void createWeb(String searchValue) {
        browser.setStyle("-fx-opacity: 1");
        engine.load("https://en.wikipedia.org/wiki/" + searchValue);


    }

    @FXML
    private void backToMain() throws IOException {
        if(sqlConnection.connectionStatus() == ConnectionStatus.CONNECTED){
            sqlConnection.closeConnection();
        }
        new PlatformController().mainScene();
    }
}

package com.example.simpleprojectsplatform.TheCountriesWiki;


import com.example.simpleprojectsplatform.PathsTitlesFiles;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;

public class SqlConnection implements PathsTitlesFiles {

    private String user;
    private String password;
    private String url = "jdbc:mysql://localhost:3306/";
    private ConnectionStatus connected = ConnectionStatus.NOT_CONNECTED;
    private Connection connection;

    public ConnectionStatus connectionStatus() {
        return connected;
    }

    public void insertDataConnection(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.url = url + databaseName;
    }

    public void makeConnection(ImageView connectionMark, Button makeConnectionButton, Button searchButton){
        connection = null;
        try {
            connectionMark.setImage(connectionYesIcon);
            connection = DriverManager.getConnection(url, user, password);
            connected = ConnectionStatus.CONNECTED;
            makeConnectionButton.disableProperty().set(true);
            searchButton.disableProperty().set(false);
        } catch (SQLException e) {
            connectionMark.setImage(connectionNoIcon);
            connected = ConnectionStatus.NOT_CONNECTED;
        }
    }

    public ArrayList<String> getData(String searchByType, String searchValInput) {

        ArrayList<String> data = new ArrayList<>();
        String query = String.format(
                "SELECT `iso`, `Nice name`, `iso3`, `Num code`, `Phone code` FROM country WHERE `%s` = '%s'",
                searchByType, searchValInput);

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                for (int column = 1; column <= 5; column++) {
                    data.add(result.getString(column));
                }
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

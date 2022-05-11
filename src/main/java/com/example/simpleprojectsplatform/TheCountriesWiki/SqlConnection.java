package com.example.simpleprojectsplatform.TheCountriesWiki;


import com.example.simpleprojectsplatform.PathsTitlesFiles;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;

public class SqlConnection implements PathsTitlesFiles {

    private ConnectionStatus connected = ConnectionStatus.NOT_CONNECTED;
    private Connection connection;

    public ConnectionStatus connectionStatus() {
        return connected;
    }

    public void makeConnection(ImageView connectionMark, Button makeConnectionButton, Button searchButton){
        connection = null;
        try {
            String user = "jqouqvkcbhhjhf";
            String password = "f55dd258144a7eefa3c8cfa22c40aa8dfd466ccfdee2839bdb2dd45e826248c3";
            String url = "jdbc:postgresql://ec2-3-230-122-20.compute-1.amazonaws.com:5432/d2ttasfjtidlfl";
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
                "select \"Iso\", \"Nice name\", iso3, \"Num code\", \"Phone code\" from country where \"%s\" = '%s'",
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

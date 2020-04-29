package com.example.demo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseConnection {
    private String prefixString = "database.connection.";
    public DatabaseConnection(){}

    public ArrayList<String> getConnectionInfo () {

        ArrayList<String> connectionInfo = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/connection.properties");
            Properties properties = new Properties();

            properties.load(fis);
            connectionInfo.add(properties.get(prefixString + "url").toString());
            connectionInfo.add(properties.getProperty(prefixString + "username"));
            connectionInfo.add(properties.getProperty(prefixString +  "password"));
        }
        catch (Exception e) {
            System.out.println("Doslo je do greske prilikom citanja connection.properties fajla!");
        }
        return connectionInfo;
    }
}

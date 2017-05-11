package com.codecool.shop.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class JDBC {


    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + read(1) + "/" + read(2) + "",
                read(3),
                read(4));
    }

    protected void executeQuery(String query) {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected String read(int paramNumber) {
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream("src/main/resources/connection.properties");

            prop.load(input);
            if (paramNumber == 1) {
                return prop.getProperty("url");
            } else if (paramNumber == 2) {
                return prop.getProperty("database");
            } else if (paramNumber == 3) {
                return prop.getProperty("user");
            } else if (paramNumber == 4) {
                return prop.getProperty("password");
            } else {
                return null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

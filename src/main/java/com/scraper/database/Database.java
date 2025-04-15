package com.scraper.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String url = dotenv.get("URL");
    private static final String user = dotenv.get("USERNAME");
    private static final String password = dotenv.get("PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}

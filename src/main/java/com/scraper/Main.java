package com.scraper;

import com.scraper.database.Database;
import com.scraper.database.PostgreSQLHelper;
import com.scraper.domain.CarService;
import com.scraper.logic.ThreeNinesExtractor;


import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        String browser = "firefox";
        try(ThreeNinesExtractor threeNinesExtractor = new ThreeNinesExtractor(browser)) {
            try(Connection connection = Database.getConnection()){
                CarService service = new CarService(threeNinesExtractor, new PostgreSQLHelper(connection));
                    service.execute("Volvo", "960", "I");
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
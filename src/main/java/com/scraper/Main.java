package com.scraper;

import com.scraper.database.Database;
import com.scraper.database.PostgreSQLHelper;
import com.scraper.domain.CarService;
import com.scraper.factory.ChromeWebDriver;
import com.scraper.factory.FirefoxWebDriver;
import com.scraper.logic.ThreeNinesExtractor;


import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try(ThreeNinesExtractor threeNinesExtractor = new ThreeNinesExtractor(new ChromeWebDriver())) {
            try(Connection connection = Database.getConnection()){
                CarService service = new CarService(threeNinesExtractor, new PostgreSQLHelper(connection));
                    service.execute("Renault", "Megane", "II");
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
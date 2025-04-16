package com.scraper.database;

import com.scraper.data.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLHelper implements DatabaseHelper {

    private final Connection connection;

    public PostgreSQLHelper(Connection connection) {
        this.connection = connection;

    }

    public void insertCarData(Car car) {
        String procedureCall = "CALL car_sales.insert_car(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareCall(procedureCall)) {
            stmt.setString(1, car.getName());
            stmt.setInt(2, car.getYear());
            stmt.setInt(3, car.getPrice());
            stmt.setInt(4, car.getKilometrage());
            stmt.setString(5, car.getCurrency());
            stmt.setString(6, car.getLink());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error inserting car data: " + car + ",Error: " + e.getMessage());
        }
    }
}

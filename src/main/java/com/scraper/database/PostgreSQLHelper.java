package com.scraper.database;

import com.scraper.data.Car;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLHelper implements DatabaseHelper {

    private final Connection connection;

    public PostgreSQLHelper(Connection connection) {
        this.connection = connection;
    }

    public void insertCarData(Car car) {
        String statement1 = "INSERT INTO car_sales.car (car_name, car_year) VALUES (?, ?) ON CONFLICT (car_name, car_year) DO NOTHING";
        try (PreparedStatement stmt = connection.prepareStatement(statement1)) {
            stmt.setString(1, car.getName());
            stmt.setInt(2, car.getYear());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error inserting into the car table: " + car);
            return;
        }

        String statement2 = "INSERT INTO car_sales.sale_ad (car_id, car_price, car_kilometrage, currency) "+
                            "SELECT car_id, ?, ?, ? FROM car_sales.car WHERE car_name = ? AND car_year = ?";
        try (PreparedStatement stmt = connection.prepareStatement(statement2)) {
            stmt.setInt(1, car.getPrice());
            stmt.setInt(2, car.getKilometrage());
            stmt.setString(3, car.getCurrency());
            stmt.setString(4, car.getName());
            stmt.setInt(5, car.getYear());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error inserting into the sale ad table: " + car);
        }
    }

}

package com.scraper.database;

import com.scrapperscript.models.ErrorRecord;
import com.scrapperscript.models.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLHelper implements DatabaseHelper {

    private final Connection connection;

    public PostgreSQLHelper(Connection connection) {
        this.connection = connection;
    }

    public void executeInsertProductData(Product product) throws SQLException {

        String procedureCall = "CALL product_pricing.insert_product_data(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (CallableStatement stmt = connection.prepareCall(procedureCall)) {

            stmt.setString(1, product.code);
            stmt.setString(2, product.url);
            stmt.setString(3, product.name);
            stmt.setObject(4, product.price, java.sql.Types.FLOAT);
            stmt.setObject(5, product.originalPrice, java.sql.Types.FLOAT);
            stmt.setString(6, product.imageUrl);
            stmt.setBoolean(7, product.inStock);
            stmt.setBoolean(8, product.withPreorder);
            stmt.setInt(9, product.sellerId);
            stmt.setObject(10, product.occuredAt);

            stmt.execute();

        } catch (SQLException e) {

            System.err.println("Error executing InsertProductData: " + e.getMessage());
            throw e;
        }
    }

    public void executeInsertScrapingError(ErrorRecord error) throws SQLException {

        String procedureCall = "CALL product_pricing.insert_scraping_error(?, ?, ?)";

        try (CallableStatement stmt = connection.prepareCall(procedureCall)) {

            stmt.setString(1, error.productUrl);
            stmt.setString(2, error.errorDescription);
            stmt.setObject(3, error.occuredAt);

            stmt.execute();

        } catch (SQLException e) {

            System.err.println("Error executing InsertProductData: " + e.getMessage());
            throw e;
        }
    }

}

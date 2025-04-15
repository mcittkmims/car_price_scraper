package com.scraper.database;

import com.scrapperscript.models.ErrorRecord;
import com.scrapperscript.models.Product;

import java.sql.SQLException;

public interface DatabaseHelper {

    public void executeInsertProductData(Product product) throws SQLException;

    public void executeInsertScrapingError(ErrorRecord error) throws SQLException;
}

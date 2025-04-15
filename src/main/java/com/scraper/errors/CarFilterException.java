package com.scraper.errors;

public class CarFilterException extends RuntimeException {
    public CarFilterException() {
        super("Failed to apply the car filters. Please check so filters are actually present on the website");
    }
}

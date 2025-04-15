package com.scraper;

import com.scraper.data.Car;
import com.scraper.data.CarHelper;
import com.scraper.logic.ThreeNinesExtractor;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try(ThreeNinesExtractor threeNinesExtractor = new ThreeNinesExtractor("firefox")) {
            List<Car> cars = threeNinesExtractor.extractCars();
            List<Car> filteredCars = CarHelper.filterByName("Renault Megane", CarHelper.filterValidOnly(cars));

            System.out.println("The cheapest car");
            System.out.println(CarHelper.getMinPrice(filteredCars));
            System.out.println("\nThe most expensive car");
            System.out.println(CarHelper.getMaxPrice(filteredCars));
            System.out.println("\nThe average price: " + CarHelper.getAveragePrice(filteredCars));

            System.out.println(filteredCars);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
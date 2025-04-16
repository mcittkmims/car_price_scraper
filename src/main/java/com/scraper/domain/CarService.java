package com.scraper.domain;

import com.scraper.data.Car;
import com.scraper.data.CarHelper;
import com.scraper.database.DatabaseHelper;
import com.scraper.logic.ThreeNinesExtractor;

import java.util.List;

public class CarService {

    private ThreeNinesExtractor extractor;
    private DatabaseHelper databaseHelper;

    public CarService(ThreeNinesExtractor extractor, DatabaseHelper databaseHelper) {
        this.extractor = extractor;
        this.databaseHelper = databaseHelper;
    }

    public void execute(String brand, String series, String generation) throws InterruptedException {
        List<Car> cars = extractor.extractCars(brand, series, generation);
        cars = CarHelper.filterByName(brand + " " + series, CarHelper.filterValidOnly(cars));
        this.insertListToDatabase(cars);
        this.getStatistics(cars);
    }

    public void execute(String brand, String series) throws InterruptedException {
        List<Car> cars = extractor.extractCars(brand, series);
        cars = CarHelper.filterByName(brand + " " + series, CarHelper.filterValidOnly(cars));
        this.insertListToDatabase(cars);
        this.getStatistics(cars);
    }

    public void execute(String brand) throws InterruptedException {
        List<Car> cars = extractor.extractCars(brand);
        cars = CarHelper.filterByName(brand, CarHelper.filterValidOnly(cars));
        this.insertListToDatabase(cars);
        this.getStatistics(cars);
    }

    public void execute() throws InterruptedException {
        List<Car> cars = extractor.extractCars();
        cars = CarHelper.filterValidOnly(cars);
        this.insertListToDatabase(cars);
        this.getStatistics(cars);
    }

    public void insertListToDatabase(List<Car> cars) {
        if (cars != null && !cars.isEmpty()) {
            cars.forEach(car -> databaseHelper.insertCarData(car));
        }
    }

    public void getStatistics(List<Car> cars){
        System.out.println("The cheapest car");
        System.out.println(CarHelper.getMinPrice(cars));
        System.out.println("\nThe most expensive car");
        System.out.println(CarHelper.getMaxPrice(cars));
        System.out.println("\nThe average price: " + CarHelper.getAveragePrice(cars));
    }
}

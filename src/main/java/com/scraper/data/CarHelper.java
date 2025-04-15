package com.scraper.data;

import java.util.List;
import java.util.stream.Collectors;

public class CarHelper {
    public static List<Car> filterByName(String name, List<Car> cars){
        return cars.stream()
                .filter(car -> car.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Car> filterValidOnly(List<Car> cars){
        return cars.stream().filter(car -> !hasNullFields(car)).collect(Collectors.toList());
    }

    public static Car getMinPrice(List<Car> cars){
        return cars.stream().min((car1, car2) -> car1.getPrice() - car2.getPrice()).get();
    }

    public static Car getMaxPrice(List<Car> cars){
        return cars.stream().max((car1, car2) -> car1.getPrice() - car2.getPrice()).get();
    }

    public static double getAveragePrice(List<Car> cars){
        return cars.stream().mapToInt(Car::getPrice).average().orElse(0.0);
    }

    private static boolean hasNullFields(Car car) {
        return car == null
                || car.getName() == null
                || car.getYear() == null
                || car.getPrice() == null
                || car.getKilometrage() == null
                || car.getCurrency() == null
                ;
    }


}

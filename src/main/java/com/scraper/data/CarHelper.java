package com.scraper.data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CarHelper {
    private static Pattern pattern = Pattern.compile("/ro/\\d+");

    public static List<Car> filterByName(String name, List<Car> cars){
        return cars.stream()
                .filter(car -> car.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Car> filterValidOnly(List<Car> cars){
        return cars.stream().filter(car -> !hasNullFields(car))
                            .filter(car -> car.getPrice() > 100)
                            .peek(car -> {car.setCurrency(convertPriceToStandard(car)); car.setLink(filterLink(car));})
                            .collect(Collectors.toList());
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
                || car.getLink() == null
                ;
    }

    private static String convertPriceToStandard(Car car){
        return switch (car.getCurrency()) {
            case "$" -> "USD";
            case "€" -> "EUR";
            case "leu" -> "MDL";
            default -> car.getCurrency();
        };
    }

    private static String filterLink(Car car){
        Matcher matcher = pattern.matcher(car.getLink());
        if (matcher.find()) {
             return matcher.group();  // this will be "/ro/100496720" or "/ro/100749150"
        }
        return null;
    }
}

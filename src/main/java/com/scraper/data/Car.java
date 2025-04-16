package com.scraper.data;

public class Car {
    private String name;
    private Integer price;
    private Integer kilometrage;
    private Integer year;
    private String currency;
    private String link;

    public Car(String name, Integer price, Integer kilometrage, Integer year, String currency, String link) {
        this.name = name;
        this.price = price;
        this.kilometrage = kilometrage;
        this.year = year;
        this.currency = currency;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", kilometrage=" + kilometrage +
                ", year=" + year +
                ", currency='" + currency + '\'' +
                ", link=" + link +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Integer kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

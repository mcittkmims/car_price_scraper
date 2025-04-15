package com.scraper.errors;

public class CarSectionInitException extends RuntimeException{
    public CarSectionInitException(){
        super("Failed to get to the Car section");
    }


}

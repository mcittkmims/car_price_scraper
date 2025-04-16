package com.scraper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriver implements CarWebDriver {
    @Override
    public WebDriver init() {
        return (new FirefoxDriver());
    }
}

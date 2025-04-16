package com.scraper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriverFactory implements WebDriverFactory{
    @Override
    public WebDriver createWebDriver() {
        return new FirefoxDriver();
    }
}

package com.scraper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriverFactory implements WebDriverFactory{

    @Override
    public WebDriver createWebDriver() {
        return new ChromeDriver();
    }
}

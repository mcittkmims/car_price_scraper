package com.scraper.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver implements CarWebDriver {

    @Override
    public WebDriver init() {
        return new ChromeDriver();
    }
}

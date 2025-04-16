package com.scraper.factory;

public class FactoryProvider {
    public static WebDriverFactory getFactory(String browser){
        return switch (browser) {
            case "firefox" -> new FirefoxWebDriverFactory();
            case "chrome" -> new ChromeWebDriverFactory();
            default -> throw new IllegalArgumentException("Unknown factory type: " + browser);
        };
    }
}

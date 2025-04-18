package com.scraper.logic;

import com.scraper.data.Car;
import com.scraper.errors.CarFilterException;
import com.scraper.errors.CarSectionInitException;
import com.scraper.factory.FactoryProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ThreeNinesExtractor implements AutoCloseable {

    private static String link = "https://999.md";
    private WebDriver driver;
    private WebDriverWait wait;

    public ThreeNinesExtractor(String browser) {
        this.driver = FactoryProvider.getFactory(browser).createWebDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<Car> extractCars(String brand, String series, String generation) throws InterruptedException {
        try {
            this.initiateCarPage();
        } catch (WebDriverException e) {
            throw new CarSectionInitException();
        }

        try {
            this.applyCarFilter(brand, series, generation);
        } catch (WebDriverException e) {
            throw new CarFilterException();
        }
        return this.extractCarsFromPages();
    }

    public List<Car> extractCars(String brand, String series) throws InterruptedException {
        try {
            this.initiateCarPage();
        } catch (WebDriverException e) {
            throw new CarSectionInitException();
        }

        try {
            this.applyCarFilter(brand, series);
        } catch (WebDriverException e) {
            throw new CarFilterException();
        }
        return this.extractCarsFromPages();
    }

    public List<Car> extractCars(String brand) throws InterruptedException {
        try {
            this.initiateCarPage();
        } catch (WebDriverException e) {
            throw new CarSectionInitException();
        }
        this.applyCarFilter(brand);

        return this.extractCarsFromPages();
    }

    public List<Car> extractCars() throws InterruptedException {
        this.initiateCarPage();

        return this.extractCarsFromPages();
    }


    public void applyCarFilter(String brand, String series, String generation) {

        WebElement searchBrandField = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("styles_search__input__1XW7W")));
        searchBrandField.sendKeys(brand);

        String xpath = String.format(
                "//div[./label[contains(text(), '%s')]]/following-sibling::div//div[./label[contains(text(), '%s')]]/following-sibling::div//div[./label[starts-with(text(), '%s ')]]/input[@type='checkbox']",
                brand, series, generation
        );
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(xpath)
                ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }


    public void applyCarFilter(String brand, String series) {
        WebElement searchBrandField = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("styles_search__input__1XW7W")));
        searchBrandField.sendKeys(brand);

        String xpath = String.format(
                "//div[./label[contains(text(), '%s')]]/following-sibling::div//div[./label[contains(text(), '%s')]]/input[@type='checkbox']",
                brand, series
        );
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(xpath)
                ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }


    public void applyCarFilter(String brand) {
        WebElement searchBrandField = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("styles_search__input__1XW7W")));
        searchBrandField.sendKeys(brand);

        String xpath = String.format(
                "//div[./label[contains(text(), '%s')]]/input[@type='checkbox']",
                brand
        );
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(xpath)
                ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    public void initiateCarPage() {
        driver.get(link);
        WebElement transportSectionLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Transport"))
        );
        transportSectionLink.click();

        WebElement autoturismeSectionLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Autoturisme"))
        );
        autoturismeSectionLink.click();

    }

    public List<Car> extractCarsFromPages() throws InterruptedException {
        boolean hasNextPage = true;
        List<Car> cars = new ArrayList<>();
        while (hasNextPage) {
            Thread.sleep(1500);
            cars.addAll(ThreeNinesHTMLParser.extractCarPrices(driver.getPageSource()));
            try {
                WebElement nextButton = driver.findElement(By.className("Pagination_pagination__container__buttons__wrapper__icon__next__A22Rc"));
                if (nextButton.isEnabled()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
                } else {
                    hasNextPage = false;
                }
            } catch (NoSuchElementException ignored) {
                hasNextPage = false;
            }

        }
        return cars;
    }

    @Override
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}

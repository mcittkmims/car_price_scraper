package com.scraper.logic;

import com.scraper.data.Car;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreeNinesHTMLParser {

    private static Pattern carNamePattern = Pattern.compile("^(.+?), an\\. \\d{4}$");
    private static Pattern carYearPattern = Pattern.compile("^.+?, an\\. (\\d{4})$");
    private static Pattern carPricePattern = Pattern.compile("(\\d+(?:\\s+\\d+)*)\\s+.+");
    private static Pattern priceCurrencyPattern = Pattern.compile("\\d+(?:\\s+\\d+)*\\s+(.+)");
    private static Pattern carKilometragePattern = Pattern.compile("(\\d+(?:\\s+\\d+)*)");


    public static List<Car> extractCarPrices(String html) {
        List<Car> cars = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements carElements = doc.getElementsByClass("AdPhoto_wrapper__gAOIH");


        for (Element carElement : carElements) {

            String carName = extractStringElement(carElement, "AdPhoto_info__link__OwhY6", carNamePattern);
            Integer carYear = extractIntegerElement(carElement, "AdPhoto_info__link__OwhY6", carYearPattern);
            Integer carPrice = extractIntegerElement(carElement, "AdPrice_price__2L3eA", carPricePattern);
            String priceCurrency = extractStringElement(carElement, "AdPrice_price__2L3eA", priceCurrencyPattern);
            Integer carKilometrage = extractIntegerElement(carElement, "AdPrice_info__LYNmc", carKilometragePattern);

            cars.add(new Car(carName, carPrice, carKilometrage, carYear, priceCurrency));

        }
        return cars;


    }

    public static String extractStringElement(Element parentElement, String className, Pattern pattern) {
        Element targetElement = parentElement.getElementsByClass(className).first();

        if (targetElement == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(targetElement.text());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;

    }

    public static Integer extractIntegerElement(Element parentElement, String className, Pattern pattern) {
        Element targetElement = parentElement.getElementsByClass(className).first();

        if (targetElement == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(targetElement.text());
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1).replace(" ", ""));
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }


}

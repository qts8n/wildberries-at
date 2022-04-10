package main;

import org.testng.annotations.DataProvider;

public class Config {
    static final String GECKO_PATH = "/webdrivers/geckodriver/geckodriver";
    static final String HOSTNAME = "https://www.wildberries.ru/";

    @DataProvider(name = "city-name")
    public static Object[][] cityNames() {
        return new Object[][] {
                { "Хвалынск" },
                { "Неизвестнобург" }
        };
    }
}

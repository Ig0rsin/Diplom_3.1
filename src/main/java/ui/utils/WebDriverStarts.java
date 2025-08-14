package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverStarts {

    static String browserEnv = System.getenv("BROWSER");
    public static Browser browser = browserEnv == null ? Browser.CHROME : Browser.valueOf(browserEnv.toUpperCase());

    private static int counter = 1;

    private WebDriver driver;

    public static WebDriver getWebDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");
                return new ChromeDriver();
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "drivers/yandex/yandexdriver.exe");
                return new ChromeDriver();
            default:
                return new ChromeDriver();
        }
    }
}


/* Вариант 1
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverStarts {
    public static WebDriver createDriver(String browserType) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        if ("yandex".equalsIgnoreCase(browserType)) {
            System.setProperty("webdriver.chrome.driver", ".\\drivers\\yandexdriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
        }
        return new ChromeDriver(options); */


import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import ui.pages.*;
import ui.utils.WebDriverStarts;
import ui.utils.Browser; // импорт enum Browser
import api.clients.AuthClient;
import constants.Urls;

public class TestBase {

    protected WebDriver driver;
    protected Browser browserType; // по умолчанию, можно менять в тестах или через параметры

    public RegisterPage registerPage;
    public MainPage mainPage;
    public LoginPage loginPage;
    public LkPage lkPage;
    public RecoveryPage recoveryPage;

    @BeforeEach
    public void setUp() {
        // Получаем тип браузейро из системных свойств, по умолчанию CHROME
        String browserName = System.getProperty("browser", "CHROME").toUpperCase();
        try {
            browserType = Browser.valueOf(browserName);
        } catch (IllegalArgumentException e) {
            // Если неправильное значение - по умолчанию Chrome
            browserType = Browser.CHROME;
        }

        // Инициализируем WebDriver по факту выбранного браузера
        driver = WebDriverStarts.getWebDriver(browserType);

        // Инициализируем страницы после получения драйвера
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        lkPage = new LkPage(driver);
        recoveryPage = new RecoveryPage(driver);
    }

    protected void openBaseUrl() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не инициализирован! Вызовите setUp() перед использованием.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(600));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        driver.get(Urls.HOME_PAGE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        String token = mainPage.getCurrentUserToken();
        if (token != null) {
            AuthClient authClient = new AuthClient(Urls.BASE_URI);
            authClient.deleteUser(token)
                    .then()
                    .statusCode(HttpStatus.SC_ACCEPTED);
        }
    }

    //Закрываем модальное окно (оверлей), если оно вылезло
    protected void closeModalIfPresent(By overlayLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        try {
            WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(overlayLocator));
            if (overlay.isDisplayed()) {
                overlay.click(); // тук-тук по оверлею
                // JavaScript для скрытия, если клик не сработал:
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
                wait.until(ExpectedConditions.invisibilityOf(overlay));
            }
        } catch (Exception e) {
            // Если оверлей не найден или уже скрыт — чилим
        }
    }

    //Ожидание появления элемента на странице.
    protected WebElement waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //прокрутка
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}



/* Вариант 1

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ui.pages.*;
import ui.utils.WebDriverStarts;
import api.clients.AuthClient;
import constants.Urls;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Настройка драйвера Chrome с помощью WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Создание экземпляра ChromeDriver
        driver = new ChromeDriver();
    }

    public RegisterPage registerPage;
    public MainPage mainPage;
    public LoginPage loginPage;
    public LkPage lkPage;
    public RecoveryPage recoveryPage;

    public void initDriver(String browserType) {
        driver = new WebDriverStarts(driver);
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        lkPage = new LkPage(driver);
        recoveryPage = new RecoveryPage(driver);
    }

    protected void openBaseUrl() {
        driver.get(Urls.HOME_PAGE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        String token = mainPage.getCurrentUserToken();
        if (token != null) {
            AuthClient authClient = new AuthClient(Urls.BASE_URI);
            authClient.deleteUser(token)
                    .then()
                    .statusCode(HttpStatus.SC_ACCEPTED);
        }
    }
} */


/*

Вариант 2.2

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ui.pages.*;
import ui.utils.WebDriverStarts;
import ui.utils.Browser;
import api.clients.AuthClient;
import constants.Urls;

public class TestBase {

    protected WebDriver driver;
    protected Browser browserType = Browser.CHROME; // по умолчанию, можно менять в тестах или через параметры

    public RegisterPage registerPage;
    public MainPage mainPage;
    public LoginPage loginPage;
    public LkPage lkPage;
    public RecoveryPage recoveryPage;

    @BeforeEach
    public void setUp() {
        // Инициализация драйвера по умолчанию (например, Chrome)
        driver = WebDriverStarts.getWebDriver(browserType);

        // Инициализация страниц после получения драйвера
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        lkPage = new LkPage(driver);
        recoveryPage = new RecoveryPage(driver);
    }

    /**
     * Инициализация драйвера с указанием типа браузера в виде строки.
     * Например: "chrome", "yandex"
     *
     * @param browserTypeStr строка с названием браузера */
/*
public void initDriver(String browserTypeStr) {
    // Преобразуем строку в enum Browser
    this.browserType = Browser.valueOf(browserTypeStr.toUpperCase());

    // Получаем WebDriver для выбранного браузера
    driver = WebDriverStarts.getWebDriver(this.browserType);

    // Инициализация страниц после получения драйвера
    registerPage = new RegisterPage(driver);
    mainPage = new MainPage(driver);
    loginPage = new LoginPage(driver);
    lkPage = new LkPage(driver);
    recoveryPage = new RecoveryPage(driver);
}

protected void openBaseUrl() {
    driver.get(Urls.HOME_PAGE_URL); // главная страница - ТЫЦ
}

@AfterEach
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }

    String token = mainPage != null ? mainPage.getCurrentUserToken() : null;
    if (token != null) {
        AuthClient authClient = new AuthClient(Urls.BASE_URI);
        authClient.deleteUser(token)
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED);
    }
}

*/

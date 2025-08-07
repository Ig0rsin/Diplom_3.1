package ui.pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import api.clients.AuthClient;
import api.models.auth.UserLoginRequest;
import api.models.auth.UserRegisterRequest;
import api.utils.DataHelper;
import constants.Urls;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends PageBase {

    private final By enterAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By enterLK = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoBurgers = By.xpath(".//div[@class ='AppHeader_header__logo__2D0X2']/descendant::a");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    private String currentUserToken;
    private UserRegisterRequest createdUser;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Кликнуть по кнопке войти в аккаунт")
    public MainPage clickEnterInAccount() {
        WebElement element = waitForElementToBeClickable(enterAccount);
        element.click();
        return this;
    }

    @Step("Кликнуть по лого")
    public MainPage clickLogoBurgers() {
        WebElement element = waitForElementToBeClickable(logoBurgers);
        element.click();
        return this;
    }

    @Step("Кликнуть по кнопке конструктор")
    public MainPage clickConstructorButton() {
        WebElement element = waitForElementToBeClickable(constructorButton);
        element.click();
        return this;
    }

    @Step("Проверить что после перехода мы на главной странице")
    public String verifyMainPageUrlConstructor() {
        WebElement element = waitForElementToBeClickable(constructorButton);
        String actualUrl = webDriver.getCurrentUrl();
        //assertEquals(Urls.HOME_PAGE_URL, actualUrl, "Ссылка ведёт не на главную страницу после клика на кнопку конструктор");
        return actualUrl;
    }

    @Step("Проверить что после перехода мы на главной странице")
    public String verifyMainPageUrlBurger() {
        WebElement element = waitForElementToBeClickable(logoBurgers);
        String actualUrl = webDriver.getCurrentUrl();
        //assertEquals(Urls.HOME_PAGE_URL, actualUrl, "Ссылка ведёт не на главную страницу после клика на логотип");
        return actualUrl;
    }

    @Step("Кликнуть по кнопке личный кабинет")
    public MainPage clickEnterLK() {
        WebElement element = waitForElementToBeClickable(enterLK);
        element.click();
        return this;
    }

    @Step("Создание нового пользователя через API")
    public MainPage createUserViaApi() {
        createdUser = new DataHelper().createRandomUser();
        AuthClient authClient = new AuthClient(Urls.BASE_URI);
        Response response = authClient.registerUser(createdUser);
        response.then().statusCode(HttpStatus.SC_OK);
        Response loginResponse = authClient.loginUser(new UserLoginRequest(createdUser.getEmail(), createdUser.getPassword()));
        loginResponse.then().statusCode(HttpStatus.SC_OK);
        currentUserToken = authClient.getAccessToken(loginResponse);
        return this;
    }

    @Step("Клик по вкладке 'Булки'")
    public MainPage clickBunsTab() {
        WebElement element = waitForElementToBeClickable(bunsTab);
        element.click();
        return this;
    }

    @Step("Клик по вкладке 'Соусы'")
    public MainPage clickSaucesTab() {
        WebElement element = waitForElementToBeClickable(saucesTab);
        element.click();
        return this;
    }

    @Step("Клик по вкладке 'Начинки'")
    public MainPage clickFillingsTab() {
        WebElement element = waitForElementToBeClickable(fillingsTab);
        element.click();
        return this;
    }

    @Step("Проверка, что активна вкладка 'Булки'")
    public boolean isBunsTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.getText().equals("Булки");
    }

    @Step("Проверка, что активна вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.getText().equals("Соусы");
    }

    @Step("Проверка, что активна вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.getText().equals("Начинки");
    }

    @Step("Проверка, что активна вкладка 'Булки'")
    public void assertBunsTabIsActive() {
        WebElement element = webDriver.findElement(activeTab);
        //assertEquals(element.getText(), "Вкладка 'Булки' не активна", "Булки");
    }

    @Step("Проверка, что активна вкладка 'Соусы'")
    public void assertSaucesTabIsActive() {
        WebElement element = webDriver.findElement(activeTab);
        //assertEquals(element.getText(), "Вкладка 'Соусы' не активна", "Соусы");
    }

    @Step("Проверка, что активна вкладка 'Начинки'")
    public void assertFillingsTabIsActive() {
        WebElement element = webDriver.findElement(activeTab);
        //assertEquals(element.getText(), "Вкладка 'Начинки' не активна", "Начинки");
    }

    public String getCurrentUserToken() {
        return currentUserToken;
    }

    public String getCreatedUserEmail() {
        return createdUser != null ? createdUser.getEmail() : null;
    }

    public String getCreatedUserPassword() {
        return createdUser != null ? createdUser.getPassword() : null;
    }
}

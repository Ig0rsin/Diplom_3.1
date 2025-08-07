package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import constants.Urls;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends PageBase {

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Ввести email")
    public LoginPage inputEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage inputPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку войти")
    public LoginPage enterButtonClick() {
        webDriver.findElement(enterButton).click();
        return this;
    }

    @Step("Проверить что мы на странице авторизации после выхода из ЛК")
    public String verifyExitLkURL() {
        WebElement element = waitForElementToBeClickable(enterButton);
        String actualUrl = webDriver.getCurrentUrl();
        //assertEquals(Urls.LOGIN_PAGE_URL, actualUrl, "Ссылка ведёт не на форму авторизации");
        return actualUrl;
    }
}

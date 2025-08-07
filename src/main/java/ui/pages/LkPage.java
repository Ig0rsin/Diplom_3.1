package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import constants.Urls;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class LkPage extends PageBase {

    private final By loginInput = By.xpath(".//label[text()='Логин']/following-sibling::input");
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public LkPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу авторизации")
    public void openAuthorizeLkUrl() {
        webDriver.get(Urls.LOGIN_PAGE_URL);
    }

    @Step("Нажать на кнопку выйти")
    public LkPage clickExitButton() {
        WebElement element = waitForElementToBeClickable(exitButton);
        webDriver.findElement(exitButton).click();
        return this;
    }

    @Step("Получить текст поля логин")
    public String getLkLoginText() {
        WebElement element = waitForElementToBeVisible(loginInput);
        return element.getAttribute("value");
    }

    @Step("Получить URL текущей страницы и проверить его")
    public String verifyLkURL() {
        WebElement element = waitForElementToBeClickable(exitButton);
        String actualUrl = webDriver.getCurrentUrl();
        //assertEquals(Urls.LK_PAGE_URL, actualUrl, "Ссылка ведёт не на страницу личного кабинета");
        return actualUrl;
    }
}

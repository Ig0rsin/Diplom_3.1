import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GoToTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка перехода в Профиль по клику на «Личный кабинет»")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testLKverify(String browserType) throws InterruptedException { //было (String browserTypeStr)
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        lkPage.verifyLkURL();
        mainPage.deleteUserViaApi();

        driver.quit(); // закрываем драйвер после теста
    }

    @ParameterizedTest
    @DisplayName("Переход по клику на «Конструктор»")
    @Description("Проверка перехода на главную страницу из ЛК при клике на кнопку «Конструктор»")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testConstructorButtonRedirectsToMainPage(String browserType) {
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickConstructorButton();
        mainPage.verifyMainPageUrlConstructor();
        mainPage.deleteUserViaApi();

        driver.quit();
    }

    @ParameterizedTest
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    @Description("Проверка перехода на главную страницу из ЛК при клике на логотип")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testLogoClickRedirectsToMainPage(String browserType) {
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickLogoBurgers();
        mainPage.verifyMainPageUrlBurger();
        mainPage.deleteUserViaApi();

        driver.quit();
    }

    @ParameterizedTest
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Проверка выхода при клике на кнопку «Выйти» в личном кабинете")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testExitLK(String browserType) {
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();

        lkPage.clickExitButton();

        // После выхода проверяем URL или другой признак выхода
        loginPage.verifyExitLkURL();
        mainPage.deleteUserViaApi();

        driver.quit();
    }

    // Тесты на вкладки
    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Проверка, что при клике на вкладку 'Соусы' она становится активной")
    public void testSaucesTabIsActive() {
        openBaseUrl();
        mainPage.clickSaucesTab();
        mainPage.assertSaucesTabIsActive();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Проверка, что при клике на вкладку 'Начинки' она становится активной")
    public void testFillingsTabIsActive() {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.assertFillingsTabIsActive();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Проверка, что при клике на вкладку 'Булки' она становится активной")
    public void testBunsTabIsActive() throws InterruptedException {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.assertBunsTabIsActive();
    }
}

/*

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ui.utils.Browser;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class GoToTest extends TestBase {

    private String browserType;

    public GoToTest() {
    }

    @BeforeEach
    public void setUp() {initDriver(browserType);    }

    @ParameterizedTest
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    @Description("Проверка перехода в аккаунт по клику на «Личный кабинет»")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLKverify(String browserType) throws InterruptedException {
        this.browserType = browserType; // текущий тип браузера
        setUp(); // вызов setup для инициализации дровины с нужным браузейро

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        lkPage.verifyLkURL();
    }

    @ParameterizedTest
    @DisplayName("Переход по кнопке «Конструктор» из ЛК")
    @Description("Проверка перехода на главную страницу из ЛК при клике на кнопку «Конструктор»")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testConstructorButtonRedirectsToMainPage(String browserType) {
        this.browserType = browserType;
        setUp();

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickConstructorButton();
        mainPage.verifyMainPageUrlConstructor();
    }

    @ParameterizedTest
    @DisplayName("Переход по клику на логотип Stellar Burgers из ЛК")
    @Description("Проверка перехода на главную страницу из ЛК при клике на логотип")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLogoClickRedirectsToMainPage(String browserType) {
        this.browserType = browserType;
        setUp();

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickLogoBurgers();
        mainPage.verifyMainPageUrlBurger();
    }

    @ParameterizedTest
    @DisplayName("Проверка выхода из личного кабинета")
    @Description("Проверить что вышли из личного кабинета")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testExitLK(String browserType) {
        this.browserType = browserType;
        setUp();

        mainPage.createUserViaApi();
        lkPage.openAuthorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        lkPage.clickExitButton();
        loginPage.verifyExitLkURL();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Проверка, что при клике на вкладку 'Соусы' она становится активной")
    public void testSaucesTabIsActive() {
        openBaseUrl();
        mainPage.clickSaucesTab();
        mainPage.assertSaucesTabIsActive();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Проверка, что при клике на вкладку 'Начинки' она становится активной")
    public void testFillingsTabIsActive() {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.assertFillingsTabIsActive();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Проверка, что при клике на вкладку 'Булки' она становится активной")
    public void testBunsTabIsActive() throws InterruptedException {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.assertBunsTabIsActive();
    }
}

 */


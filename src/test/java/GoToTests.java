import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GoToTests extends TestBase {

    private String browserType;

    public GoToTests() {
    }

    @BeforeEach
    public void setUp() {
        initDriver(browserType);
    }

    @ParameterizedTest
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    @Description("Проверка перехода по клику на «Личный кабинет»")
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
    @Description("Проверить, что при клике на «Конструктор» из ЛК происходит переход на главную страницу")
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
    @Description("Проверить, что при клике на логотип из ЛК происходит переход на главную страницу")
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

    // это нужно параметризовать?

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

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class LoginTests extends TestBase {

    private String browserType;

    @ParameterizedTest
    @DisplayName("Войти по кнопке - Войти в аккаунт на главной")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testEnterButtonAccount(String browserType) throws InterruptedException {
        this.browserType = browserType;
        initDriver(browserType);
        mainPage.createUserViaApi();
        openBaseUrl();
        mainPage.clickEnterInAccount();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        // ПроверкИ
        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );
    }

    @ParameterizedTest
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("Вход по кнопке через кнопку «Личный кабинет»")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testEnterLKAccount(String browserType) throws InterruptedException {
        this.browserType = browserType;
        initDriver(browserType);
        mainPage.createUserViaApi();
        openBaseUrl();
        mainPage.clickEnterLK();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();

        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );
    }

    @ParameterizedTest
    @DisplayName("вход через кнопку войти на форме «Регистрация»")
    @Description("вход через кнопку войти на форме «Регистрация»")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testEnterRegisterAccount(String browserType) throws InterruptedException {
        this.browserType = browserType;
        initDriver(browserType);
        mainPage.createUserViaApi();
        registerPage.openRegisterUrl();
        registerPage.clickEnterRegisterButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();

        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );
    }

    @ParameterizedTest
    @DisplayName("вход через кнопку войти на форме «Восстановления пароля»")
    @Description("вход через кнопку войти на форме «Восстановления пароля»")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testEnterRecoveryAccount(String browserType) throws InterruptedException {
        this.browserType = browserType;
        initDriver(browserType);
        mainPage.createUserViaApi();
        recoveryPage.openForgotUrl();
        recoveryPage.clickEnterForgotButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();

        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );
    }
}

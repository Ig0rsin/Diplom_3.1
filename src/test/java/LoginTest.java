import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends TestBase {

    @ParameterizedTest
    @DisplayName("Войти по кнопке - «Войти в аккаунт» на главной")
    @Description("Проверка перехода при клике по кнопке «Войти в аккаунт» на главной")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testEnterButtonAccount(String browserType) throws InterruptedException {
        // Установка системного свойства для выбора браузера
        System.setProperty("browser", browserType);
        openBaseUrl();

        mainPage.createUserViaApi(); // новенький юзер через API
        // Навигация и вход в аккаунт
        mainPage.clickEnterInAccount();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();

        mainPage.clickEnterLK(); // в ЛК

        // Проверяем, что пользователь отображается
        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );

        mainPage.deleteUserViaApi();
    }

    @ParameterizedTest
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка перехода при клике по кнопке «Личный кабинет»")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testEnterLKAccount(String browserType) throws InterruptedException {
        System.setProperty("browser", browserType);
        openBaseUrl();

        mainPage.createUserViaApi();
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

        mainPage.deleteUserViaApi();
    }

    @ParameterizedTest
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода при клике по кнопке «Войти» в форме регистрация")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testEnterRegisterAccount(String browserType) throws InterruptedException {
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        registerPage.openRegisterUrl();
        registerPage.clickEnterRegisterButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();

        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );

        mainPage.deleteUserViaApi();
    }

    @ParameterizedTest
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода при клике по кнопке «Войти» в форме восстановления пароля")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testEnterRecoveryAccount(String browserType) throws InterruptedException {
        System.setProperty("browser", browserType);

        mainPage.createUserViaApi();
        recoveryPage.openForgotUrl();
        recoveryPage.clickEnterForgotButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();

        assertEquals(
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText(),
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации"
        );

        mainPage.deleteUserViaApi();
    }
}
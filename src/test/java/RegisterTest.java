import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.models.auth.UserRegisterRequest;
import api.utils.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterTest extends TestBase {

    private final UserRegisterRequest user = new DataHelper().createRandomUser();
    private String browserType;

    @ParameterizedTest
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации через UI")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testRegistrationSuccess(String browserType) {
        System.setProperty("browser", browserType);

        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        registerPage.inputPassword(user.getPassword());
        registerPage.registerButtonClick();
    }

    @ParameterizedTest
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверка ошибки, если пароль менее 6 символов")
    @ValueSource(strings = {"chrome", "yandex"}) // "chrome", "yandex"
    public void testIncorrectPasswordError(String browserType) {
        System.setProperty("browser", browserType);

        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        String password = "12345"; // короткий пароль для теста
        registerPage.inputPassword(password);
        registerPage.clickEmail(); // снимает фокус или триггерит валидацию
        assertEquals(
                "Некорректный пароль",
                "Некорректный пароль",
                registerPage.errorIncorrectPassword()
        );
    }
}

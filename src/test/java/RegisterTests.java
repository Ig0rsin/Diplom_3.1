import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.models.auth.UserRegisterRequest;
import api.utils.DataHelper;

public class RegisterTests extends TestBase {

    private final UserRegisterRequest user = new DataHelper().createRandomUser();
    private String browserType;

    @ParameterizedTest
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации через UI")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testRegistrationSuccess(String browserType) {
        this.browserType = browserType;
        initDriver(browserType);
        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        registerPage.inputPassword(user.getPassword());
        registerPage.registerButtonClick();
    }

    @ParameterizedTest
    @DisplayName("Тест проверки некорректного пароля")
    @Description("Проверка некорректного ввода пароля")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testIncorrectPasswordError(String browserType) {
        this.browserType = browserType;
        initDriver(browserType);
        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        String password = "12345"; // короткий пароль для теста
        registerPage.inputPassword(password);
        registerPage.clickEmail(); // снимает фокус или триггерит валидацию
        assertEquals(
                "Проверка ошибки, если пароль менее 6 символов",
                "Некорректный пароль",
                registerPage.errorIncorrectPassword()
        );
    }
}

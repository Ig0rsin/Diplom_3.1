import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import ui.pages.*;
import ui.utils.WebDriverStarts;
import api.clients.AuthClient;
import constants.Urls;

public class TestBase {

    protected WebDriver driver;

    public RegisterPage registerPage;
    public MainPage mainPage;
    public LoginPage loginPage;
    public LkPage lkPage;
    public RecoveryPage recoveryPage;

    public void initDriver(String browserType) {
        driver = WebDriverStarts.createDriver(browserType);
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
}

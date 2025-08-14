package api.clients;

import io.restassured.response.Response;
import api.models.auth.UserInfoUpdateRequest;
import api.models.auth.UserLoginRequest;
import api.models.auth.UserRegisterRequest;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private static final String API_AUTH_REGISTER = "/api/auth/register";
    private static final String API_AUTH_LOGIN = "/api/auth/login";
    private static final String API_AUTH_LOGOUT = "/api/auth/logout";
    private static final String API_AUTH_TOKEN = "/api/auth/token";
    private static final String API_AUTH_USER = "/api/auth/user";

    private static final String API_PASSWORD_RESET = "/api/password-reset";

    private final String base_uri;

    public AuthClient(String base_uri) {
        this.base_uri = base_uri;
    }

    public Response registerUser(UserRegisterRequest userRegisterRequest) {
        return given()
                .baseUri(this.base_uri)
                .header("Content-type", "application/json")
                .body(userRegisterRequest)
                .when()
                .post(API_AUTH_REGISTER);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .baseUri(this.base_uri)
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(API_AUTH_USER);
    }

    public Response loginUser(UserLoginRequest userLoginRequest) {
        return given()
                .baseUri(this.base_uri)
                .header("Content-type", "application/json")
                .body(userLoginRequest)
                .when()
                .post(API_AUTH_LOGIN);
    }

    public Response updateUser(String accessToken, UserInfoUpdateRequest userInfoUpdateRequest) {
        return given()
                .baseUri(this.base_uri)
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(userInfoUpdateRequest)
                .when()
                .patch(API_AUTH_USER);
    }

    public String getAccessToken(Response response) {
        return response.path("accessToken");
    }
}

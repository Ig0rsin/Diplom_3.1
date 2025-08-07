package api.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogoutRequest {
    private String token;
}

/* package api.models.auth;

public class UserLogoutRequest {
    private String token;

    public UserLogoutRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
} */
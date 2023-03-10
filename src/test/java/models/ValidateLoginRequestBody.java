package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateLoginRequestBody {
    private String username;
    private String password;
    @JsonProperty("request_token")
    private String requestToken;


    public ValidateLoginRequestBody(String username, String password, String requestToken) {
        this.username = username;
        this.password = password;
        this.requestToken = requestToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}

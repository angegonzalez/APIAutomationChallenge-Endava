package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSessionRequestBody {
    @JsonProperty("request_token")
    private String requestToken;

    public CreateSessionRequestBody(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}

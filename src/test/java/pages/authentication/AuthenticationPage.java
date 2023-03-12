package pages.authentication;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CreateSessionRequestBody;
import models.ValidateLoginRequestBody;
import org.apache.log4j.Logger;
import pages.BasePage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AuthenticationPage extends BasePage {

    private final Logger logger = Logger.getLogger(AuthenticationPage.class);
    private String requestToken;
    private String validatedRequestToken;
    private String sessionID;
    public void createRequestToken() {
        requestToken = requestSpecification.get("/authentication/token/new").
                getBody().jsonPath().get("request_token");
    }

    public void validateRequestToken(String username, String password) {
        ValidateLoginRequestBody validateLoginRequestBody =
                new ValidateLoginRequestBody(username, password, requestToken);
        validatedRequestToken = requestSpecification
                .body(validateLoginRequestBody).contentType(ContentType.JSON)
                .post("/authentication/token/validate_with_login").getBody().jsonPath().get("request_token");
    }

    public void noValidateRequestToken() {
        validatedRequestToken = requestToken;
    }

    public Response createSession() {
        CreateSessionRequestBody createSessionRequestBody = new CreateSessionRequestBody(validatedRequestToken);
        Response response = requestSpecification.body(createSessionRequestBody)
                .contentType(ContentType.JSON).post("/authentication/session/new");
        sessionID = response.body().jsonPath().get("session_id");
        if (sessionID != null)
            writeSessionID();
        return response;
    }

    private void writeSessionID() {
        if (dotenv.get("SESSION_ID") == null) {
            try {
                FileWriter writer = new FileWriter("src/test/resources/.env", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("\nSESSION_ID=" + sessionID);
                bufferedWriter.close();
            } catch (IOException e) {
                logger.error(e);
                logger.error("Error while opening .env file");
            }
        }
    }

}

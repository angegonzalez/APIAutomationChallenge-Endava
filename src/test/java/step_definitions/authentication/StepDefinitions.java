package step_definitions.authentication;

import common.StatusAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import pages.authentication.AuthenticationPage;

public class StepDefinitions extends StatusAssertions {
    private final AuthenticationPage authenticationPage = new AuthenticationPage();
    private String username;
    private String password;
    @Given("the user wants to do authentication with credentials {string} and {string}")
    public void userDoAuthenticationWithCredentials(String username, String password){
        this.username = username;
        this.password = password;
        authenticationPage.prepareRequest();
    }
    @When("the user creates the request token")
    public void userCreatesRequestToken(){
        authenticationPage.createRequestToken();
    }
    @And("the user validates the request token")
    public void userValidatesRequestToken(){
        authenticationPage.validateRequestToken(username, password);
    }
    @And("the user does not validate the request token")
    public void userDoesNotValidateToken(){
        authenticationPage.noValidateRequestToken();
    }
    @Then("the user successfully creates a session")
    public void userSuccessfullyCreatesASession(){
        Response response = authenticationPage.createSession();
        assertOK(response);
        Boolean isSuccess = Boolean.parseBoolean(response.body().jsonPath().get("success").toString());
        Assertions.assertThat(isSuccess).isTrue();
    }
    @Then("the user can't create a session")
    public void userCannotCreateASession(){
        Response response = authenticationPage.createSession();
        assertUnauthorized(response);
        Boolean isSuccess = Boolean.parseBoolean(response.body().jsonPath().get("success").toString());
        Boolean isFailure = Boolean.parseBoolean(response.body().jsonPath().get("failure").toString());
        String statusMessage = response.body().jsonPath().get("status_message").toString();
        Assertions.assertThat(isSuccess).isFalse();
        Assertions.assertThat(isFailure).isTrue();
        Assertions.assertThat(statusMessage).isEqualTo("Session denied.");
    }
}

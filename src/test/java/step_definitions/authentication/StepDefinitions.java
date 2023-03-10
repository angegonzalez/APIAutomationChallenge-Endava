package step_definitions.authentication;
import common.StatusAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
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
        ValidatableResponse validatableResponse = authenticationPage.createSession();
        assertOK(validatableResponse.extract().response());
        Boolean isSuccess = Boolean.parseBoolean(validatableResponse.extract().body().jsonPath().get("success").toString());
        Assertions.assertThat(isSuccess).isTrue();
    }
    @Then("the user can't create a session")
    public void userCannotCreateASession(){
        ValidatableResponse validatableResponse = authenticationPage.createSession();
        assertUnauthorized(validatableResponse.extract().response());
        Boolean isSuccess = Boolean.parseBoolean(validatableResponse.extract().body().jsonPath().get("success").toString());
        Boolean isFailure = Boolean.parseBoolean(validatableResponse.extract().body().jsonPath().get("failure").toString());
        String statusMessage = validatableResponse.extract().body().jsonPath().get("status_message").toString();
        Assertions.assertThat(isSuccess).isFalse();
        Assertions.assertThat(isFailure).isTrue();
        Assertions.assertThat(statusMessage).isEqualTo("Session denied.");
    }
}

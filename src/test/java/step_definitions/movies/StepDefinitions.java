package step_definitions.movies;

import common.StatusAssertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.RateMovieRequestBody;
import org.assertj.core.api.Assertions;
import pages.movies.MoviesPage;

public class StepDefinitions extends StatusAssertions {
    private final MoviesPage moviesPage = new MoviesPage();
    private Response response;
    private String movieID;

    @Given("the user has a movie id : {string}")
    public void userHasMovie(String movieID) {
        moviesPage.prepareRequest();
        this.movieID = movieID;
    }

    @When("the user tries to get the movie details")
    public void userTriesGetMovieDetails() {
        response = moviesPage.getMovieDetails(movieID);
    }
    @When("^the user tries to rate the movie with value : (.+)$")
    public void userTriesRateMovie(double value){
        RateMovieRequestBody rateMovieRequestBody = new RateMovieRequestBody(value);
        response = moviesPage.rateMovie(movieID, rateMovieRequestBody);
    }

    @Then("the user successfully gets the details of that movie")
    public void userGetsDetails() {
        assertOK(response);
        Assertions.assertThat(response.jsonPath().get("id").toString()).isEqualTo(movieID);
    }

    @Then("the user successfully rates the movie")
    public void userSuccessfullyRatesMovie(){
        assertCreated(response);
    }

    @Then("the user can't get the details of that movie")
    public void userCannotGetDetailsMovie(){
        assertNotFound(response);
    }
    @Then("the user can't rate the movie")
    public void userCannotRateMovie(){
        assertBadRequest(response);
        Assertions.assertThat(Boolean.parseBoolean(response.jsonPath().get("success").toString())).isFalse();
    }
    @And("the title of the movie is {string}")
    public void assertTitleMovie(String movieTitle){
        Assertions.assertThat(response.jsonPath().get("title").toString()).isEqualTo(movieTitle);
    }

    @And("the status message is : {string}")
    public void validateStatusMessage(String statusMessage) {
        Assertions.assertThat(response.jsonPath().get("status_message").toString()).isEqualTo(statusMessage);
    }

}
